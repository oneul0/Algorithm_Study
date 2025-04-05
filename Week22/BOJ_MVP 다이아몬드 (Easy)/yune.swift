let n = Int(readLine()!)!
let sgpd = readLine()!.split { $0 == " " }.map { Int(String($0))! }
let standard: [Character: Int] = [ "B": sgpd[0]-1,
                 "S": sgpd[1]-1,
                 "G": sgpd[2]-1,
                 "P": sgpd[3]-1,
                 "D": sgpd[3] ]
var spendMoneyList = [Int](repeating: 0, count: n+1)

var currentMonth = 1
readLine()!.forEach { c in
    if c == "D" {
        spendMoneyList[currentMonth] = standard[c]!
        currentMonth += 1
        return
    }
    let spendMoney = standard[c]! - spendMoneyList[currentMonth-1]
    spendMoneyList[currentMonth] = spendMoney
    currentMonth += 1
}
let answer = spendMoneyList.reduce(0,+)
print(answer)