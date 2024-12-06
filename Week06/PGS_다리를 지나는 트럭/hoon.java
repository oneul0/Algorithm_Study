import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0, bWeight = 0, idx = 0;
        Deque<Truck> br = new ArrayDeque<>();

        while(idx < truck_weights.length || !br.isEmpty()){
            answer++;

            if(!br.isEmpty()){
                Truck cur = br.peek();
                //다리 끝
                if(answer - cur.pos >= bridge_length){
                    bWeight -= cur.w; //다리에서 트럭 무게 제최
                    br.remove(); //다리에서 제거
                }
            }

            //제거할 트럭 제거하고 다리에 올라올 수 있는 트럭이 있는지 확인
            if(idx < truck_weights.length){
                if(bWeight + truck_weights[idx] <= weight && br.size() < bridge_length){
                    br.add(new Truck(truck_weights[idx], answer)); //다리에 트럭 추가
                    bWeight += truck_weights[idx];
                    idx++; //몇 번째 트럭까지 올라왔는지 최신화
                }
            }
        }

        return answer;
    }
}

class Truck {
    int w, pos;
    Truck(int w, int pos){
        this.w = w;
        this.pos = pos;
    }
}