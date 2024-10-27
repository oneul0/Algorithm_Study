import java.util.*;

public class Main {
    static int N,M,V;
    static int[][] graph;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        V = scanner.nextInt();
        graph = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i=0; i< M; i++){
            int u = scanner.nextInt(); // 첫 번째 정점
            int v = scanner.nextInt(); // 두 번째 정점
            graph[u][v] = graph[v][u] =  1; // 간선 추가
        }

        DFS(V);
        Arrays.fill(visited, false); // 방문 기록 초기화
        System.out.println();
        BFS(V);
        scanner.close(); // 스캐너 닫기
    }

    private static void BFS(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        System.out.print(v + " "); // 시작 노드
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int next=1; next<=N; next++){
                if(!visited[next] && graph[curr][next] == 1){
                    queue.offer(next);
                    System.out.print(next + " ");
                    visited[next] = true;
                }
            }
        }
    }

    static void DFS(int start){
        visited[start] = true;
        System.out.print(start + " ");
        for( int next=1; next<=N; next++){
            if(!visited[next] && graph[start][next] == 1){ // 방문하지 않은 노드 방문하기
                DFS(next);
            }
        }
    }
}
