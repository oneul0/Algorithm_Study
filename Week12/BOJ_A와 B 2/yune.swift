let s = readLine()!.map { String($0) }
let t = readLine()!.map { String($0) }

func solution(str: [String]) -> Bool {
    if str.count == s.count {
        return str == s
    }
    if str.last! == "A" {
        var a = str
        _ = a.popLast()
        if solution(str: a) { return true }
    }
    if str.first! == "B" {
        var b: [String] = str.reversed()
        _=b.popLast()
        if solution(str: b) { return true }
    }
    return false
}

let answer = solution(str: t) ? 1 : 0
print(answer)
