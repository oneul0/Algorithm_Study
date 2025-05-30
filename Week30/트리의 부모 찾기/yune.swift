// 루트 없는 트리가 주어진다. 
// 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
// 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 
// 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
import Foundation

let n = Int(readLine()!)!
var parent = [Int](repeating: 0, count: n+1)
var tree = [[Int]](repeating: [Int](), count: n+1)
var queue = [1]

for _ in 0..<n-1 {
    let gv = readLine()!.split { $0 == " " }.map { Int(String($0))! },
        g = gv[0],
        v = gv[1]
    tree[g].append(v)
    tree[v].append(g)
}
var index = 0
while queue.count > index {
    let currentNode = queue[index]
    for node in tree[currentNode] {
        guard parent[node] == 0 else { continue }
        queue.append(node)
        parent[node] = currentNode
    }
    index += 1
}

print(parent[2...].map { "\($0)\n"}.joined())