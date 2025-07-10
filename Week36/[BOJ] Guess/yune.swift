import Foundation

var map = Array(repeating: Array(repeating: Character(" "), count: 11), count: 11)
var num = 0
var v = [Int]()

func possible(_ idx: Int) -> Bool {
    var sum = 0
    for i in stride(from: idx, through: 0, by: -1) {
        sum += v[i]
        let sign = map[i][idx]
        if sign == "+" && sum <= 0 { return false }
        if sign == "-" && sum >= 0 { return false }
        if sign == "0" && sum != 0 { return false }
    }
    return true
}

func solve(_ idx: Int) {
    if idx == num {
        print(v.map(String.init).joined(separator: " "))
        exit(0) // 종료
    }

    for i in -10...10 {
        v.append(i)
        if possible(idx) {
            solve(idx + 1)
        }
        v.removeLast()
    }
}

// 입력 처리
if let input = readLine(), let n = Int(input) {
    num = n
}

if let signLine = readLine() {
    var idx = 0
    for i in 0..<num {
        for j in i..<num {
            map[i][j] = signLine[signLine.index(signLine.startIndex, offsetBy: idx)]
            idx += 1
        }
    }
}

solve(0)
