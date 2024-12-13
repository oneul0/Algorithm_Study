
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int cal_scovile(int a, int b){
        int ans =0;
        ans = a +(b*2);
        return ans;
    }

    public int solution(int[] scoville, int K) {
        int answer = 0;


        // PriorityQueue로 Min-Heap 생성
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 배열 요소를 힙에 추가
        for (int num : scoville) {
            minHeap.add(num);
        }


        while(minHeap.peek() < K){

            int a= minHeap.poll();
            if(a<1 || minHeap.isEmpty()){
                // 1보다 작은 값이 들어가면 임계치 이상으로 못만든다
                // minHeap이 비어 있어도 임계치 이상으로 못만든다.
                answer=-1;
                break;
            }

            int b= minHeap.poll();
            int scovile = cal_scovile(a,b);
            minHeap.add(scovile);
            // System.out.println(minHeap);
            answer++;
        }
        return answer;
    }
}
