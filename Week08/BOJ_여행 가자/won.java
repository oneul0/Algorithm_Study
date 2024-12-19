import java.io.*;

public class Main {
    static int[][] graph; // 도시 간 연결 관계를 저장하는 인접 행렬
    static boolean[] visited; // dfs 탐색 중 방문한 도시를 기록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 도시의 수 N
        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        // 여행 계획에 속한 도시들의 수 M
        int M = Integer.parseInt(br.readLine());
        int[] plan = new int[M];

        // 도시 들의 연결관계 (N 줄)
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" "); // 한 줄 읽어서 공백 기준으로 나누기
            for (int j = 0; j < N; j++) {
                graph[i][j] =  Integer.parseInt(line[j]);
            }
        }

        // 마지막 줄: 여행 계획
        String[] tmpPlan = br.readLine().split(" "); // 한 줄 읽어서 공백 기준으로 나누기
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(tmpPlan[i]);
        }


        // plan 배열이 가능한지 확인해보기
        boolean isConnected = checkPlanConnection(N, plan);
        if (isConnected) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    private static boolean checkPlanConnection(int N, int[] plan) {
        visited = new boolean[N];

        // 첫번째 노드에서 탐색을 시작
        dfs(plan[0]-1);

        // 모든 plan 노드가 방문되었는지 확인
        for (int node: plan) {
            if (!visited[node - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int node) {
        visited[node] = true;
        for (int i = 0; i < graph.length; i++) {
            if(graph[node][i] == 1 && !visited[i]) {
                // 연결되어 있으며 방문하지 않은 노드
                dfs(i);
            }
        }
    }
}
