import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 수
        int m = Integer.parseInt(st.nextToken()); // 간선 수
        int start = Integer.parseInt(st.nextToken()); // 시작 노드

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (List<Integer> val : graph.values()) {
            Collections.sort(val);
        }

        boolean[] visited = new boolean[n+1];
        dfs(start, visited, graph);
        System.out.println();
        bfs(start,visited,graph);
    }

    public static void dfs(int node, boolean[] visited, Map<Integer, List<Integer>> graph) {
        System.out.print(node + " ");
        visited[node] = true;
        if (graph.containsKey(node)) {
            for (int v : graph.get(node)) {
                if (!visited[v]) {
                    dfs(v, visited, graph);
                }
            }
        }
    }

    public static void bfs(int node, boolean[] visited, Map<Integer, List<Integer>> graph) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(node);
        visited[node] = false;
        while(!q.isEmpty()) {
            int out = q.remove();
            System.out.print(out + " ");
            if (graph.containsKey(out)) {
                for (int v : graph.get(out)) {
                    if (visited[v]) {
                        visited[v] = false;
                        q.add(v);
                    }
                }
            }
        }
    }
}
