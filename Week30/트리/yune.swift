import Foundation
// MARK: - FileIO, Thanks to Wapas

final class FileIO {
    private let buffer: [UInt8]
    private var index: Int = 0
    
    init(fileHandle: FileHandle = FileHandle.standardInput) {
        buffer = Array(try! fileHandle.readToEnd()!) + [UInt8(0)]
    }
    
    @inline(__always) private func read() -> UInt8 {
        defer { index += 1 }
        return buffer[index]
    }
    
    @inline(__always) func readInt() -> Int {
        var sum = 0
        var now = read()
        var isPositive = true
        while now == 10 || now == 32 { now = read() }
        if now == 45 { isPositive.toggle(); now = read() }
        while now >= 48, now <= 57 {
            sum = sum * 10 + Int(now-48)
            now = read()
        }
        return sum * (isPositive ? 1 : -1)
    }
    
    @inline(__always) func readString() -> String {
        var now = read()
        while now == 10 || now == 32 { now = read() }
        let beginIndex = index-1
        while now != 10, now != 32, now != 0 { now = read() }
        return String(bytes: Array(buffer[beginIndex ..< (index - 1)]), encoding: .ascii)!
    }
    
    @inline(__always) func readStringSum() -> Int {
        var byte = read()
        while byte == 10 || byte == 32 { byte = read() }
        var sum = Int(byte)
        while byte != 10 && byte != 32 && byte != 0 { byte = read(); sum += Int(byte) }
        return sum - Int(byte)
    }
}

var answer = ""
var caseNumber = 0
let io = FileIO()

while true {
    caseNumber += 1
    func union(_ g: Int, _ v: Int) {
        var g = find(parent[g])
        var v = find(parent[v])
        //언제나 g가 작도록.
        if g > v { swap(&g, &v) }
        parent[v] = g
    }

    func find(_ v: Int) -> Int {
        if v == parent[v] { return v }
        parent[v] = find(parent[v])
        return parent[v]
    }

    let n = io.readInt(),
        m = io.readInt()

    if n == 0 && m == 0 { break }

    var parent = Array(0...n)
    var edges = [(Int, Int)]()

    (0..<m).forEach { _ in
        edges.append((io.readInt(), io.readInt()))
    }

    var cycleNode = Set<Int>()
    for edge in edges {
        let rootG = find(edge.0)
        let rootV = find(edge.1)
        if rootG == rootV { 
            cycleNode.insert(edge.0)
            cycleNode.insert(edge.1)
        }
        union(edge.0, edge.1)
    }

    let notTreeRoots = Set(cycleNode.map { find($0) })
    let roots = Set(parent[1...].map { find($0) })
    let treeCount = roots.filter { !notTreeRoots.contains($0) }.count

    switch treeCount {
        case 0: answer.write("Case \(caseNumber): No trees.\n")
        case 1: answer.write("Case \(caseNumber): There is one tree.\n")
        default: answer.write("Case \(caseNumber): A forest of \(treeCount) trees.\n")
    }
}
print(answer)