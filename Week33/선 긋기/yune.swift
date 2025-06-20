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

struct Line {
    let start: Int
    let end: Int
}
var answer = 0

let calibration = 1000000000
let io = FileIO()

let n = io.readInt()
let lines = (0..<n).map { _ in Line(start: io.readInt() + calibration, end: io.readInt() + calibration) }.sorted { 
    if $0.start == $1.start {
        return $0.end < $1.end
    }
    return $0.start < $1.start
}

if !lines.isEmpty {
    var curStart = lines.first!.start
    var curEnd = lines.first!.end

    for line in lines {
        if curEnd < line.start {
            answer += curEnd - curStart

            curStart = line.start
            curEnd = line.end
            continue
        }
        if curEnd < line.end {
            curEnd = line.end
        }
    }

    answer += curEnd - curStart
}

print(answer)