import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        List<Integer> list = new ArrayList<>();
        int n = privacies.length;
        Map <String, Integer> map = new HashMap<>();
        
//       map에서 날짜 확인
        for(String st : terms) {
            map.put(st.substring(0,1),Integer.parseInt(st.substring(2)));
        }
        
        int today_Y = Integer.parseInt(today.substring(0,4));
        int today_M = Integer.parseInt(today.substring(5,7));
        int today_D = Integer.parseInt(today.substring(8,10));
        
        
        for(int i=0; i<n; i++) {
            String pri = privacies[i].substring(privacies[i].length()-1);
//          날짜 받기
            int D = map.get(pri);
//          예외 케이스 
//          년, 월, 일 순으로 넘치는 경우 확인 + 유효기간은  day-1 해줘야한다
//          기간은 100이하 -> %24
            String priday= privacies[i];
            int pri_Y = Integer.parseInt(priday.substring(0,4));
            int pri_M = Integer.parseInt(priday.substring(5,7));
            int pri_D = Integer.parseInt(priday.substring(8,10));
            
            int D_num = D;
            
            pri_M += D_num;
            
            pri_Y += (pri_M - 1) / 12;
            pri_M = (pri_M - 1) % 12 + 1;
            
             pri_D -= 1;
            if (pri_D == 0) {
                pri_D = 28;
                pri_M -= 1;
                if (pri_M == 0) {
                    pri_M = 12;
                    pri_Y -= 1;
                }
            }
            
//          년, 월, 일순 각각 비교 X 하나라도 크면 만료
            if (pri_Y < today_Y || 
                (pri_Y == today_Y && pri_M < today_M) || 
                (pri_Y == today_Y && pri_M == today_M && pri_D < today_D)) {
                list.add(i + 1); 
            }  
        }
        int[] answer= new int[list.size()];
        for(int i = 0; i<list.size(); i ++) {
            answer[i] = list.get(i);
        }        
        return answer;
    }
}
