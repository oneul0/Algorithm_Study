import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[][] board = new char[9][9];
    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static boolean[][] block = new boolean[9][10];

    static boolean foundSolution = false;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] != '0') {
                    int num = board[i][j] - '0';

                    row[i][num] = true;
                    col[j][num] = true;

                    // (행/3) * 3 + (열/3)
                    int blockIdx = (i / 3) * 3 + (j / 3);
                    block[blockIdx][num] = true;
                }
            }
        }

        solve(0, 0);

        for (int i = 0; i < 9; i++) {
            System.out.println(new String(board[i]));
        }
    }

    public static void solve(int r, int c) {
        if (foundSolution) {
            return;
        }

        // 한 행의 끝에 도달하면 다음 행의 첫 번째 열로 이동
        if (c == 9) {
            c = 0;
            r++;
        }

        if (r == 9) {
            foundSolution = true;
            return;
        }

        if (board[r][c] != '0') {
            solve(r, c + 1);
            return;
        }

        for (int num = 1; num <= 9; num++) {
            int blockIdx = (r / 3) * 3 + (c / 3);

            if (!row[r][num] && !col[c][num] && !block[blockIdx][num]) {
                board[r][c] = (char) (num + '0');
                row[r][num] = true;
                col[c][num] = true;
                block[blockIdx][num] = true;

                solve(r, c + 1);

                if (foundSolution) {
                    return;
                }

                board[r][c] = '0';
                row[r][num] = false;
                col[c][num] = false;
                block[blockIdx][num] = false;
            }
        }
    }
}