struct Point {
    let r: Int
    let c: Int
}
var paper = [[Int]]()
var possiblePoints = [Point]()
var isFilled = [[Bool]](repeating: [Bool](repeating: false, count: 10), count: 10)
var answer = Int.max

for i in 0..<10 {
    let line = readLine()!.split { $0 == " "}.map { Int($0)! }
    for j in line.indices {
        if line[j] == 1 {
            possiblePoints.append(Point(r: i, c: j))
        } else {
            isFilled[i][j] = true
        }
    }
    paper.append(line)
}

//var isWrapedPoint = [Bool](repeating: false, count: possiblePoints.count)
var paperCount = [0, 5, 5, 5, 5, 5]


func isWrapable(_ r: Int, _ c: Int, _ size: Int) -> Bool {
    guard (0..<10)~=r, r+size<=10, c+size<=10, (0..<10)~=c else { return false }

    for i in r..<r+size {
        for j in c..<c+size {
            if isFilled[i][j] { return false }
        }
    }
    return true
}

func unwrap(_ r: Int, _ c: Int, _ size: Int) {
    for i in r..<r+size {
        for j in c..<c+size {
            isFilled[i][j] = false
        }
    }
}

func wrap(_ r: Int, _ c: Int, _ size: Int) {
    for i in r..<r+size {
        for j in c..<c+size {
            isFilled[i][j] = true
        }
    }
}

func dfs(_ depth: Int) {
    if depth >= answer { return }

    // 다음 안 채워진 좌표 찾기
    var found = false
    for i in 0..<10 {
        for j in 0..<10 {
            if !isFilled[i][j] {
                found = true
                for size in (1...5).reversed() {
                    // 종이를 다 썼다면 패스
                    if paperCount[size] == 0 { continue }
                    // 큰종이를 벗어난다면 패스
                    if !isWrapable(i, j, size) { continue }

                    wrap(i, j, size)
                    paperCount[size] -= 1

                    dfs(depth + 1)

                    unwrap(i, j, size)
                    paperCount[size] += 1
                }
                //빈 칸을 발견했다면 재귀하므로 반환
                return
            }
        }
    }

    // 덮을 수 있는 곳을 찾지 못했다면 다 채운것이므로 갱신
    if !found {
        answer = min(answer, depth)
    }
}

dfs(0)
answer = answer == Int.max ? possiblePoints.count == 0 ? 0 : -1 : answer

print(answer)