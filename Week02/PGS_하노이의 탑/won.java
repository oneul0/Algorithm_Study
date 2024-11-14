
import java.util.*;

class Solution {
    static List<int[]> tower;
    public int[][] solution(int n) {

        tower = new ArrayList<>();
        hanoi(n, 1, 3, 2);

        int[][] answer = new int[tower.size()][2];
        for (int i = 0; i < tower.size(); i++) {
            answer[i] = tower.get(i);
        }
        return answer;
    }

    // start: 시작점, end: 목표 지점, sub: 보조, cnt: 타워의 개수
    private static void hanoi(int cnt, int start, int end, int sub){
        if(cnt == 1){ // 옮겨야 할 타워가 1개
            tower.add(new int[]{start, end}); // 시작 -> 목표 옮기면 끝
            return;
        }
        hanoi(cnt - 1, start, sub, end); // 시작 -> 보조 옮김
        tower.add(new int[]{start, end});
        hanoi(cnt - 1, sub, end, start); // 보조 -> 목표 옮김
    }


}