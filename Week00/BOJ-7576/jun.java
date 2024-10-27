import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken()); // 열
        int row = Integer.parseInt(st.nextToken()); // 행 (2 ≤ M,N ≤ 1,000)

        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dirs = new int[][] {{1,0},{0,1},{0,-1},{-1,0}};
        Queue<int[]> q = new ArrayDeque<>();
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }
        while (!q.isEmpty()) {
            int[] out = q.remove();
            int y = out[0], x = out[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (check(nx, ny, map)) {
                    map[ny][nx] = map[y][x] + 1;
                    q.add(new int[]{ny, nx});
                }
            }
        }
        int answer = 0;
        loop: for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (map[i][j] == 0) {
                    answer = -1;
                    break loop;
                } else {
                    answer = Math.max(answer, map[i][j]-1);
                }
            }
        }
        System.out.println(answer);
    }

    public static boolean check(int x, int y, int[][] map) {
        if (y >= 0 && y < map.length && x >= 0 && x < map[0].length && map[y][x] == 0) return true;
        else return false;
    }
}
