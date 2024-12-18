package solo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 200 이하
        int m = Integer.parseInt(br.readLine()); // 1,000 이하
        int[][] matrix = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] plan = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m-1; i++) {
            int start = plan[i];
            int end = plan[i+1];
            if (!trip(matrix, start, end)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static boolean trip(int[][] matrix, int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[matrix.length];
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int out = q.remove();
            if (out == end) {
                return true;
            }
            for (int next = 1; next < matrix.length; next++) {
                if (!visited[next] && matrix[out][next] == 1) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}
