import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dp = new int[1001];
    static final int mod = 10007;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%mod;
        }
        bw.write(dp[n]+"");
        bw.flush();
        br.close();
        bw.close();
    }
}
/*
 * 가로로만 늘어나기 때문에 가로 칸이 몇 개인지만 구하면 됨
 * */