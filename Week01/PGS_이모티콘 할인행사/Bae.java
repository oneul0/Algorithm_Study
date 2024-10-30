import java.util.*;

class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
//      기준에 맞는 할인 이모티콘 전부 구매 -> 일정 금액이후 이모티콘 플러스
        
//      이모티콘 당 할인율 설정 변경으로
//      users 배열을 할인율 조합에 따라 완전탐색하여 이용자수, 판매액 검사하여 MAX
//      1. 할인율 조건은 user[0] X -> 10, 20, 30, 40 4가지 max 4^7 -> 2^14 16000 -> 10^4  
//      1. 할인율 조합을 구하고 2. user 완전탐색
        int n = emoticons.length;
        boolean[] visited = new boolean[n];
        int[] sales = {10,20,30,40};
        
        List<int[]> combs = new ArrayList<>();
        List<int[]> answer = new ArrayList<>();
        
//      조합 생성
        
        makeComb(new int[n], 0, sales, combs);
        
        for (int[] curr : combs)  {
            int plus = 0;
            int amount = 0;
            
            for(int[] user : users) {
                
                int limit = user[1];
                int user_sale = user[0];
                int curr_amount = 0;
                for(int j=0; j<curr.length; j++) {
                    if(curr[j] >= user_sale) {
                        curr_amount+= emoticons[j]*(100-curr[j])/100;
                    }
                }
                
                // 마지막에 검사 로직
//                limit여도 바로 플러스 
                    if(curr_amount >= limit) {
                        plus++;
                    } else {
                        amount += curr_amount;
                    }
            }
            answer.add(new int[]{plus, amount});
        }
        
        if (answer.size() == 1) {
            return answer.get(0);
        } else {
            Collections.sort(answer, (a,b) -> {
                if(a[0] != b[0]) {
                    return Integer.compare(b[0], a[0]);
                }
                return Integer.compare(b[1], a[1]);
            });
        }
        
        
        return answer.get(0);
    }
    
    public void makeComb (int[] curr, int index, int[] sales, List<int[]> combs ) {
        if(index == curr.length) {
//          배열 참조때문에 copyOf or clone or 수동 복사
            combs.add(Arrays.copyOf(curr, curr.length));
            return;
        }
        
        for(int sale : sales) {
            curr[index] = sale;
            makeComb(curr, index+1, sales, combs);
        }
    }
   
}
