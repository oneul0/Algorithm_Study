import Foundation

final class Queue<T> {
    private var queue = [T?]()
    private var head = 0
    var isEmpty: Bool {
        queue.count - head == 0
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

let nlr = readLine()!.split { $0 == " "  }.map { Int(String($0))! },
n = nlr[0],
l = nlr[1],
r = nlr[2]

func bfs(_ startY: Int, _ startX: Int) -> Int {
    let queue = Queue<(Int, Int)>()
    queue.enqueue((startY, startX))
    isVisited[startY][startX] = true  // 방문처리
    var union = [(Int, Int)]()  // 연합국가 초기화하고, 다시 넣어주는 과정
    union.append((startY, startX))
    var count = 0  // 연합국가의 인구수를 더해주는 변수
    count += graph[startY][startX]
    
    while !queue.isEmpty {
        let yx = queue.dequeue()!
        let y = yx.0
        let x = yx.1
        
        for k in 0..<4 {
            let nx = x + dx[k]
            let ny = y + dy[k]
            
            if nx < 0 || nx >= n || ny < 0 || ny >= n {
                continue
            }
            
            if isVisited[ny][nx] {
                continue
            }
            
            // 조건 충족하면 union 에 넣어주어서 수 세어주기
            if l <= abs(graph[ny][nx] - graph[y][x]), abs(graph[ny][nx] - graph[y][x]) <= r {
                union.append((ny, nx))
                isVisited[ny][nx] = true
                count += graph[ny][nx]
                queue.enqueue((ny, nx))
            }
        }
    }
    // 연합국가들의 인구 수
    let unionPopulation = Int(floor(Double(count) / Double(union.count)))
    for country in union {
        graph[country.0][country.1] = unionPopulation
    }
    return union.count
}

func isAvailable(_ x: Int, _ y: Int) -> Bool {
    if (0..<n).contains(x) && (0..<n).contains(y) { return true }
    return false
}

var graph = (0..<n).map { _ in readLine()!.split { $0 == " " }.map{ Int(String($0))! } }
typealias Pos = (Int, Int)

let dx = [0, 0, -1, 1]
let dy = [-1, 1, 0, 0]

var day = 0
var check = false

var isVisited = [[Bool]](repeating: [Bool](repeating: false, count: n), count: n)

while true {
    isVisited = [[Bool]](repeating: [Bool](repeating: false, count: n), count: n)
    for y in 0..<n {
        for x in 0..<n {
            if !isVisited[y][x] {
                if bfs(y, x) >= 2 {
                    check = true
                }
            }
        }
    }
    if check == false { break }
    day += 1
    check = false
}

print(day)