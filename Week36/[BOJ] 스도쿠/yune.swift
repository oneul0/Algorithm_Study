struct Point {
    let r: Int
    let c: Int
}

var grid = [[Int]]()
var rowSet = [Set<Int>]()
var colSet = [Set<Int>](repeating: Set<Int>(), count: 9)

var emptyPoints = [Point]()

for i in 0..<9 {
    let line = readLine()!.map { Int(String($0))! }

    for j in line.indices {
        if line[j] == 0 { emptyPoints.append(Point(r: i, c: j)) }
    }
    grid.append(line)
    rowSet.append(Set(line))
}

for i in 0..<9 {
    for j: Int in 0..<9 {
        colSet[j].insert(grid[i][j])
    }
}

var isVisited = [Bool](repeating: false, count: emptyPoints.count)

func isUniqueInSetcion(_ r: Int, _ c: Int, _ v: Int) -> Bool {
    let sr = r / 3
    let sc = c / 3

    for i in sr*3..<sr*3+3 {
        for j in sc*3..<sc*3+3 {
            if grid[i][j] == v {
                return false
            }
        }
    }
    return true
}

func backtracking(_ depth: Int) -> Bool {
    if depth == emptyPoints.count {
        return true
    }
    
    let point = emptyPoints[depth]
    
    for number in 1...9 {
        if rowSet[point.r].contains(number) { continue }
        if colSet[point.c].contains(number) { continue }
        if !isUniqueInSetcion(point.r, point.c, number) { continue }
        
        rowSet[point.r].insert(number)
        colSet[point.c].insert(number)
        grid[point.r][point.c] = number
        
        if backtracking(depth + 1) { return true }
        
        grid[point.r][point.c] = 0
        rowSet[point.r].remove(number)
        colSet[point.c].remove(number)
    }
    return false
}

_=backtracking(0)

var answer = ""

for r in 0..<9 {
    for c in 0..<9 {
        answer.write("\(grid[r][c])")
    }
    answer.write("\n")
}

print(answer)