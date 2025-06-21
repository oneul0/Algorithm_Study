import Foundation

let info = readLine()!.split(separator: " ").map({Int(String($0))!})
let count = Int(readLine()!)!
var nums = [[Int]]() 
var town = Array(repeating: 0, count: info[0])
var answer = 0

for _ in 0..<count { nums.append(readLine()!.split(separator: " ").map({Int(String($0))!})) }

nums.sort {
    if $0[1] != $1[1] { return $0[1] < $1[1] }
    else { return $0[0] < $1[0] }
}

for i in 0..<count {
    let max = (town[nums[i][0]-1..<nums[i][1]-1]).max()!
    if max < info[1] {
        let add = nums[i][2] + max > info[1] ? info[1] - max : nums[i][2]
        answer += add
        for j in nums[i][0]-1...nums[i][1]-2 {
            town[j] += add 
        }
    }
}

print(answer)