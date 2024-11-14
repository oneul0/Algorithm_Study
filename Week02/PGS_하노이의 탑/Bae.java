import java.util.*;

class Solution {
    public int[][] solution(int n) {
        
//        dp
//        n-1개 -> 2번째 기둥
//        n번째 -> 3번째
//        n-1개 -> 3번째 기둥
//        ----
//        n-2개 -> 1번째 기둥
//        n-1번째 -> 3번째
//        n-2개 -> 3번째 기둥
//        --- 반복
        
        List<int[]> list = new ArrayList<>();
        func(n,1,2,3,list);
        
        int[][] answer= new int[list.size()][2];
        
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public static void func (int n, int one, int two, int three, List<int[]> list) {
        
        
        if(n == 1) {
            list.add(new int[]{one,three});
            return;
        }
        
        func(n-1, one, three, two,list);
        list.add(new int[]{one,three});
        func(n-1,two,one,three,list);
        
    }
}
