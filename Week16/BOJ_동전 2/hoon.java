import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] arr = new int[n];
    int[] dp = new int[k + 1];

    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 0; i < n; i++) {
      for (int j = arr[i]; j <= k; j++) {
        if (dp[j - arr[i]] != Integer.MAX_VALUE) {
          dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
        }
      }
    }

    bw.write((dp[k] == Integer.MAX_VALUE ? -1 : dp[k]) + "\n");
    bw.flush();
    bw.close();
    br.close();
  }
}
