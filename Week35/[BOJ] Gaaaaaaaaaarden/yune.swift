let inputs = readLine()!.split(separator: " ").compactMap { Int($0) }
let n = inputs[0] // 행의 개수
let m = inputs[1] // 열의 개수
let g = inputs[2] // 초록색 배양액 개수
let r = inputs[3] // 빨간색 배양액 개수
var board = Array(repeating: Array(repeating: "", count: m), count: n)
var sprinkable: [(Int, Int)] = []
var queue: [(Int, Int)] = []
var vist: [[(String, Int)]] = []

for i in 0..<n {
    let input = readLine()!.split(separator: " ").map { String($0) }
    for j in 0..<m {
        if input[j] == "2" { sprinkable.append((i,j)) }
        board[i][j] = input[j]
    }
}

var rCombi = Array(repeating: (-1,-1), count: r)
var gCombi = Array(repeating: (-1,-1), count: g)
var isUsed = Array(repeating: false, count: sprinkable.count)
var result = 0

recursiveR(0, index: 0)
print(result)

// 1. 배양액 뿌릴 수 있는 좌표 탐색 - 빨간색
func recursiveR(_ x: Int, index: Int) {
    if x == r {
        recursiveG(0, index: 0)
        return
    }

    for i in index..<sprinkable.count {
        if !isUsed[i] {
            rCombi[x] = sprinkable[i]
            isUsed[i] = true
            recursiveR(x+1, index: i+1)
            isUsed[i] = false
        }
    }
}

// 1. 배양액 뿌릴 수 있는 좌표 탐색 - 초록색
func recursiveG(_ x: Int, index: Int) {
    if x == g {
        sprinkle(rCombi: rCombi, gCombi: gCombi)
        result = max(result, bfs())
        return
    }

    for i in index..<sprinkable.count {
        if !isUsed[i] {
            gCombi[x] = sprinkable[i]
            isUsed[i] = true
            recursiveG(x+1, index: i+1)
            isUsed[i] = false
        }
    }
}

// 2. 해당 좌표에 배양액 뿌리기
func sprinkle(rCombi: [(Int, Int)], gCombi: [(Int, Int)]) {
    vist = Array(repeating: Array(repeating: ("", 0), count: m), count: n)
    queue = []

    for i in 0..<rCombi.count {
        vist[rCombi[i].0][rCombi[i].1].0 = "R"
        vist[rCombi[i].0][rCombi[i].1].1 = 1
        queue.append(rCombi[i])
    }

    for i in 0..<gCombi.count {
        vist[gCombi[i].0][gCombi[i].1].0 = "G"
        vist[gCombi[i].0][gCombi[i].1].1 = 1
        queue.append(gCombi[i])
    }
}

// 3. 인접한 곳으로 배양액 퍼트리기 -> bfs
func bfs() -> Int {
    let dx = [-1, 1, 0, 0]
    let dy = [0, 0, -1, 1]
    var pointer = 0
    var count = 0

    while pointer < queue.count {
        let cur = queue[pointer]
        pointer += 1

        if vist[cur.0][cur.1].0 == "F" { continue }

        for i in 0..<4 {
            let curColor = vist[cur.0][cur.1].0
            let nx = cur.0 + dx[i]
            let ny = cur.1 + dy[i]

            if !(0..<n ~= nx) || !(0..<m ~= ny) { continue }
            if board[nx][ny] == "0" || vist[nx][ny].0 == "F" { continue }

            if vist[nx][ny].0 == "" {
                vist[nx][ny] = (curColor, vist[cur.0][cur.1].1 + 1)
                queue.append((nx, ny))
            } else if vist[nx][ny].0 == "R" || vist[nx][ny].0 == "G" {
                if curColor != vist[nx][ny].0 && (vist[nx][ny].1 == vist[cur.0][cur.1].1 + 1) {
                    count += 1
                    vist[nx][ny].0 = "F"
                }
            }
        }
    }
    return count
}