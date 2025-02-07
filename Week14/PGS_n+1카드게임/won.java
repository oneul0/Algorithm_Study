import java.util.*;

/*
그리디 시뮬레이션 구현
* 게임 방식
* 처음에 카드 뭉치에서 카드 n/3장을 뽑아 모두 가집니다. (n은 6의 배수입니다.) 당신은 카드와 교환 가능한 동전 coin개를 가지고 있습니다.
*
* 게임은 1라운드부터 시작되며, 각 라운드가 시작할 때 카드를 두 장 뽑습니다. 카드 뭉치에 남은 카드가 없다면 게임을 종료합니다.
* 뽑은 카드는 카드 한 장당 동전 하나를 소모해 가지거나, 동전을 소모하지 않고 버릴 수 있습니다.
*
* 카드에 적힌 수의 합이 n+1이 되도록 카드 두 장을 내고 다음 라운드로 진행할 수 있습니다.
* 만약 카드 두 장을 낼 수 없다면 게임을 종료합니다.
* */

/*
 * idea
 * 1. 처음 시작하는 카드 뭉치에서 N+1을 만든다.
 * 2. 1에서 불가능하다면, 추가적으로 뽑은 카드 중 1개를 이용해서 N+1을 만든다
 * 3. 2에서 불가능하다면, 추가적으로 뽑은 카드 2개를 이용해서 N+1을 만든다.
 * 4. 3에서 불가능하다면, 다음 라운드로의 진행이 불가능하다.
 * */

class Solution {
    Set<Integer> original, additional;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int len = cards.length;
        original = new HashSet<>();
        additional = new HashSet<>();

        int idx = len / 3; // idx: 초기 카드 개수
        for (int i = 0; i < len; i++) {
            original.add(cards[i]);
        }

        int target = len +1;

        while(true) {
            answer++;
            if(idx >= len) break;
            additional.add(cards[idx]);
            additional.add(cards[idx+1]);
            idx += 2;

            boolean flag = false;

            // 1) 최초 카드에서 해결 가능한지 확인
            for(int i : original){
                if(original.contains(target - i)){
                    original.remove(i);
                    original.remove(target - i);
                    flag = true;
                    break;
                }
            }

            // 2) 최초 카드에서 해결이 안되었다면
            if(!flag) {
                // 최초 카드와 라운드 추가 카드 1장을 이용해서 해결할 수 있는지 확인
                if(coin >0){ // 최소 1개 이상의 코인이 있어야 추가 카드를 받아서 사용할 수 있다.
                    for(int i : original){
                        if(additional.contains(target - i)){
                            original.remove(i);
                            additional.remove(target - i);
                            --coin;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            // 3) 그래도 해결이 안되었다면, 추가 카드들 간에 해결이 가능한 지 확인
            if(!flag){
                if(coin > 1){
                    // 최소 2개 이상의 코인이 있어야 추가 카드를 중에서 해결이 가능하다.
                    for(int i : additional){
                        if(additional.contains(target - i)){
                            additional.remove(i);
                            additional.remove(target - i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            // 완성되지 않았다.
            if(!flag)
                break;
        }

        return answer;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int coin= 4;
        int[] cards= {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};
        System.out.println("답: " + solution.solution(coin, cards));

    }
}