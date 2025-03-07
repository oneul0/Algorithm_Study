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
struct Position {
    let x: Int
    let y: Int
    init(x: Int, y: Int) {
        self.x = x
        self.y = y
    }
}
func bfs(_ connectedInfo: [[Int]]) -> String {
    let queue = Queue<Int>()
    var answer = "sad"
    var isVisited = [Bool](repeating: false, count: connectedInfo.count)
    queue.enqueue(0)
    while !queue.isEmpty {
        let current = queue.dequeue()!
        if current == connectedInfo.count - 1 {
            answer = "happy"
            break
        }
        isVisited[current] = true
        connectedInfo[current].filter { !isVisited[$0] }.forEach { queue.enqueue($0) }
    }
    return answer
}
func abs(_ a: Int) -> Int {
    return a > 0 ? a : -a
}
//let io = FileIO()
let t = Int(readLine()!)!
var totalAnswer = ""
(0..<t).forEach { _ in
    let gsCount = Int(readLine()!)!
    let homeXY = readLine()!.split { $0 == " " }.map { Int(String($0))! }
    let homePos = Position(x: homeXY[0], y: homeXY[1])
    let gsPosList: [Position] = (0..<gsCount).map { _ in
        let xy = readLine()!.split { $0 == " " }.map { Int(String($0))! }
        return Position(x: xy[0], y: xy[1])
    }
    let festivalXY = readLine()!.split { $0 == " " }.map { Int(String($0))! }
    let festivalPos = Position(x: festivalXY[0], y: festivalXY[1])
    let vertexList = [homePos] + gsPosList + [festivalPos]
    var connectedInfo = [[Int]](repeating: [Int](), count: vertexList.count)
    for i in (0..<vertexList.count) {
        for j in (i+1..<vertexList.count) {
            let distenceX = abs(vertexList[i].x - vertexList[j].x)
            let distenceY = abs(vertexList[i].y - vertexList[j].y)
            guard distenceX + distenceY <= 1000 else { continue }
            connectedInfo[i].append(j)
            connectedInfo[j].append(i)
        }
    }
    let answer = bfs(connectedInfo)
    totalAnswer.write(answer+"\n")
}
print(totalAnswer)