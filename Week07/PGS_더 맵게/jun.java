import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> sco = new PriorityQueue<>();
        for (int s : scoville) {
            sco.add(s);
        }
        int cnt = 0;
        while (sco.size() > 1) {
            int first = sco.remove();
            if (first >= K) {
                break;
            }
            int second = sco.remove();
            int next = first + (second * 2);
            sco.add(next);
            cnt++;
        }
        if (sco.size() == 1 && sco.peek() < K) {
            return -1;
        }
        return cnt;
    }
   
}
