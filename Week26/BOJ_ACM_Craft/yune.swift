import Foundation

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

// 사용 방법

let fileIO = FileIO()

struct Queue {
    var head = 0
    var front: Int? {
        queue[head]
    }
    var rear: Int? {
        guard let last = queue.last else { return nil }
        return last
    }
    var isEmpty: Bool {
        queue.count == head
    }
    private var queue = [Int?]()
    mutating func enqueue(_ element: Int) {
        queue.append(element)
    }
    mutating func dequeue() -> Int? {
        if isEmpty { return nil }
        let ret = queue[head]
        queue[head] = nil
        head += 1
        return ret
    }
}

var answer = ""

(0..<fileIO.readInt()).forEach { _ in
    let n = fileIO.readInt(),
        k = fileIO.readInt()
    
    let cost = [0] + (0..<n).map { _ in fileIO.readInt() }
    var queue = Queue()
    var graph = [[Int]](repeating: [Int](), count: n+1)
    var indegree = [Int](repeating: 0, count: n+1)
    var dp = [Int](repeating: 0, count: n+1)
    
    (0..<k).forEach { _ in
        let g = fileIO.readInt(),
            v = fileIO.readInt()
        graph[g].append(v)
        indegree[v] += 1
    }
    
    let w = fileIO.readInt()
    
    for i in 1...n {
        guard indegree[i] == 0 else { continue }
        dp[i] = cost[i]
        queue.enqueue(i)
    }

    while !queue.isEmpty {
        let current = queue.dequeue()!
        for i in graph[current] {
            dp[i] = max(dp[current] + cost[i], dp[i])
            indegree[i] -= 1
            if indegree[i] == 0 {
                queue.enqueue(i)
            }
        }
    }

    answer.write("\(dp[w])\n")
}
print(answer)