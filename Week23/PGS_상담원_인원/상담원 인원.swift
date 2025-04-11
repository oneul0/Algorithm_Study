fileprivate func distributeInAdviceKind(_ n: Int, _ k: Int) -> [[Int]] {
    func distributePeople(_ n: Int, _ k: Int, _ startIndex: Int, _ currentDistribution: inout [Int], _ result: inout [[Int]]) {
        if k == 1 {
            currentDistribution[startIndex] = n
            result.append(currentDistribution)
            currentDistribution[startIndex] = 0
            return
        }
        
        for i in 1...(n-k+1) { // n-k+1은 특정 상담 종류에 넣을 수 있는 최대 인원 수
            currentDistribution[startIndex] = i
            distributePeople(n-i, k-1, startIndex+1, &currentDistribution, &result)
        }
        currentDistribution[startIndex] = 0
    }
    var result = [[Int]]()
    var currentDistribution = Array(repeating: 0, count: k)
    distributePeople(n, k, 0, &currentDistribution, &result)
    return result
}

func clacTotalWatingTime(k: Int, mento: [Int], reqs: [[Int]]) -> Int {
    var watingTime = 0
    var heapList = (0..<k).map { _ in [Int]() }
    mento.enumerated().forEach { i, v in
        (0..<v).forEach { _ in heapList[i].append(0)
        }
    }
    reqs.forEach { req in
        let startTime = req[0]
        let duration = req[1]
        let type = req[2]-1
        
        let mentoTime = heapList[type].removeLast()
        
        if mentoTime > startTime {
            let curWatingTime = mentoTime - startTime
            let nextMentoTime = mentoTime + duration
            heapList[type].append(nextMentoTime)
            watingTime += curWatingTime
            
        } else if mentoTime == startTime {
            let nextMentoTime = mentoTime + duration
            heapList[type].append(nextMentoTime)

        } else if mentoTime < startTime {
            let nextMentoTime = startTime + duration
            heapList[type].append(nextMentoTime)
        }
        heapList[type].sort { $0 > $1 }
    }
    
    return watingTime
}

func solution(_ k:Int, _ n:Int, _ reqs:[[Int]]) -> Int {
    // req = [요청시간, 걸리는 시간, 유형]
    // k 유형
    // n 가용 멘토 인원
    let comb = distributeInAdviceKind(n, k)
    let ret = comb.map { clacTotalWatingTime(k: k, mento: $0, reqs: reqs) }.min()!
    
    return ret
}