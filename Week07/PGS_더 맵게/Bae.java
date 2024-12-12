/*
가장 낮은 스코빌 + (두번째 낮은 스코빌 *2)
우선순위 큐를 사용
1. 먼저 큐에서 1개 꺼내서 k보다 큰가?
1-1 두 번째를 확인하지 않고 꺼내서 일단 합친다
1-2 두 번째를 확인하고 꺼낸다 -> 확인하지 않아도 가장 낮은 원소가 k보다 크면 우선순위 큐에 의해서
    결국 모든 원소가 다 k보다 크다
    
scoville < 10^6 -> 결국 우선순위 큐에 의해서 마지막만 확인해서 큰 상관없지만
우선순위 큐는 결국 메서드로 구현이 된 것이라 실제 작동에서 시간초과 고려
*/

/*
1차 제출
4개에서 런타임 에러 -> 모든 음식의 스코빌 지수 k이상 불가능 일시 -> -1 조건 추가해야한다
count를 통해 scoville.length만큼 돌아도 if문 안으로 들어가면 -> -1로 취급 
그러면 scoville.length만큼 돌아야하는데 시간적으로 문제가 되지는 않을까 ?
테스트 케이스 [0,0,0,0,0] k=3, return = -1 추가 -> nosuchelementexception
count로 측정하기 보다는 q의 원소가 계속 합쳐지면서 큐가 줄어든다
-> 큐가 다 비게되면 조건에 만족하는 경우가 없다.
*/

/*
2번 제출
q.size()>1로 해서 2개까지 하니 틀린다
아마 q에 한 개만 들어도 그게 k보다 큰 조건이 있다고 판단
q.remove -> peek로 확인하고 작다면 둘 다 한번에 꺼내서 합친다 -> 1개인 경우를 예외 케이스로 처리



*/


import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        Queue<Integer> q = new PriorityQueue<>();
        
        for(int temp : scoville ) {
            q.add(temp);
        }
        
        int answer = 0;
        
        while(true) {
            if(q.size()>0) {
                int min = q.peek();
                if(min < K) {
                    if(q.size() == 1) {
                        return -1;
                    }
                    int food = q.remove();
                    int food2 =q.remove();
                    int newfood = food+(food2 * 2);
                    q.add(newfood);
                    answer++;
                } else {
                    break;
                } 
            } else {
                return -1;
            }
        }
        
        return answer;
    }
}
