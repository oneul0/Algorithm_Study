package PGS_다리를지나는트럭;

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // bridge_length: 최대 다리 개수
        // weight: 무게 임계치
        // 트럭이 1개 있어도 다리의 크기 만큼 지나가야 다리를 전부 지나갈 수 있음
        // 예를 들어 무게가 7인 트럭 최대 다리 개수가 10인 다리라면 트럭이 1개이더라도 다리 개수인 10을 전부 지나가야함
        // 그럼 총 10초 걸림

        int answer = 0; // 경과시간
        int current_truck_weight = 0;
        Queue<Integer> bridge = new LinkedList<>();

        // 다리 위의 트럭 수만큼 큐에 0을 미리 채워넣어 놓습니다.
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0); // 다리의 공간을 0으로 채워놓기
        }

        for (int truck : truck_weights) {
            // 트럭이 다리 위로 올라가려면
            while (true) {
                // 다리에서 트럭이 빠져나가면
                int removedTruck = bridge.poll();
                current_truck_weight -= removedTruck;

                // 새로운 트럭이 올라갈 수 있으면
                if (current_truck_weight + truck <= weight) {
                    bridge.offer(truck); // 트럭을 다리에 올린다
                    current_truck_weight += truck; // 다리 위의 무게 갱신
                    answer++; // 시간이 1초 흐른다
                    break;
                } else {
                    // 올라갈 수 없으면 대기
                    bridge.offer(0); // 대기 중인 트럭을 다리에 추가
                    answer++; // 시간이 1초 흐른다

                }
            }
        }

        // 모든 트럭이 다리를 건넜을 때 남은 시간은 다리 길이만큼
        return answer + bridge_length;
    }

}