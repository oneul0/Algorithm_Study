import java.util.*;
import java.io.*;

public class Main {

    static int n; // 남은 날
    static int m; // 챕터
    static int[][] dp;

    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[m+1][n+1];

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int page = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= n; j++) {
                //초기화
                dp[i][j] = dp[i-1][j];
                if( j >= day) {
                    //dp[챕터][소요된 일 수] == dp[i][j]
                    //(dp[i-1][j-현재 소모할 일 수]) 과 이전 dp값을 비교해주며 더 큰것으로 갱신
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-day] + page);
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}