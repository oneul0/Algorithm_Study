struct Point {
    let r: Int
    let c: Int
}
enum Direction {
    case up
    case down
    case left
    case right
}
let dr = [0, 0, -1, 1]
let dc = [-1, 1, 0, 0]
let dirs: [Direction] = [.up, .down, .left, .right]
var answer = ""

while let input = readLine() {
    func printVisited() {
        var state = ""
        for line in isVisited {
            state.append(line.map { $0 ? "*" : "." }.joined() + "\n")
        }
        print(state)
    }
    func canMove(_ r: Int, _ c: Int) -> Bool {
        for i in 0..<4 {
            let nextR = r + dr[i]
            let nextC = c + dc[i]
            guard (0..<n) ~= nextR, (0..<m) ~= nextC else { continue }
            guard grid[nextR][nextC] != "*" else { continue }
            if isVisited[nextR][nextC] { continue }
            return true
        }
        return false
    }
    func isAllVisited(_ r: Int, _ c: Int) -> Bool {
        isVisited[r][c] = true
        for i in 0..<n {
            for j in 0..<m {
                if grid[i][j] == "*" { continue }
                if !isVisited[i][j] { return false }
            }
        }
        isVisited[r][c] = false
        return true
    }

    func backtracking(_ r: Int, _ c: Int, _ direction: Direction, _ count: Int) {
        if count >= currentAnswer { return }
        var nextR = r
        var nextC = c
        var prevPaths = [(Int, Int)]()

        switch direction {
        case .up:
            while (0..<n) ~= nextR - 1, (0..<m) ~= nextC, grid[nextR - 1][nextC] != "*",
                !isVisited[nextR - 1][nextC]
            {
                prevPaths.append((nextR, nextC))
                isVisited[nextR][nextC] = true
                nextR -= 1
            }
        case .down:
            while (0..<n) ~= nextR + 1, (0..<m) ~= nextC, grid[nextR + 1][nextC] != "*",
                !isVisited[nextR + 1][nextC]
            {
                prevPaths.append((nextR, nextC))
                isVisited[nextR][nextC] = true
                nextR += 1
            }
        case .left:
            while (0..<n) ~= nextR, (0..<m) ~= nextC - 1, grid[nextR][nextC - 1] != "*",
                !isVisited[nextR][nextC - 1]
            {
                prevPaths.append((nextR, nextC))
                isVisited[nextR][nextC] = true
                nextC -= 1
            }
        case .right:
            while (0..<n) ~= nextR, (0..<m) ~= nextC + 1, grid[nextR][nextC + 1] != "*",
                !isVisited[nextR][nextC + 1]
            {
                prevPaths.append((nextR, nextC))
                isVisited[nextR][nextC] = true
                nextC += 1
            }
        }

        if nextR == r, nextC == c {
            if !canMove(r, c) {
                //
                //                printVisited()
                //                print()

                if isAllVisited(nextR, nextC) {

                    if count < currentAnswer {
                        //                        printVisited()
                        //                        print()
                        //                        print(count)
                        currentAnswer = count
                    }
                }
            }
        } else {
            for direction in dirs {
                backtracking(nextR, nextC, direction, count + 1)
            }
        }
        for (r, c) in prevPaths {
            isVisited[r][c] = false
        }
    }

    let nm = input.split { $0 == " " }.map { Int(String($0))! },
        n = nm[0],
        m = nm[1]

    var grid = [[String]]()
    var isVisited = [[Bool]](repeating: [Bool](repeating: false, count: m), count: n)
    var currentAnswer = Int.max

    for _ in 0..<n {
        let line = readLine()!.map { String($0) }
        grid.append(line)
    }

    for r in 0..<n {
        for c in 0..<m {
            if grid[r][c] == "*" { continue }
            for direction in dirs {
                backtracking(r, c, direction, 1)
            }
        }
    }
    answer.write("\(currentAnswer)\n")
}
print(answer)