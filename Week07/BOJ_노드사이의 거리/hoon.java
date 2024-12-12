import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static ArrayList<ArrayList<int[]>> gr = new ArrayList<>();
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        dist = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            gr.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            String[] in = br.readLine().split(" ");
            int n1 = Integer.parseInt(in[0]);
            int n2 = Integer.parseInt(in[1]);
            int n3 = Integer.parseInt(in[2]);
            gr.get(n1).add(new int[]{n2, n3});
            gr.get(n2).add(new int[]{n1, n3});
        }

        for (int i = 0; i < M; i++) {
            String[] tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            if (dist[from][to] == 0) bfs(from);
            System.out.println(dist[from][to]);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] nx : gr.get(cur)) {
                int next = nx[0];
                int cost = nx[1];
                if (!visited[next]) {
                    visited[next] = true;
                    dist[start][next] = dist[start][cur] + cost;
                    dist[next][start] = dist[start][next];
                    q.offer(next);
                }
            }
        }
    }
}
