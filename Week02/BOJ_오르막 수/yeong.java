import java.io.*;

public class Main {
    static long[][] dp;
    static long[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        result = new long[n + 1];
        dp = new long[n + 1][10]; // y: n, x: 끝 자리 수 0~9 끝자리를 기준으로 2차원 배열
        // n=1 초기화
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
        result[1] = 10;

        for (int i = 2; i <= n; i++) { //n자리까지 구하는 dp의 y
            for(int j = 0; j < 10; j++) { //dp의 x
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    dp[i][j] %= 10007;
                }
                result[i] += dp[i][j];
            }
        }
        System.out.println(result[n] % 10007);
    }
}