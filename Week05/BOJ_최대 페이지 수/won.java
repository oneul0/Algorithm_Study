//85 min
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 남은 날 수
        int M = Integer.parseInt(st.nextToken()); // 챕터 수

        // 각 챕터의 소요 일 수와 페이지 수 저장
        int[] days = new int[M + 1];
        int[] pages = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            pages[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 초기화
        int[][] dp = new int[M + 1][N + 1];

        // dp[i][j] :  i는 챕터, j는 소요된 일 수

        // DP 점화식 적용
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                // 챕터를 읽지 않는 경우
                dp[i][j] = dp[i - 1][j];

                // 챕터를 읽는 경우 (남은 날 수가 충분한 경우)
                if (j >= days[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - days[i]] + pages[i]);
                }
            }
        }
       /* System.out.println("dp = ");
        for( int[] page : dp ) {
            System.out.println(Arrays.toString(page));
        }*/
        // 결과 출력
        System.out.println(dp[M][N]);
    }
}
