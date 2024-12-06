import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // bridge_length 만큼의 크기를 갖는 큐 생성
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0; //대기중인 트럭의 인덱스

        // 다리를 0으로 초기화(빈 공간)
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (truckIndex < truck_weights.length) {
            // 시간 증가
            time++;

            // 다리에서 나가는 트럭 무게 제거
            currentWeight -= bridge.poll();

            // 새 트럭이 다리에 올라갈 수 있는지 확인
            if (currentWeight + truck_weights[truckIndex] <= weight) {
                bridge.offer(truck_weights[truckIndex]);
                currentWeight += truck_weights[truckIndex];
                truckIndex++;
            } else {
                // 견딜 수 있는 무게보다 커지면 0을 넣음
                bridge.offer(0);
            }
        }

        // 마지막 트럭이 다리를 완전히 건너는 시간 추가
        return time + bridge_length;
    }
}