import java.util.*;
class Solution {
    static int n, n3;
    public int solution(int coin, int[] cards) {
        n = cards.length;
        n3 = n/3;
        //카드의 숫자는 유일하므로 boolean으로 관리
        boolean hand[] = new boolean[n+1]; //손패에 있는 카드는 true
        boolean paid[] = new boolean[n+1]; //처음 뽑은 카드
        int coinLeft = coin; //코인

        //첫 패 얻기
        for(int i = 0; i<n3; i++){
            hand[cards[i]] = true;
            paid[cards[i]] = true;
        }

        int answer = 1;
        //게임 시작
        for(int i = n3; i<n; i+=2){
            if(coinLeft > 0){
                hand[cards[i]] = true;
                hand[cards[i+1]] = true;
            }

            boolean pass = false; //낼 수 있는 카드 쌍이 있는지
            int minCost = 3; // 가능한 가장 큰 비용은 2이므로 3으로 초기화
            int cardThrown = -1; //버릴 카드 번호
            //비용 산정 및 통과할 수 있는지 체크(1~현재 라운드까지)
            for(int j = 1; j<=n; j++){
                if(!hand[j]) continue; //손패에 없으면 continue

                if(hand[n+1-j]){ //낼 수 있는 비용의 카드인지
                    int cost = (paid[j] ? 0 : 1) + (paid[n+1-j] ? 0:1);
                    if(coinLeft < cost || minCost <= cost) continue;

                    pass = true;
                    cardThrown = j;
                    minCost = cost;
                }
            }

            if(!pass) break; //버릴 카드가 없으면 끝
            hand[cardThrown] = false;
            hand[n+1-cardThrown] = false;
            coinLeft -= minCost;

            answer++;
        }
        return answer;
    }
}