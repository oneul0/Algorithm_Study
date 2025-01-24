import Foundation

func solution(_ n:Int, _ tops:[Int]) -> Int {
    let totalCount = (2*n)+1
    var dp = [Int](repeating: 0, count: totalCount+1)
    dp[1] = 1
    dp[2] = 2 + tops[0]
    for i in 3...totalCount {
        if i%2 == 0 {
            dp[i] = (dp[i-1]*(1+tops[(i/2)-1]))%10007 + dp[i-2]%10007
        } else {
            dp[i] = dp[i-2]%10007 + dp[i-1]%10007
        }
        dp[i] %= 10007
    }
    return dp[totalCount]
}