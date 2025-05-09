let nk = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nk[0],
    k = nk[1]

let stdList = (0..<n).map { _ in readLine()!.count }
var friendCountList = [Int](repeating: 0, count: 20 + 1)

var start = 0
var end = 0
var answer = 0

repeat {
    if end < n { friendCountList[stdList[end]] += 1 }
    if end - start == k || end >= n {
        answer += friendCountList[stdList[start]] - 1
        friendCountList[stdList[start]] -= 1
        start += 1
    }
    if end < n { end += 1 }
} while start != end

print(answer)