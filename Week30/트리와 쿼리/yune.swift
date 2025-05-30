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

let n = io.readInt(),
    r = io.readInt(),
    q = io.readInt()

var graph = [[Int]](repeating: [Int](), count: n+1)
var subTreeSizeList = [Int](repeating: 0, count: n+1)

(0..<n-1).forEach { _ in
    let u = io.readInt()
    let v = io.readInt()
    graph[u].append(v)
    graph[v].append(u)
}

func dfs(_ root: Int) {
    subTreeSizeList[root] = 1
    for node in graph[root] {
        guard subTreeSizeList[node] == 0 else { continue }
        dfs(node)
        subTreeSizeList[root] += subTreeSizeList[node]
    }
}

let root = r
dfs(root)

let answer = (0..<q).map { _ in
    let query = io.readInt()
    let answer = subTreeSizeList[query]
    return "\(answer)\n"
}.joined()

print(answer)