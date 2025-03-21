import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    int[][] rgbs = new int[n][3];
    StringTokenizer st;
    for(int i = 0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j<3; j++) {
        rgbs[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int ans = Integer.MAX_VALUE;
    for (int first = 0; first < 3; first++) {
      int[][] dp = new int[n][3];

      for (int i = 0; i < 3; i++) {
        dp[0][i] = (i == first) ? rgbs[0][i] : 2000;
      }

      for (int i = 1; i < n; i++) {
        dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgbs[i][0];
        dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgbs[i][1];
        dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgbs[i][2];
      }

      for (int last = 0; last < 3; last++) {
        if (last != first) {
          ans = Math.min(ans, dp[n - 1][last]);
        }
      }
    }
    bw.write(ans+"");
    bw.flush();
    br.close();
    bw.close();
  }

}

//이전에 선택한 색 제외 선택할 수 있는 경우 중 가장 작은 값을 더해가면서 dp
