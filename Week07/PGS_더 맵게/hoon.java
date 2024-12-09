import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<scoville.length; i++){
            pq.offer(scoville[i]);
        }

        while(pq.peek()<K && pq.size() > 1){
            answer++;
            int f1 = pq.remove(); //가장 안 매운 음식
            int f2 = pq.remove(); //두번째로 안 매운 음식
            pq.offer(f1+(f2*2));
        }

        if(pq.size() <= 1 && pq.peek() < K){
            return -1;
        }

        return answer;
    }
}