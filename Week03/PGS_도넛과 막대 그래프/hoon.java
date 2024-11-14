import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, int[]> nodes = new HashMap<>();
        int[] answer = new int[4];

        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];

            if(!nodes.containsKey(from)){
                nodes.put(from, new int[]{0,0});
            }
            if(!nodes.containsKey(to)){
                nodes.put(to, new int[]{0,0});
            }

            nodes.get(from)[0]++;
            nodes.get(to)[1]++;
        }

        for(int key : nodes.keySet()){
            int[] cnt = nodes.get(key);

            //out 2이상, in 0 -> 생성한 정점

            if(cnt[0] >= 2 && cnt[1] == 0){
                answer[0] = key;
            }

            //out 0, int >= 1 -> 막대 그래프
            else if(cnt[0] == 0 && cnt[1] > 0){
                answer[2]++;
            }

            //in, out >= 2 -> 8
            else if(cnt[0] >= 2 && cnt[1] >= 2){
                answer[3]++;
            }

        }

        answer[1] = nodes.get(answer[0])[0] - answer[2] - answer[3];
        return answer;
    }
}