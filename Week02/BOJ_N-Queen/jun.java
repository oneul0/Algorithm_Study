import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[][] queens;
    public static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        queens = new int[n][n];
        answer = 0;
        bt(0, n);
        System.out.println(answer);
    }
    public static void bt(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (queens[row][col] == 0) {
                process(row, col, 1);
                bt(row + 1, n);
                process(row, col, -1);
            }
        }
    }
    public static void process(int y, int x, int flag) {
        for (int i = 0; i < queens.length; i++) {
            queens[i][x] += flag; // 세로
            for (int j = 0; j < queens.length; j++) {
                if (i-j == y-x || i+j == y+x) {
                    queens[i][j] += flag; // 대각선
                }
            }
        }
        queens[y][x] += flag;
    }
}
