func isMovable(_ y: Int, _ x: Int) -> Bool {
    guard x > -1, x < wh[0], y > -1, y < wh[1] else { return false }
    return map[y][x] > -1
}

func bfs(_ x: Int, _ y: Int, _ k: Int) -> Int {
    typealias Next = (Int, Int, Int, Int)
    let dx = [0, 0, -1, 1]
    let dy = [-1, 1, 0, 0]
    let dHorseX = [-2, -1, 1, 2, 2, 1, -1, -2]
    let dHorseY = [-1, -2, -2, -1, 1, 2, 2, 1]
    var leftJumpCount = [[Int]](repeating: [Int](repeating: -1, count: wh[0]), count: wh[1])
    leftJumpCount[0][0] = k
    let first = (y, x, 0)
    var queue = [first], q = 0
    var result = -1
    while queue.count > q {
        let current = queue[q]
        q += 1
        if current.0 == wh[1]-1 && current.1 == wh[0]-1 {
            result = current.2
            break
        }
        (0..<4).forEach { i in
            let next = (current.0 + dy[i], current.1 + dx[i], current.2 + 1)
            guard isMovable(next.0, next.1) else { return }
            guard leftJumpCount[next.0][next.1] < leftJumpCount[current.0][current.1] else { return }
            queue.append(next)
            leftJumpCount[next.0][next.1] = leftJumpCount[current.0][current.1]
        }
        guard leftJumpCount[current.0][current.1] > 0 else { continue }
        (0..<8).forEach { i in
            let next = (current.0 + dHorseY[i], current.1 + dHorseX[i], current.2 + 1)
            guard isMovable(next.0, next.1) else { return }
            guard leftJumpCount[next.0][next.1] < leftJumpCount[current.0][current.1] - 1 else { return }
            queue.append(next)
            leftJumpCount[next.0][next.1] = leftJumpCount[current.0][current.1] - 1
        }
    }
    return result
}
let k = Int(readLine()!)!
let wh = readLine()!.split { $0 == " " }.map { Int(String($0))! }
var map = (0..<wh[1]).map { _ in  readLine()!.split { $0 == " " }.map { $0 == "0" ? 0 : -1 }}
let answer = bfs(0, 0, k)
print(answer == Int.max ? -1 : answer)