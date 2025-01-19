package 표편집;
// https://blog.encrypted.gg/1001?category=916869
// https://www.youtube.com/watch?v=Dja2THApFoY
/*
* 위 그림에서 파란색으로 칠해진 칸은 현재 선택된 행을 나타냅니다.
* 단, 한 번에 한 행만 선택할 수 있으며, 표의 범위(0행 ~ 마지막 행)를 벗어날 수 없습니다.
* 이때, 다음과 같은 명령어를 이용하여 표를 편집합니다.

"U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
"D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
"C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
"Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
예를 들어 위 표에서 "D 2"를 수행할 경우 아래 그림의 왼쪽처럼 4행이 선택되며, "C"를 수행하면 선택된 행을 삭제하고,
* 바로 아래 행이었던 "네오"가 적힌 행을 선택합니다(4행이 삭제되면서 아래 있던 행들이 하나씩 밀려 올라오고, 수정된 표에서 다시 4행을 선택하는 것과 동일합니다).*/

/*
 * 풀이 방법
 * 삭제된 행 : stack (가장 최근에 삭제된것이 반환 될수 있다)
 * 커서가 돌아다니면서 중간에 삭제를 하는 형태임 => 연결 리스트가 필요함
 * */
import java.util.*;

class Solution {
    // 연결 리스트의 각 노드를 나타내는 클래스
    class Node {
        boolean removed; // node 삭제 여부
        Node prev;
        Node next;
    }

    Node[] NodeArr = new Node[1000000];

    public String solution(int n, int k, String[] cmd) {
        for (int i = 0; i < n; ++i) {
            NodeArr[i] = new Node();
        }

        // 각 노드를 연결하여 이중 연결 리스트 생성
        for (int i = 1; i < n; ++i) {
            NodeArr[i - 1].next = NodeArr[i];
            NodeArr[i].prev = NodeArr[i - 1];
        }

        Node curr = NodeArr[k];
        Stack<Node> stack = new Stack<>();// 삭제된 노드를 저장할 스택

        for (String str : cmd) {
            if (str.charAt(0) == 'U') {
                // 위로 이동
                int x = Integer.parseInt(str.substring(2));
                for (int i = 0; i < x && curr.prev != null; ++i) {
                    // 이전 지점이 NULL인지 확인해야한다.
                    curr = curr.prev;
                }
            } else if (str.charAt(0) == 'D') {
                // 아래로 이동
                int x = Integer.parseInt(str.substring(2));
                for (int i = 0; i < x && curr.next != null; ++i) {
                    // 다음 지점이 NULL인지 확인해야 한다.
                    curr = curr.next;
                }
            } else if (str.charAt(0) == 'C') {
                // 삭제 후 다음 지점
                stack.push(curr);
                curr.removed = true;
                Node up = curr.prev;
                Node down = curr.next;

                if (up != null) up.next = down; // 젤 처음 노드인가?
                if (down != null) { // 젤 마지막 노드인가?
                    down.prev = up; // 다음 노드가 있다면 이전 노드를 연결
                    curr = down; // 다음 노드로 이동
                } else {
                    curr = up; // 마지막 노드이면 이전 노드로 이동
                    if (up != null) up.next = null; // 이전 노드의 끝을 NULL으로 해줌
                }
            } else { // Z
                Node node = stack.pop();
                node.removed = false;
                Node up = node.prev; // 복원할 노드의 이전 노드
                Node down = node.next; // 복원할 노드의 다음 노드

                // 연결 리스트 갱신
                if (up != null) up.next = node; // 이전 노드가 있다면 복원된 노드 연결
                if (down != null) down.prev = node; // 다음 노드가 있다면 복원된 노드 연결
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (NodeArr[i].removed) {
                answer.append('X');
            } else {
                answer.append('O');
            }
        }
        return answer.toString();
    }
}
