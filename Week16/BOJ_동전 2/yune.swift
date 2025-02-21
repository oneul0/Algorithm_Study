let nk = readLine()!.split { $0 == " " }.map { Int(String($0))! }
let coinList = (0..<nk[0]).map { _ in Int(readLine()!)! }.reduce(into: Set<Int>()){ $0.insert($1) }.sorted { $0 < $1 }
var dp = [0] + [Int](repeating: 987654321, count: 10000)
for coin in coinList {
    for i in stride(from: coin, through: nk[1], by: 1) {
        dp[i] = min(dp[i], dp[i-coin] + 1)
    }
}
print(dp[nk[1]] == 987654321 ? -1 : dp[nk[1]])