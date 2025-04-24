import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static char[][] board;
  static int N, M, K, ans = 0;
  static String str;
  static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
  static int[][][] dp;

  public static void main(String[] args) throws Exception {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    board = new char[N][M];
    for (int i = 0; i < N; i++) {
      board[i] = br.readLine().toCharArray();
    }

    str = br.readLine();
    br.close();
    dp = new int[N][M][str.length()];
    for(int[][] tmp : dp){
      for(int[] row : tmp){
        Arrays.fill(row, -1);
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] == str.charAt(0)) {
          ans += search(i, j, 0);
        }
      }
    }

    bw.write(ans + "");
    bw.flush();
    bw.close();
  }

  public static int search(int cx, int cy, int depth) {
    if (depth == str.length()-1) {
      return 1;
    }

    if(dp[cx][cy][depth] != -1){
      return dp[cx][cy][depth];
    }

    dp[cx][cy][depth] = 0;

    for (int i = 0; i < 4; i++) {
      for(int k = 1; k<=K; k++){
        int nx = cx + (dx[i] * k);
        int ny = cy + (dy[i] * k);
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

        if(board[nx][ny] == str.charAt(depth + 1)){
          dp[cx][cy][depth] += search(nx, ny, depth+1);
        }
      }

    }
    return dp[cx][cy][depth];
  }
}
