let nmh = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nmh[0],
    m = nmh[1],
    h = nmh[2]
var laddar = [[Bool]](repeating: [Bool](repeating: false, count: n), count: h)

(0..<m).forEach { _ in
    let ab = readLine()!.split { $0 == " " }.map { Int(String($0))! },
        a = ab[0] - 1,
        b = ab[1] - 1
    // a번 높이에 b번 세로줄의 오른쪽에 위치시킴
    laddar[a][b] = true
}

func isAvailable() -> Bool {
    for i in 0..<n {
        var start = i
        // j가 높이, start가 세로줄
        for j in 0..<h {
            if laddar[j][start] {
                start += 1
            }else if start > 0 && laddar[j][start-1] {
                start -= 1
            }
        }
        if i != start { return false }
    }
    return true
}

var answer = 4

func backtrack(_ cnt : Int, _ x : Int, _ y : Int) {
    if answer < cnt { return }
    if isAvailable() {
        answer = min(answer, cnt)
    }
    if cnt == 3 { return }
    
    for i in 0..<n-1 {
        for j in y..<h {
            if laddar[j][i] == false {
                laddar[j][i] = true
                backtrack(cnt + 1, i + 2, j)
                laddar[j][i] = false
            }
        }
    }
}
backtrack(0, 0, 0)
print(answer > 3 ? -1 : answer)
