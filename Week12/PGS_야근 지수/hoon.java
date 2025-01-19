import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works) pq.offer(w);
        while(n>0 && !pq.isEmpty()){
            int tmp = pq.remove();
            --tmp;
            if(tmp > 0) pq.offer(tmp);
            n--;
        }

        while(!pq.isEmpty()){
            int t = pq.remove();
            answer += (t*t);
        }
        return answer;
    }
}