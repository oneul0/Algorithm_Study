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
var negas = [Int]()
var posits = [Int]()
for i in 0..<n {
    let a = io.readInt()
    if a < 1 { 
        negas.append(a)
    continue
    }
    posits.append(a)    
}
negas.sort { $0 > $1 }
posits.sort { $0 < $1 }
var answer = 0

while posits.count >= 2 {
    let a = posits.removeLast()
    let b = posits.removeLast()
    if b == 1 {
        answer += (a+b)
        continue
    }
    answer += a*b
}

while negas.count >= 2 {
    let a = negas.removeLast()
    let b = negas.removeLast()
    if a*b <= a+b { break }
    answer += a*b
}
answer += posits.reduce(0, +)
answer += negas.reduce(0, +)
print(answer)