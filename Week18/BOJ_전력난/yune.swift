import Foundation

//Thanks to 라이노
final class FileIO {
    private let buffer:[UInt8]
    private var index: Int = 0

    init(fileHandle: FileHandle = FileHandle.standardInput) {
        
        buffer = Array(try! fileHandle.readToEnd()!)+[UInt8(0)] // 인덱스 범위 넘어가는 것 방지
    }

    @inline(__always) private func read() -> UInt8 {
        defer { index += 1 }

        return buffer[index]
    }

    @inline(__always) func readInt() -> Int {
        var sum = 0
        var now = read()
        var isPositive = true

        while now == 10
                || now == 32 { now = read() } // 공백과 줄바꿈 무시
        if now == 45 { isPositive.toggle(); now = read() } // 음수 처리
        while now >= 48, now <= 57 {
            sum = sum * 10 + Int(now-48)
            now = read()
        }

        return sum * (isPositive ? 1:-1)
    }

    @inline(__always) func readString() -> String {
        var now = read()

        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시

        let beginIndex = index-1

        while now != 10,
              now != 32,
              now != 0 { now = read() }

        return String(bytes: Array(buffer[beginIndex..<(index-1)]), encoding: .ascii)!
    }

    @inline(__always) func readByteSequenceWithoutSpaceAndLineFeed() -> [UInt8] {
        var now = read()

        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시

        let beginIndex = index-1

        while now != 10,
              now != 32,
              now != 0 { now = read() }

        return Array(buffer[beginIndex..<(index-1)])
    }
}

struct Edge {
    let v1: Int
    let v2: Int
    let cost: Int
}
let io = FileIO()
var totalAnswer = ""
while true {
    func union(_ v1: Int, _ v2: Int) {
        let p1 = find(v1)
        let p2 = find(v2)
        guard p1 != p2 else { return }
        parentList[p2] = p1
    }
    
    func find(_ v: Int) -> Int {
        if parentList[v] == -1 {
            return v
        }
        parentList[v] = find(parentList[v])
        return parentList[v]
    }
    // n - 간선갯수, m = 정점갯수
    let m = io.readInt(), n = io.readInt()
    guard m != 0, n != 0 else { break }
    var parentList = ContiguousArray<Int>(repeating: -1, count: m)
    let edgeList: [Edge] = (0..<n).map { _ in Edge(v1: io.readInt(), v2: io.readInt(), cost: io.readInt())
    }.sorted { $0.cost < $1.cost }
    
    var choosedEdgeCount = 0
    var answer = edgeList.reduce(0) { partialResult, edge in
        return partialResult + edge.cost
    }
    
    for edge in edgeList {
        guard choosedEdgeCount < m-1 else { break }
        let v1 = edge.v1
        let v2 = edge.v2
        if find(v1) == find(v2) { continue }
        union(v1, v2)
        answer -= edge.cost
        choosedEdgeCount += 1
    }
    totalAnswer.write("\(answer)\n")
}
print(totalAnswer)