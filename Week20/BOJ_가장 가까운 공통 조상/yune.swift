let t = Int(readLine()!)!
var answer = ""
(0..<t).forEach { _ in
    let n = Int(readLine()!)!
    var anc = 0
    var isVisited = [Bool](repeating: false, count: n+1)
    var parentInfo = [Int](repeating: 0, count: n+1)
                 (0..<n-1).forEach { _ in
                     //부모, 자식
                     let vg = readLine()!.split { $0 == " " }.map { Int(String($0))! }
                     parentInfo[vg[1]] = vg[0]
                 }
                 let ab = readLine()!.split { $0 == " " }.map { Int(String($0))! },
                     a = ab[0],
                     b = ab[1]
                 var current = a
                 while current != 0 {
                     isVisited[current] = true
                     current = parentInfo[current]
                 }
                 current = b
                 while true {
                     if isVisited[current] {
                         anc = current
                         break
                     }
                     isVisited[current] = true
                     current = parentInfo[current]
                 }
                 answer.write("\(anc)\n")
}
print(answer)