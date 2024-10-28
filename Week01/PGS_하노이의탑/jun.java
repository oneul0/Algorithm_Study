import java.util.*;
class Solution {
    public List<int[]> arr;
    public int[][] solution(int n) { // 0 < n <= 15
        arr = new ArrayList<>();
        move(n, 1, 3, 2);
        int[][] answer = new int[arr.size()][2];
        for (int i=0; i<arr.size(); i++) {
            answer[i] = arr.get(i);
        }
        return answer;
    }
    public void move(int cnt, int start, int end, int other) {
        if (cnt == 1) {
            arr.add(new int[] {start, end});
        } else {
            move(cnt-1, start, other, end);
            arr.add(new int[] {start, end});
            move(cnt-1, other, end, start);
        }
    }
}
