import Foundation

enum Direction {
    case U
    case D
    case L
    case R
}

class Queue<T> {
    var queue = [T?]()
    var head = 0
    var isEmpty: Bool {
        queue.count - head == 0
    }
    var front: T? {
        isEmpty ? nil : queue[head]
    }
    func enqueue(_ element: T) {
        queue.append(element)
    }
    func dequeue() -> T? {
        if isEmpty { return nil }
        let ret = queue[head]
        queue[head] = nil
        head += 1
        return ret
    }
}

func calcCost(_ currentDirection: Direction, _ nextDirection: Direction, _ currentCost: Int) -> Int {
    if [.L, .R].contains(currentDirection) && [.U, .D].contains(nextDirection) {
        return currentCost + 600
    }
    if [.U, .D].contains(currentDirection) && [.L, .R].contains(nextDirection){
        return currentCost + 600
    }
    return currentCost + 100
}

func BFS(_ board:[[Int]], _ direction: Direction) -> Int {
    typealias PosCost = (Int, Int, Int, Direction) // y, x, preCost, direction
    
    let dx = [0, 0, -1, 1]
    let dy = [-1, 1, 0, 0]
    let dir: [Direction] = [.U, .D, .L, .R]
    
    let queue = Queue<PosCost>()
    let start = (0, 0, 0, direction)
    
    var visitCost = [[Int]](repeating: [Int](repeating: Int.max, count: board.count), count: board.count)
    var answer = Int.max
    
    queue.enqueue(start)
    
    while !queue.isEmpty {
        let cur = queue.dequeue()!
        if cur.0 == board.count - 1 && cur.1 == board.count - 1 {
            answer = min(answer, cur.2)
            continue
        }
        (0..<4).forEach { i in
            let nextDirection = dir[i]
            let cost = calcCost(cur.3, nextDirection, cur.2)
            let next = (cur.0 + dy[i], cur.1 + dx[i], cost, nextDirection)
            guard next.0 < board.count && next.0 > -1 && next.1 < board.count && next.1 > -1 else { return }
            guard board[next.0][next.1] == 0 else { return }
            guard visitCost[next.0][next.1] > cost else { return }
            // 현재에 저장하지 않고 다음에 저장하는 이유는
            // 지금까지의 경로는 직선도로 n개 + 코너 m개를 저장한 후
            // 다른 경로와 이 경로를 비교할 때 그 경로까지의 직선도로와 코너의 수를
            // 비교해도 무방하기 때문이다.
            // 다음 칸에 저장하지만, 지금까지의 정보가 들어있는 셈
            // 마지막 칸은 도로를 건설하지 않기 때문에 가능하기도 하다.
            visitCost[next.0][next.1] = cost
            queue.enqueue(next)
        }
    }
    return answer
}

func solution(_ board:[[Int]]) -> Int {
    let answer = min(BFS(board, .L), BFS(board, .D))
    return answer
}
