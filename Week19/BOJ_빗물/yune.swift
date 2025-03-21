let hw = readLine()!.split { $0 == " " }.map{ Int(String($0))! },
    h = hw[0],
    w = hw[1]
let stickList = readLine()!.split { $0 == " " }.map { Int(String($0))! }
var grid = [[Int]](repeating: [Int](repeating: 1, count: w), count: h)

for x in 0..<w {
    for y in 0..<stickList[x] {
        grid[y][x] = 0
    }
}

for y in 0..<h {
    for x in 0..<w {
        if grid[y][x] == 0 { break }
        grid[y][x] = -1
    }
}

for y in stride(from: h-1, through: 0, by: -1) {
    for x in stride(from: w-1, through: 0, by: -1) {
        if grid[y][x] == 0 { break }
        grid[y][x] = -1
    }
}

let answer = grid.reduce(0) { $0 + $1.filter { $0 == 1 }.count }
print(answer)