import Foundation

class FileIO {
    @inline(__always) private var buffer: [UInt8] = Array(FileHandle.standardInput.readDataToEndOfFile()) + [0], byteIdx = 0
    
    @inline(__always) private func readByte() -> UInt8 {
        defer { byteIdx += 1 }
        return buffer.withUnsafeBufferPointer { $0[byteIdx] }
    }
    
    @inline(__always) func readInt() -> Int {
        var number = 0, byte = readByte(), isNegative = false
        while byte == 10 || byte == 32 { byte = readByte() }
        if byte == 45 { byte = readByte(); isNegative = true }
        while 48...57 ~= byte { number = number * 10 + Int(byte - 48); byte = readByte() }
        return number * (isNegative ? -1 : 1)
    }
}

let io = FileIO()

let n = io.readInt()
var graph = (1...n).reduce(into: [Int:(Int, Int)]()) { graph, node in
    return graph[node] = (-1, -1)
}

(0..<n).forEach { _ in
    let root = io.readInt()
    let l = io.readInt()
    let r = io.readInt()
    graph[root] = (l, r)
}

var count = 0
var lastNode = 0
var isMoveFinishied = false

func move(_ node: Int) {
//    1. 현재 위치한 노드의 왼쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 왼쪽 자식 노드로 이동한다.
//    2. 그렇지 않고 현재 위치한 노드의 오른쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 오른쪽 자식 노드로 이동한다.
//    3. 그렇지 않고 현재 노드가 유사 중위 순회의 끝이라면, 유사 중위 순회를 종료한다.
//    4. 그렇지 않고 부모 노드가 존재한다면, 부모 노드로 이동한다.
//    5. 유사 중위 순회를 종료할 때까지 1 ~ 4를 반복한다.
    if graph[node]!.0 != -1 {
        count += 1
        move(graph[node]!.0)
        if !isMoveFinishied { count += 1 }
    }
    if graph[node]!.1 != -1 {
        count += 1
        move(graph[node]!.1)
        if !isMoveFinishied { count += 1 }
    }
    if node == lastNode {
        isMoveFinishied = true
        return
    }
}
func findLastNode(_ node: Int) {
    if graph[node]!.0 != -1 {
        findLastNode(graph[node]!.0)
    }
    lastNode = node
    if graph[node]!.1 != -1 {
        findLastNode(graph[node]!.1)
    }
}
findLastNode(1)
move(1)
print(count)