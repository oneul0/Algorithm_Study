/*
루트 노드( 무조건 양) -> 최대 모을 수 잇는 양은 몇 마리
무조건 양 수 > 늑대 수
탐색할 때 현재 노드에서 양 확인 -> 다음 노드 후보들 모두 탐색하여 되면 -> 진행



*/




import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
//      트리 양방향
        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
        }
        
//      루트 0부터 시작
        return dfs(map, info, 0, 0, 0, new ArrayList<>(List.of(0)));
    }
    
    public int dfs(Map<Integer, List<Integer>> map, int[] info, int sheep, int wolf, int curr, List<Integer> nextNodes) {
        
        // 현재 노드 방문
        if (info[curr] == 0) {
                sheep++;
            } else {
                wolf++;
            }
        
        
        // 늑대가 양보다 많아지면 현재 탐색은 0
        if (wolf >= sheep) {
            return 0;
        }
        
        // 최대 양의 수 갱신
        int max = sheep;
        
        // 다음 노드 리스트 업데이트
        List<Integer> candidates = new ArrayList<>(nextNodes);
        
//      list에서 index가 아닌 해당 값을 제거 -> Integer.valueOf로 객체 변환
        candidates.remove(Integer.valueOf(curr)); // 현재 노드를 후보에서 제거
        
        if (map.containsKey(curr)) {
            for(int temp : map.get(curr)) {
                candidates.add(temp);
            }
        }
        
        // 후보 노드를 탐색
        for (int next : candidates) {
            max = Math.max(max, dfs(map, info, sheep, wolf, next, candidates));
        }
        
        return max;
    }
}
