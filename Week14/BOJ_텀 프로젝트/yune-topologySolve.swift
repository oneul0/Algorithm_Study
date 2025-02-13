class Queue {
    var queue = [Int?]()
    var head = 0
    var isEmpty: Bool {
        queue.count-head == 0
    }
    var front: Int? {
        isEmpty ? nil : queue[head]
    }
    func enqueue(_ element: Int) {
        queue.append(element)
    }
    func dequeue() -> Int? {
        if isEmpty { return nil }
        let ret = queue[head]
        queue[head] = nil
        head += 1
        return ret
    }
}
let t = Int(readLine()!)!
func topologySort(_ vertexList: [Int], _ inDegreeList: inout [Int]) -> Int {
    var acyclicVertexCount = 0
    let queue = Queue()
    for i in inDegreeList.indices {
        guard inDegreeList[i] == 0 else { continue }
        queue.enqueue(i)
    }
    while !queue.isEmpty {
        let cur = queue.dequeue()!
        let next = vertexList[cur]
        inDegreeList[next] -= 1
        if inDegreeList[next] == 0 {
            queue.enqueue(next)
        }
        acyclicVertexCount += 1
    }
    return acyclicVertexCount
}
var totalAnswer = ""
(0..<t).forEach { _ in
    let n = Int(readLine()!)!
    var inDegree = [Int](repeating: 0, count: n)
    var vertexList = [Int](repeating: 0, count: n)
    readLine()!.split { $0 == " " }.map { Int(String($0))! - 1 }.enumerated().forEach { i, value in
        vertexList[i] = value
        inDegree[value] += 1
    }
    let answer = topologySort(vertexList, &inDegree)
    totalAnswer.write("\(answer)\n")
}
print(totalAnswer)