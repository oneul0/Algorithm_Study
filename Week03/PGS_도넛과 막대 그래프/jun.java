import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, List<Integer>> nodes = new HashMap<>();
        Map<Integer, Integer> depth = new HashMap<>();
        for(int[] edge : edges) {
            nodes.putIfAbsent(edge[0], new ArrayList<>());
            nodes.get(edge[0]).add(edge[1]);
            depth.put(edge[1], depth.getOrDefault(edge[1], 0) + 1);
            depth.putIfAbsent(edge[0], 0);
        }
        int start = -1;
        int len = 0;
        for (int node : depth.keySet()) {
            if ((depth.get(node) == 0) && (nodes.get(node).size() > len)) {
                start = node;
                len = nodes.get(node).size();
            }
        }
        answer[0] = start;
        Set<Integer> visited = new HashSet<>();
        for(Integer node : nodes.get(start)) {
            if(visited.contains(node)) {
                continue;
            }
            Queue<Integer> q = new ArrayDeque<>();
            visited.add(node);
            q.add(node);
            int nc = 0;
            int ec = 0;
            while(!q.isEmpty()) {
                int out = q.remove();
                nc++;
                if(nodes.containsKey(out)) {
                    for(Integer next : nodes.get(out)) {
                        ec++;
                        if(!visited.contains(next)){
                            q.add(next);
                            visited.add(next);
                        }
                    }
                }
            }
            if(nc == ec) {
                answer[1]++;
            } else if((ec + 1) == nc) {
                answer[2]++;
            } else if((nc + 1) == ec) {
                answer[3]++;
            }
        }
        return answer;
    }
}
