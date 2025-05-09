import Foundation

final class FileIO {
    private let buffer: Data
    private var index: Int = 0

    init(fileHandle: FileHandle = FileHandle.standardInput) {
        self.buffer = try! fileHandle.readToEnd()! // 인덱스 범위 넘어가는 것 방지
    }

    @inline(__always) private func read() -> UInt8 {
        defer {
            index += 1
        }
        guard index < buffer.count else { return 0 }

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
            var str = ""
            var now = read()

            while now == 10
                    || now == 32 { now = read() } // 공백과 줄바꿈 무시

            while now != 10
                    && now != 32 && now != 0 {
                str += String(bytes: [now], encoding: .ascii)!
                now = read()
            }
        return str
    }
}
let io = FileIO()

let n = io.readInt()
let t = io.readInt()

var timeList = [Int](repeating: 0, count: 100_001)
var timeSumList = [Int](repeating: 0, count: 100_002)

var minStart = Int.max
var maxEnd = Int.min

var changePoint = Set<Int>()

for _ in 0..<n {
    let k = io.readInt()
    for _ in 0..<k {
        let s = io.readInt()
        let e = io.readInt()
        if s < minStart {
            minStart = s
        }
        if e > maxEnd {
            maxEnd = e
        }
        for i in s+1...e+1 {
            changePoint.insert(i)
        }
        for j in s..<e {
            timeList[j] += 1
        }
    }
}

for i in minStart+1...maxEnd+1 {
    timeSumList[i] += timeList[i-1] + timeSumList[i-1]
}

var start = Int.min
var end = Int.min
var score = Int.min

for point in changePoint {
    if point >= 100_001 { continue }
    let curScore = timeSumList[point+t-1] - timeSumList[point-1]
    if curScore > score {
        start = point - 1
        end = point + t - 1
        score = curScore
    }
}

print(start, end)
