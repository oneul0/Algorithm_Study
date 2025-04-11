let nm = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nm[0],
    m = nm[1]
// 첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.
// 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다.
// 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
let grid = (0..<n).map { _ in readLine()!.split { $0 == " " }.map { Int(String($0))! }} //[[Int]]
// 치킨거리 = |x2 - x1| + |y2 - y1|
// 치킨집 중 최대 m개만 남겼을 때, 도시의 치킨 거리의 합을 최소로 하시오.
// 각 집에서 가장 가까운 치킨집까지의 거리가 치킨 거리.
// 모든 치킨 거리의 합이 도시의 치킨 거리
// 치킨집을 하나씩 선택해서, k개까지 선택했을 때의 모든 경우 까지 최소 도시 치킨 거리를 갱신한다.

struct Point {
    init(_ y: Int, _ x: Int) {
        self.y = y
        self.x = x
    }
    let y: Int
    let x: Int
    
    static func - (lhs: Point, rhs: Point) -> Int {
        abs(lhs.x - rhs.x) + abs(lhs.y - rhs.y)
    }
}

var placeList = [Point]()
var homeList = [Point]()
// 치킨집의 위치를 등록한다.
for y in 0..<n {
    for x in 0..<n {
        if grid[y][x] == 2 {
            placeList.append(Point(y, x))
            continue
        }
        if grid[y][x] == 1 {
            homeList.append(Point(y, x))
            continue
        }
    }
}

var minTotalDistance = Int.max
// 1...m까지 치킨집을 뽑고, minTotalDistance를 계속해서 갱신한다.
var selected = [Bool](repeating: false, count: placeList.count)


func selectChickens(_ idx: Int, _ selectedCount: Int) {
    if selectedCount == nm[1] {
        // 최소값
        minTotalDistance = min(minTotalDistance, clacDistance())
        return
    }
    if idx >= placeList.count { return }
    selected[idx] = true
    selectChickens(idx+1, selectedCount+1)
    selected[idx] = false
    selectChickens(idx+1, selectedCount)
}

func clacDistance() -> Int {
    var totalDist = 0
    for home in homeList {
        var minDist = Int.max
        for i in placeList.indices {
            guard selected[i] else { continue }
            let dist = placeList[i] - home
            minDist = dist < minDist ? dist : minDist
        }
        totalDist += minDist
    }
    return totalDist
}

selectChickens(0,0)

print(minTotalDistance)