let input = readLine()!.split(separator : " " ).map{Int(String($0))!}

let n = input[0],
    l = Double(input[1])/100,
    r = Double(input[2])/100,
    d = Double(input[3])/100,
    u = Double(input[4])/100

let dict = [0 : l, 1 : r , 2 : d , 3 : u ]
let direction = [(0,1),(0,-1),(-1,0),(1,0)]
let dx = [-1, 1, 0, 0]
let dy = [0, 0, -1, 1]
var answer : Double = 0.0
var visit = [[Bool]](repeating : [Bool](repeating: false, count: 31),count : 31)

func dfs(_ x: Int, _ y: Int, _ value: Double , _ count: Int) {
    if count == n {
        answer += value
        return
    }
    for i in 0..<4 {
        let nx = x + dx[i]
        let ny = y + dy[i]
        if visit[ny][nx] == false {
            visit[ny][nx] = true
            dfs(nx, ny, dict[i]! * value, count + 1)
            visit[ny][nx] = false
        }
    }
}
visit[15][15] = true
dfs(15, 15, 1.0, 0)
print(answer)