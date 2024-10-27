import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int[][] dir = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static int[][] matrix;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 5 <= n <= 25
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (line.charAt(j) - '0');
            }
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    answer.add(dfs(i,j));
                }
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }
    public static int dfs(int y, int x) {
        int dep = 1;
        matrix[y][x] = 0;
        for (int[] d : dir) {
            int ny = y + d[0], nx = x + d[1];
            if (0 <= ny && ny < n && 0<= nx && nx < n && matrix[ny][nx] == 1) {
                dep += dfs(ny, nx);
            }
        }
        return dep;
    }
}
