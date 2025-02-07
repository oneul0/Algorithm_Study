let t = Int(readLine()!)!

func findCycle( _ start: Int, _ cur: Int, _ studentList: inout [Int], _ cnt: Int, _ isVisited: inout [Int], _ startVertex: inout [Int]) -> Int {
    if isVisited[cur] != 0 {
        guard startVertex[cur] == start else { return 0 }
        return cnt - isVisited[cur]
    }
    let next = studentList[cur]
    isVisited[cur] = cnt
    startVertex[cur] = start
    return findCycle(start, next, &studentList, cnt+1, &isVisited, &startVertex)
}

(0..<t).forEach { _ in
    let n = Int(readLine()!)!
    var answer = n
    var studentList = readLine()!.split { $0 == " " }.map { Int(String($0))! - 1 }
    var isVisited = [Int](repeating: 0, count: n)
    var startVertex = [Int](repeating: 0, count: n)
    for index in studentList.indices where isVisited[index] == 0 {
        let coupledCount = findCycle(index, index, &studentList, 1, &isVisited, &startVertex)
        answer -= coupledCount
    }
    print(answer)
}