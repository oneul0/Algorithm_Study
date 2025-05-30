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

struct Edge {
    let to: Int
    let weight: Int
}
let io = FileIO()

let n = io.readInt(),
    m = io.readInt()

var edgeList = [[Edge]](repeating: [Edge](), count: n+1)

(0..<n-1).forEach { _ in
    let g = io.readInt()
    let v = io.readInt()
    let w = io.readInt()
    edgeList[g].append(Edge(to: v, weight: w))
    edgeList[v].append(Edge(to: g, weight: w))
}


var answer = ""

(0..<m).forEach { _ in
    func dfs(_ from: Int, _ to: Int, _ distance: Int) -> Int {
        isVisited[from] = true
        if from == to { return distance }
        for edge in edgeList[from] {
            if isVisited[edge.to] { continue }
            let result = dfs(edge.to, to, distance + edge.weight)
            if result != 0 {
                return result
            }
        }
        return 0
    }
    var isVisited = [Bool](repeating: false, count: n+1)
    let from = io.readInt()
    let to = io.readInt()
    let distance = dfs(from, to, 0)
    answer.write("\(distance)\n")
}

print(answer)