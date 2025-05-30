import Foundation

let n = Int(readLine()!)!
var tree = [String:[String]]()
(0..<n).forEach { _ in
    let parentChilds = readLine()!.split { $0 == " " }.map { String($0) }
    let parent = parentChilds[0]
    let childs = Array(parentChilds[1...])
    tree[parent] = childs
}

func search(_ node: String, _ rootOrder: Int) -> String {
    if node == "." { return "" }
    guard let childsList = tree[node] else { return "" }
    let root = node
    let left = search(childsList[0], rootOrder)
    let right = search(childsList[1], rootOrder)
    if rootOrder == 0 {
        return root + left + right
    } else if rootOrder == 1 {
        return left + root + right
    } else {
        return left + right + root
    }
}
//전위
//중위
//후위
let answer0 = search("A", 0)
let answer1 = search("A", 1)
let answer2 = search("A", 2)
print(answer0)
print(answer1)
print(answer2)