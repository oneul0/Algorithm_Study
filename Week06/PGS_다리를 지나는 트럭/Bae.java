/*
모든 트럭이 다리를 지나는데 최소시간
트럭은 순서대로 -> 순서가 바뀌지 않는다
다리에 있는 트럭의 시간을 계산해야한다.
트럭 개수 <= 10,000 , 다리 길이 <= 10,000 -> O(n)

FIFO -> 큐
현재 큐에 있는 총 무게 <= 다리 한계
다리를 건너는 트럭을 반복문을 통해 길이만큼 지나면 -> pop


*/


import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
//      int[] q -> q 두 개로 트럭 시간 같이 계산해야 시간초과 해결
        Deque<Integer> q = new ArrayDeque<>();
        Deque<Integer> truck_time = new ArrayDeque<>();
        
        int time = 0;
        int index = 0;
        int curr = 0;
        
        // q.add(truck_weights[0]);
        
        while(index < truck_weights.length || !q.isEmpty()) {
            time++;
            
            if(!truck_time.isEmpty() && truck_time.peekFirst() == time) {
                curr -= q.pollFirst();
                truck_time.pollFirst();
            }
            
            if(index < truck_weights.length && curr + truck_weights[index] <= weight) {
                q.add(truck_weights[index]);
                truck_time.add(time+bridge_length);
                curr+= truck_weights[index];
                index++;
                
            }
            
//          q 길이만큼 시간을 따로 더하면 시간 초과
            // for(int[] truck : q) {
            //     truck[1]++;
            // }
        }
        
        return time;
    }
}
