import Foundation

let nk = readLine()!.split { $0 == " " }.map {  Int(String($0))! }
let coinList = (0..<nk[0]).map { _ in Int(readLine()!)! }.sorted { $0 < $1 }
var dp = [1] + [Int](repeating: 0, count: nk[1])
for coin in coinList {
    if coin > nk[1] { continue }
    for i in coin...nk[1] {
        if dp[i] + dp[i-coin] >= Int(pow(2.0, 31.0)) {
            dp[i] = 0
        }
        dp[i] += dp[i-coin]
    }
}
print(dp[nk[1]])