import Foundation

class Node {
    var prev: Node? = nil
    var next: Node? = nil
    var isDeleted: Bool = false
}

func solution(_ n:Int, _ k:Int, _ cmd:[String]) -> String {
  /// 참조가 되지 않고 각자 독립적인 노드가 만들어질 수 있도록 유의해야합니다.
  var nodes = (0..<n).map { _ in Node() }
  /// 삭제된 노드는 stack처럼 활용해서 push, pop합니다.
  var removedBuf: [Node] = []
  /// 현재 노드를 통해서, 연결된 노드들의 위치들만 탐색합니다.
  var curNode = nodes[k]
  for i in 1..<n {
    nodes[i-1].next = nodes[i]
    nodes[i].prev = nodes[i-1]
  }
  for command in cmd {
    let splitted = command.split{$0==" "}.map { String($0) }
    switch splitted[0] {
    case "U": /// 위로!
      let x = Int(splitted[1])!
      for _ in 0..<x {
        guard let prev = curNode.prev else { break }
        curNode = prev
      }
    case "D": /// 아래로!
      let x = Int(splitted[1])!
      for _ in 0..<x {
        guard let next = curNode.next else { break }
        curNode = next
      }
    case "C": /// 삭제하기
      curNode.isDeleted = true
      removedBuf.append(curNode)
      curNode.prev?.next = curNode.next
      curNode.next?.prev = curNode.prev
      curNode = curNode.next ?? curNode.prev!
    default: /// 꺼내기!
      let popped = removedBuf.popLast()
      popped?.isDeleted = false
      popped?.next?.prev = popped
      popped?.prev?.next = popped
    }
  }
  return nodes.map { $0.isDeleted ? "X" : "O" }.joined()
}