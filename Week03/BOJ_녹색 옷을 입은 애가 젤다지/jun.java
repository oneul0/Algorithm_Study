import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine()); // 2 <= n <= 125
            if (n == 0) break;
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] row = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(row[j]);
                }
            }
            int answer = dijkstra(grid);
            System.out.println("Problem " + cnt++ + ": " + answer);
        }
    }
    public static int dijkstra(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]); // cost , y, x
        pq.add(new int[]{grid[0][0],0,0});
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            int[] out = pq.remove();
            int cur_cost = out[0], cur_y = out[1], cur_x = out[2];
            if (cur_y == n - 1 && cur_x == n - 1) {
                return cur_cost;
            }
            for (int[] dir : dir) {
                int ny = dir[0] + cur_y, nx = dir[1] + cur_x;
                if (0 <= ny && ny < n && 0 <= nx && nx < n && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    pq.add(new int[]{cur_cost + grid[ny][nx], ny, nx});
                }
            }
        }
        return -1;
    }
}
