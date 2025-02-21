import Foundation

func solution(_ alp:Int, _ cop:Int, _ problems:[[Int]]) -> Int {
    var time = 0
    // alp 알고력
    // cop 코딩력
    // problems의 원소는 [alp_req, cop_req, alp_rwd, cop_rwd, cost]의 형태로 이루어져 있습니다.
    // alp_req는 문제를 푸는데 필요한 알고력입니다.
    // cop_req는 문제를 푸는데 필요한 코딩력입니다.
    // alp_rwd는 문제를 풀었을 때 증가하는 알고력입니다.
    // cop_rwd는 문제를 풀었을 때 증가하는 코딩력입니다.
    // cost는 문제를 푸는데 드는 시간입니다.
    // 모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 return을 하시오.
    
    // 그리디인가...ㅋ

    // 알고력을 올리거나
    // 코딩력을 올리거나
    // 풀 수 있는 문제를 풀어 알고력과 풀이력을 올리거나 (0...30)
    
    // 문제들 중 제일 높은 알고력, 제일 높은 코딩력을 찾는다.
    
    let targetAlp = problems.map { $0[0] }.max()! 
    let targetCop = problems.map { $0[1] }.max()! 
    let alp = min(alp, targetAlp)
    let cop = min(cop, targetCop)
    
    var dp = [[Int]](repeating: [Int](repeating: Int.max, count: targetCop + 1), count: targetAlp+1)
    
    dp[alp][cop] = 0
    
    for i in alp...targetAlp {
        for j in cop...targetCop {
            if i + 1 <= targetAlp {
                dp[i+1][j] = min(dp[i+1][j], dp[i][j] + 1)
            }
            if j + 1 <= targetCop {
                dp[i][j+1] = min(dp[i][j+1], dp[i][j] + 1)
            }
            for problem in problems {
                guard i >= problem[0] && j >= problem[1] else { continue }
                let na = min(i + problem[2], targetAlp)
                let nc = min(j + problem[3], targetCop)
                dp[na][nc] = min(dp[na][nc], dp[i][j] + problem[4])
            }
        }
    }
    
    return dp[targetAlp][targetCop]
}