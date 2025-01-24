let m = Int(readLine()!)! // M (1 ≤ M ≤ 50), 조약돌의 색상은 1부터 M까지 중의 하나이다.
//각 색상의 조약돌 개수는 1보다 크거나 같고 50보다 작거나 같은 자연수이다.
let classifications = readLine()!.split { $0 == " " }.map { Int(String($0))! }
let n = classifications.reduce(0, +)
let k = Int(readLine()!)! //(1 ≤ K ≤ N)
//비밀 박스에서 조약돌을 랜덤하게 K개 뽑았을 때, 뽑은 조약돌이 모두 같은 색일 확률을 구하는 프로그램을 작성하시오.
var c = [[Int]](repeating: [Int](repeating: 0, count: k+1), count: n+1)

var answer: Double = 0
for amount in classifications {
    guard amount >= k else { continue }
    var tmpSum: Double = 1
    for i in 0..<k {
        tmpSum *= Double(amount-i) / Double(n-i)
    }
    answer += tmpSum
}
print(answer)