import Foundation

func solution(_ info:[[Int]], _ n:Int, _ m:Int) -> Int {
    let inf = 987654321
    var dp = [[Int]](repeating: [Int](repeating: inf, count: m), count: info.count+1)
    dp[0][0] = 0
    
    for i in 1...info.count {
        let a = info[i-1][0]
        let b = info[i-1][1]
        for j in 0..<m {
            dp[i][j] = min(dp[i][j], dp[i-1][j] + a)
            guard j+b < m else { continue }
            dp[i][j+b] = min(dp[i-1][j], dp[i][j+b])
        }
    }
    let minA = dp.last!.min()!
    let answer = minA < n ? minA : -1
    return answer
}