let n = Int(readLine()!)!
let costList = (0..<n).map { _ in readLine()!.split { $0 == " " }.map { Int(String($0))! }}
var dp = [[Int]](repeating: [Int](repeating: 0, count: 3), count: n)
let inf = 987654321
var answer = Int.max

for k in 0...2 {
    let a = (k+1)%3
    let b = (k+2)%3
    
    dp[0][k] = costList[0][k]
    dp[0][a] = inf
    dp[0][b] = inf
    
    for i in 1..<n {
        dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + costList[i][0]
        dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + costList[i][1]
        dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + costList[i][2]
    }
    
    answer = min(answer, min(dp[n-1][a], dp[n-1][b]))
}

print(answer)