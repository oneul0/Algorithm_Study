var cmd = readLine()!.split { $0 == " " }.map { Int(String($0))! }
cmd.removeLast()
let n = cmd.count
let cost = [
    //   0  1  2  3  4
        [1, 2, 2, 2, 2],
        [3, 1, 3, 4, 3],
        [3, 3, 1, 3, 4],
        [3, 4, 3, 1, 3],
        [3, 3, 4, 3, 1]
]

let inf = 987654321
var dp = [[[Int]]](repeating: [[Int]](repeating: [Int](repeating: inf, count: 5), count: 5), count: n+1)
dp[0][0][0] = 0;

var answer = inf
for stage in stride(from: 1, through: n, by: 1){
    let nextPos = cmd[stage-1]
    if nextPos == 0 { break }
    for left in 0..<5 {
        for right in 0..<5 {
            dp[stage][left][nextPos] = min(dp[stage][left][nextPos], dp[stage-1][left][right] + cost[right][nextPos])
        }
    }
    for right in 0..<5 {
        for left in 0..<5 {
            dp[stage][nextPos][right] = min(dp[stage][nextPos][right], dp[stage-1][right][left] + cost[left][nextPos])
        }
    }
}

for left in 0..<5 {
    for right in 0..<5 {
        if answer > dp[n][left][right] {
            answer = dp[n][left][right]
        }
    }
}

print(answer)