import Foundation

let input = readLine()!.components(separatedBy:" ").map{Int($0)!}
let N = input[0], K = input[1]
let usageList = readLine()!.components(separatedBy:" ").map{Int($0)!}

var keep = [Int]()
var cnt = 0
for (i, plug) in usageList.enumerated() {
    if keep.contains(plug) {
        continue
    } else if keep.count < N {
        keep.append(plug)
    } else {
        var temp = Set(keep)
        for j in i+1..<K {
            if temp.count == 1 { break }
            if temp.contains(usageList[j]) {
                temp.remove(usageList[j])
            }
        }
        keep.remove(at: keep.firstIndex(of: temp.first!)!)
        keep.append(usageList[i])
        cnt += 1
    }
}
print(cnt)