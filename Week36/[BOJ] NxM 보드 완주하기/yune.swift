import Foundation

let dr = [1, -1, 0, 0]
let dc = [0, 0, 1, -1]

var board = [[Character]](repeating: [Character](repeating: "#", count: 31), count: 31)
var visited = [[Bool]](repeating: [Bool](repeating: false, count: 31), count: 31)

func check(_ y: Int, _ x: Int, _ n: Int, _ m: Int) -> Bool {
    return (0..<n).contains(y) && (0..<m).contains(x)
}

func solve(_ r: Int, _ c: Int, _ n: Int, _ m: Int, _ numLeft: Int) -> Int {
    if numLeft == 0 { return 0 }
    var result = -1

    for i in 0..<4 {
        var nr = r + dr[i]
        var nc = c + dc[i]
        var moved = false
        var steps: [(Int, Int)] = []

        while check(nr, nc, n, m) && !visited[nr][nc] && board[nr][nc] == "." {
            visited[nr][nc] = true
            steps.append((nr, nc))
            nr += dr[i]
            nc += dc[i]
            moved = true
        }

        nr -= dr[i]
        nc -= dc[i]

        if moved {
            let cand = solve(nr, nc, n, m, numLeft - steps.count)
            if cand != -1 {
                if result == -1 || result > cand + 1 {
                    result = cand + 1
                }
            }
            for (pr, pc) in steps {
                visited[pr][pc] = false
            }
        }
    }

    return result
}

var caseNum = 1

while let line = readLine() {
    let parts = line.split(separator: " ").compactMap { Int($0) }
    guard parts.count == 2 else { break }
    let (n, m) = (parts[0], parts[1])

    var numDots = 0
    
    for i in 0..<n {
        let row = Array(readLine() ?? "")
        board[i] = row
        for j in 0..<m {
            if board[i][j] == "." {
                numDots += 1
            }
        }
    }

    var answer = -1

    for i in 0..<n {
        for j in 0..<m {
            if board[i][j] == "." {
                visited[i][j] = true
                let res = solve(i, j, n, m, numDots - 1)
                if res != -1 {
                    if answer == -1 || answer > res {
                        answer = res
                    }
                }
                visited[i][j] = false
            }
        }
    }

    print("Case \(caseNum): \(answer)")
    caseNum += 1
}