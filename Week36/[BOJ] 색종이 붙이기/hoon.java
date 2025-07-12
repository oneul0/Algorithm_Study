import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int minPaper = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(minPaper == Integer.MAX_VALUE ? -1 : minPaper);
    }

    static void dfs(int row, int col, int count) {
        if (count >= minPaper) {
            return;
        }

        if (row == 10) {
            minPaper = Math.min(minPaper, count);
            return;
        }

        int nextRow = col == 9 ? row + 1 : row;
        int nextCol = col == 9 ? 0 : col + 1;

        if (board[row][col] == 0) {
            dfs(nextRow, nextCol, count);
            return;
        }

        for (int size = 5; size >= 1; size--) {
            if (papers[size] > 0 && canPatch(row, col, size)) {
                patch(row, col, size, 0);
                papers[size]--;

                dfs(nextRow, nextCol, count + 1);

                patch(row, col, size, 1);
                papers[size]++;
            }
        }
    }

    static boolean canPatch(int row, int col, int size) {
        if (row + size > 10 || col + size > 10) {
            return false;
        }

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void patch(int row, int col, int size, int value) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = value;
            }
        }
    }
}