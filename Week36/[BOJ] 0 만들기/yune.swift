let operaters = [" ", "+", "-",]

let t = Int(readLine()!)!

var answer = ""

func Backtracking(until n: Int, _ depth: Int = 1, _ ops: [String] = []) {
    if depth == n {
        var stack = [1]
        var current = 2
        var result = "\(1)"
        
        for i in ops.indices {
            switch ops[i] {

            case "+":
                stack.append(current)
                result.write("+\(current)")
            case "-":
                stack.append(current)
                result.write("-\(current)")
            default:
                let next = stack.removeLast() * 10 + current
                stack.append(next)
                result.write(" \(current)")
            }
            current += 1
        }
        current = stack.removeFirst()
        var i = 0
        for op in ops {
            if op == " " { continue }
            if op == "+" { current += stack[i] }
            if op == "-" { current -= stack[i] }
            i += 1
        }
        if current == 0 { answer.write("\(result)\n")}
        
        return
    }
    for op in operaters {
        Backtracking(until: n, depth + 1, ops + [op])
    }
}

for _ in 0..<t {
    let n = Int(readLine()!)!
    Backtracking(until: n)
    answer.write("\n")
}

print(answer)