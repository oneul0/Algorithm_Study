func calcCost(before: Int, after: Int) -> Int {
    if before == 0 {
        return 2
    } else if before == after {
        return 1
    } else if before + 2 == after || before - 2 == after {
        return 4
    } else {
        return 3
    }
}

// calcTotalCost(index, left, right): 현재 두 발의 위치가 left, right일 때 arr[index...]를 모두 밟는 비용
func calcTotalCost(_ stage: Int, left: Int, right: Int) -> Int {
    if stage == n { return 0 }
    
    var ret = dp[stage][left][right]
    if ret != inf { return ret }
    
    let next = cmd[stage]
    if right != next {  // 왼쪽 발을 움직임
        ret = min(ret, calcCost(before: left, after: next) + calcTotalCost(stage+1, left: next, right: right))
    }
    if left != next {   // 오른쪽 발을 움직임
        ret = min(ret, calcCost(before: right, after: next) + calcTotalCost(stage+1, left: left, right: next))
    }
    
    dp[stage][left][right] = ret
    
    return ret
}

let inf = 987654321
var cmd = readLine()!.split(separator: " ").map{ Int(String($0))! }.dropLast()
let n = cmd.count
var dp = [[[Int]]](repeating: [[Int]](repeating: [Int](repeating: inf, count: 5), count: 5), count: n)
var result = inf

if n == 0 {
    result = 0
} else {
    result = 2 + min(calcTotalCost(1, left: cmd[0], right: 0), calcTotalCost(1, left: 0, right: cmd[0]))
}

print(result)