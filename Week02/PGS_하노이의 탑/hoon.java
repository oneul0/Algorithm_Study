import java.util.*;
class Solution {
    ArrayList<int[]> tmp = new ArrayList<>();
    public int[][] solution(int n) {
        dfs(n, 1, 2, 3);
        int[][] answer = new int[tmp.size()][];
        for(int i = 0; i<tmp.size(); i++){
            answer[i] = tmp.get(i);
        }
        return answer;
    }

    void dfs(int n, int from, int by, int to){
        //기저사례
        if(n == 0) return;

        dfs(n-1, from, to, by);
        tmp.add(new int[]{from, to});
        dfs(n-1, by, from, to);

    }
}