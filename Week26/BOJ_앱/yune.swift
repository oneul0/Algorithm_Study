let nm = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nm[0],
    m = nm[1]
let memoryList = [0] + readLine()!.split { $0 == " " }.map { Int(String($0))! }
let costList = [0] + readLine()!.split { $0 == " " }.map { Int(String($0))! }

var dp = [[Int]](repeating: [Int](repeating: 0, count: 10000+1), count: n+1)

var answer = Int.max

for i in 1...n {
    for j in 0...10000 {
        dp[i][j] = max(dp[i][j], dp[i-1][j])
        if j - costList[i] >= 0 {
            dp[i][j] = max(dp[i][j], dp[i-1][j-costList[i]] + memoryList[i])
        }
        if dp[i][j] >= m {
            answer = min(answer, j)
        }
    }
}

print(answer)