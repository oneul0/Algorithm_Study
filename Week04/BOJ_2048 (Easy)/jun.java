import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int max_value = 0;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 1 <= n <= 20
        // 최대 5번 -> (4^5)
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (n == 1) {
            System.out.println(board[0][0]);
        } else {
            dfs(0, board);
            System.out.println(max_value);
        }
        
    }

    private static void dfs(int cnt, int[][] board) {
        if (cnt == 5) {
            max_value = Math.max(max_value, get_max_value(board));
            return;
        }
        for (int d = 0; d < 4; d++) {
            int[][] next = move(d, board);
            if (!not_move(board, next)) {
                dfs(cnt+1, next);
            }
        }
    }

    private static int[][] move(int d, int[][] board) {
        int[][] next = new int[n][n];
        for (int i = 0; i < n; i++) {
            next[i] = board[i].clone();
        }

        for (int i = 0; i < n; i++) { // 상 하 -> 열 , 좌 우 -> 행
            plus(next, i, d);
        }

        return next;
    }

    private static void plus(int[][] next, int idx, int d) {
        Deque<Integer> q = new ArrayDeque<>();
        int[] cum = new int[n];

        if (d == 0) { // 상, 얄
            for (int i = 0; i < n; i++) {
                if (next[i][idx] > 0) {
                    q.add(next[i][idx]);
                }
            }
        } else if (d == 1) { // 하, 열
            for (int i = 0; i < n; i++) {
                if (next[n-1-i][idx] > 0) {
                    q.add(next[n-1-i][idx]);
                }
            }
        } else if (d == 2) { // 좌, 행
            for (int i = 0; i < n; i++) {
                if (next[idx][i] > 0) {
                    q.add(next[idx][i]);
                }
            }
        } else { // 우, 행
            for (int i = 0; i < n; i++) {
                if (next[idx][n-1-i] > 0) {
                    q.add(next[idx][n-1-i]);
                }
            }
        }
        int temp = 0; // 몇개가 쌓이는지 체크
        while (!q.isEmpty()) {
            int out = q.remove();
            if (!q.isEmpty() && (out == q.peek())) {
                out += q.remove();
            }
            cum[temp++] = out;
        }
        if (d == 0) { // 상, 열
            for (int i = 0; i < n; i++) {
                next[i][idx] = cum[i];
            }
        } else if (d == 1) { // 하, 열
            for (int i = 0; i < n; i++) {
                next[n-1-i][idx] = cum[i];
            }
        } else if (d == 2) { // 좌, 행
            for (int i = 0; i < n; i++) {
                next[idx][i] = cum[i];
            }
        } else { // 우, 행
            for (int i = 0; i < n; i++) {
                next[idx][n-1-i] = cum[i];
            }
        }
    }

    private static boolean not_move(int[][] board, int[][] next) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != next[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int get_max_value(int[][] board) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > max) {
                    max = board[i][j];
                }
            }
        }
        return max;
    }
}
