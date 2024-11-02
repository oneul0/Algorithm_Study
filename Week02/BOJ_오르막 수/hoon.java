import java.io.*;

public class Main {
    static final int mod = 10007;
    static long[][] dp = new long[1001][10];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        //j로 끝나는 오르막 수의 개수 초기화 -> 길이가 1인 j로 끝나는 수(0~9)
        for(int j = 0; j<10; j++){
            dp[1][j] = 1;
        }

        for(int i = 1; i<=N; i++){
            for(int j = 0; j<10; j++){
                if(j == 0){
                    dp[i][0] = 1;
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % mod;
            }
        }
        int ans = 0;
        for(int j = 0; j<10; j++){
            ans += dp[N][j];
        }
        bw.write(ans%mod+"");
        bw.flush();
        bw.close();
        br.close();
    }
}