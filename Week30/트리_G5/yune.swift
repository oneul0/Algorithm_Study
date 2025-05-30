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
let parentList = (0..<n).map { _ in io.readInt() }
let targetRemoveNode = io.readInt()

var leafNodeCount = [Int](repeating: 0, count: n)
var graph = [[Int]](repeating: [Int](), count: n)

let parentRemovedNode = parentList[targetRemoveNode]

//　부모노드정보로 트리를 구성
// 트리 구성 후 리프노드를 카운트
// 루트노드의 리프노트카운트에서 해당 노드의 리프노드 카운트를 뺀 값이 정답
var rootNode = 0

parentList.enumerated().forEach { node, parent in
    if parent == -1 {
        rootNode = node
        return
    }
    if node == targetRemoveNode {
        return
    }
    if parent == targetRemoveNode {
        return
    }
    graph[parent].append(node)
}

func dfs(_ node: Int, _ targetRemoveNode: Int) -> Int {
    if graph[node].isEmpty {
        return 1
    }
    for childNode in graph[node] {
        leafNodeCount[node] += dfs(childNode, targetRemoveNode)
    }
    return leafNodeCount[node]
}

let answer = targetRemoveNode == rootNode ? 0 : dfs(rootNode, targetRemoveNode)

print(answer)