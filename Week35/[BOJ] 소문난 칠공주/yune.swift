struct Queue<T> {
    private var q = [T?]()
    private var head = 0
    var isEmpty: Bool { q.count - head == 0 }
    
    mutating func enqueue(_ element: T) {
        q.append(element)
    }
    
    mutating func dequeue() -> T? {
        if isEmpty { return nil }
        let ret = q[head]
        q[head] = nil
        head += 1
        return ret
    }
}

struct Point: Hashable {
    let x: Int
    let y: Int
}

let grid = (0..<5).map { _ in readLine()!.map { String($0) }}
var isVisited = [[Bool]](repeating: [Bool](repeating: false, count: 5), count: 5)
var answer = 0
let dr = [0, 0, -1, 1]
let dc = [-1, 1, 0, 0]

func backtracking(_ r: Int, _ c: Int, _ countY: Int = 0, _ depth: Int = 0, _ points: Set<Point> = []) {

    if countY == 4 {
        return
    }
    
    if depth == 7 {
        if countY < 4 {
            if isNearBy(points) {
                answer += 1
            }
        }
        return
    }

    for nextR in r..<5 {
        let c = nextR == r ? c : 0
        
        for nextC in c..<5 {
            if isVisited[nextR][nextC] { continue }
            isVisited[nextR][nextC] = true
            
            let isY = grid[nextR][nextC] == "Y" ? 1: 0

            var points = points
            points.insert(Point(x: nextR, y: nextC))
            
            backtracking(nextR, nextC, countY + isY, depth+1, points)
            isVisited[nextR][nextC] = false
        }
    }
}

func isNearBy(_ points: Set<Point>) -> Bool {
    var points = points
    var queue = Queue<Point>()
    queue.enqueue(points.removeFirst())
    
    while !queue.isEmpty {
        let point = queue.dequeue()!
        for i in 0..<4 {
            let nearPoint = Point(x: point.x + dr[i], y: point.y + dc[i])
            guard points.contains(nearPoint) else { continue }
            points.remove(nearPoint)
            queue.enqueue(nearPoint)
        }
    }
    
    return points.isEmpty
}

backtracking(0, 0)

print(answer)