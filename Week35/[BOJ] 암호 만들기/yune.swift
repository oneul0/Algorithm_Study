let moum: [String.Element] = ["a", "e", "i", "o", "u"]
let jaum: [String.Element] = ["b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "x", "y", "z", "w"]
let lc = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    l = lc[0],
    c = lc[1]
let cList = readLine()!.split { $0 == " " }.map { String($0) }.sorted { $0 < $1 }
var passwordSet = [String]()

func makePassword(_ start: Int, _ count: Int, _ picked: String = "") {
    if count == 0 {
        let password = picked
        let isContainMoum = !moum.map { password.contains($0) }.filter { $0 == true }.isEmpty
        let isContainJaumMoreThanTwo = (jaum.map { password.contains($0) }.filter { $0 == true }.count) > 1
        if isContainMoum && isContainJaumMoreThanTwo {
            passwordSet.append(password)
        }
        return
    }
    for i in start..<cList.count-count+1 {
        makePassword(i+1, count-1, picked+cList[i])
    }
}
makePassword(0, l)
let answer = passwordSet.joined(separator: "\n")
print(answer)