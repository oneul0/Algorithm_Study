import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String one = br.readLine();
        String two = br.readLine();

//  두 문자열의 부분 수열중 공통인 것들 중 가장 긴
/*  공통이 먼저 or 긴 게 먼저
*   공통이 먼저
*   String에서 index의 글자 선택 했는가 ? 안했는가 ?
*   dp로 어떻게 구현하지
*   점화식
*   2개의 문자열을 각각 돌면서 문자가 같은가 ? 확인
*   문자열의 길이 n이라고 햇을 때 1~n 각각 선택 했느냐 안햇느냐 -> 2^n 경우의 수
*
*   dp는 결국 이전에 미리 계산 -> 이전의 최장길이 부분 수열과 같이 그냥 한번 탐색하면서 이러한 값을 누적한다
*   dp 삼각형 생각하며 다른 문제에도 개념 도입
*
*
*/

        int n = one.length();
        int m = two.length();
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
//                  같지 않으면 이전에 누적한 값들 중 최대 값을 가져온다
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        bw.write(String.valueOf(dp[n][m]));




        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
