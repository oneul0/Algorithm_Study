let nm = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nm[0],
    m = nm[1]
var knowPeepList = readLine()!.split { $0 == " " }.dropFirst().map { Int(String($0))! }
let partyList = (0..<m).map { _ in readLine()!.split { $0 == " " }.dropFirst().map { Int(String($0))! } }
var parentInfo = [Int](repeating: 0, count: n+1)

(1...n).forEach { parentInfo[$0] = $0 }

var answer = 0
func find(_ v: Int) -> Int {
    //0의 부모는 0이여서 및 조건문을 만족하지 않아 0을 반환함
    if parentInfo[v] != v {
        parentInfo[v] = find(parentInfo[v])
    }
    return parentInfo[v]
}

func union(_ v: Int, _ g: Int) {
    let v = find(v)
    let g = find(g)
    if v < g {
        parentInfo[g] = v
    } else {
        parentInfo[v] = g
    }
}

knowPeepList.forEach { parentInfo[$0] = 0 }

for i in partyList.indices {
    for j in stride(from: 0, to: partyList[i].count-1, by: 1) {
        union(partyList[i][j], partyList[i][j+1])
    }
}

for i in partyList.indices {
    if let first = partyList[i].first {
        let parent = find(first)
        guard parent != 0 else { continue }
        answer += 1
    }
}

print(answer)