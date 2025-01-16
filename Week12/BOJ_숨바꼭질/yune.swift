final class Queue {
    private var queue = [Int?]()
    var head = 0
    var isEmpty: Bool {
        return queue.count - head == 0
    }
    func enqueue(_ element: Int) {
        queue.append(element)
    }
    func dequeue() -> Int? {
        guard !isEmpty else { return nil }
        let element = queue[head]
        queue[head] = nil
        head += 1
        return element
    }
}

let nk = readLine()!.split { $0 == " " }.map { Int(String($0))! }
let (n, k) = (nk[0], nk[1])
var moved = [Int](repeating: 0, count: 100_001)
func solution1697(n: Int, k: Int) -> Int {
    let queue = Queue()
    queue.enqueue(n)
    while !queue.isEmpty {
        let curPos = queue.dequeue()!
        if curPos == k { return moved[curPos] }
        if curPos + 1 <= 100_000 && moved[curPos + 1] == 0 {
            moved[curPos + 1] = moved[curPos] + 1
            queue.enqueue(curPos + 1)
        }
        if curPos - 1 >= 0 && moved[curPos - 1] == 0 {
            moved[curPos - 1] = moved[curPos] + 1
            queue.enqueue(curPos - 1)
        }
        if curPos * 2 <= 100_000 && moved[curPos * 2] == 0{
            moved[curPos * 2] = moved[curPos] + 1
            queue.enqueue(2*curPos)
        }
    }
    return -1
}

let answer = solution1697(n: n, k: k)
print(answer)