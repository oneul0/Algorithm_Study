import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 1 <= n <= 1,000
        int dp[][] = new int[n+1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[i][9] = dp[i-1][9];
            for (int j = 8; j >= 0; j--) {
                dp[i][j] = (dp[i-1][j] + dp[i][j+1]) % 10007;
            }
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[n][i]) % 10007;
        }
        System.out.println(ans);
    }
}
