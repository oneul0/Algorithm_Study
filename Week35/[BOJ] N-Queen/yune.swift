//N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
//N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
//첫째 줄에 N이 주어진다. (1 ≤ N < 15)
func NQueen(_ y: Int){
    if y == n {
        answer += 1
        return
    }
    for j in 0..<n {
        if vertics[j] { continue }
        if diagonal1[y+j] { continue }
        if diagonal2[y-j+n-1] { continue }
        vertics[j] = true
        diagonal1[y+j] = true
        diagonal2[y-j+n-1] = true
        NQueen(y+1)
        vertics[j] = false
        diagonal1[y+j] = false
        diagonal2[y-j+n-1] = false
    }
}
let n = Int(readLine()!)!
var vertics = [Bool](repeating: false, count: 15)
var diagonal1 = [Bool](repeating: false, count: 2*n-1)
var diagonal2 = [Bool](repeating: false, count: 2*n-1)

var answer = 0
NQueen(0)
print(answer)
