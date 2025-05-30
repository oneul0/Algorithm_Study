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

// 민서 이 싸이코자식.. 뇌를 트리로 만들어버리네...

struct Edge {
    let from: Int
    let to: Int
}

let io = FileIO()

let n = io.readInt()
let m = io.readInt()

var graph = [[Int]](repeating: [Int](), count: n+1)
var parent = Array(0...n)
var taskCount = 0

func union(_ g: Int, _ v: Int) {
    var g = find(g)
    var v = find(v)
    if g > v { swap(&g, &v) }
    parent[v] = g
}

func find(_ g: Int) -> Int {
    if parent[g] == g { return g }
    parent[g] = find(parent[g])
    return parent[g]
}
//사이클을 형성하는 시냅스 제거 작업 개수 카운팅
(0..<m).forEach { _ in
    let g = find(io.readInt())
    let v = find(io.readInt())
    
    if g == v {
        taskCount += 1
        return
    }
    union(g, v)
}
// 연결안된 뉴런 그룹 연결 작업 개수 카운팅
//6 10
//3 4
//1 3
//1 5
//3 5
//1 6
//3 6
//2 5
//1 2
//5 6
//2 3
// 3-4에서 4의 루트노드는 3이 되고
// 1-3에서 3의 루트노드는 1이 되지만
// 여전히 4의 루트노드는 3이기 때문에
// 루트노드의 갯수를 셀 때에는 부모노드를 찾는 식으로 접근해야함
taskCount += Set(Set(parent[1...]).map { find($0)}).count - 1

print(taskCount)