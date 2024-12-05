import java.util.*;

class Solution {
    static int maxSheep = 0;
    static List<Integer>[] graph;

    public int solution(int[] info, int[][] edges) {

        graph = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        // DFS
        List<Integer> initialNodes = new ArrayList<>();
        initialNodes.add(0);
        dfs(0, 0, 0, initialNodes, info);

        return maxSheep;
    } 

    private void dfs(int node, int sheep, int wolf, List<Integer> possibleNodes, int[] info) {
        // 현재 노드의 동물에 따라 양 또는 늑대 수 증가
        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        // 늑대가 양보다 많아지면 종료
        if (wolf >= sheep) {
            return;
        }

        // 최대 양의 수 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 다음 방문 가능한 노드 리스트 생성
        List<Integer> nextNodes = new ArrayList<>(possibleNodes);
        nextNodes.remove(Integer.valueOf(node)); // 현재 노드는 제거
        nextNodes.addAll(graph[node]); // 현재 노드의 자식 노드 추가

        // 모든 가능한 다음 노드에 대해 DFS 호출
        for (int next : nextNodes) {
            dfs(next, sheep, wolf, nextNodes, info);
        }
    }
}