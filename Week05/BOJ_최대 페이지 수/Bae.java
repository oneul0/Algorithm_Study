import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());



/*  n일간 읽을 때 읽을 수 있는 최대 페이지 수
*   m은 챕터의 수
*   1<= n <= 200 , 1 <= m <= 20
*   arr[i][0] = 소요 일수 <= 20
*   arr[i][1] = 소요 페이지 수 <= 300
*   dp ->
*
*
* */



        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m][2];
        for( int i = 0; i < m; i++ ) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }



        int[][] dp = new int[m+1][n+1];

        for(int i = 1; i <= m; i++) {
            int day = arr[i-1][0];
            int page = arr[i-1][1];

            for(int j = 0; j <= n; j++) {
                if( j >= day) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-day] + page);
                } else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }








        bw.write(String.valueOf(dp[m][n]));
        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
