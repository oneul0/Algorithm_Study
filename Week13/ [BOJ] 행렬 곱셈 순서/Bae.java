import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
        * 항상 순서대로 곱할 수 있는 크기만 주어진다
        * 행렬 3
        * 0 [0] * 1 [0] * 1 [1] + 0 [0] * 2 [0] * 2 [1]
        * 1 [0] * 2 [0] * 2 [1] + 1 [0] * 0 [0] * 0 [1]
        *
        * 순서대로 곱해야 한다
        * n개중 먼저 곱할 수 있는 개수 n-1 (n번째 제외) 이후 순서대로 곱셈 합의 개수도 n-1
        *
        * 1 ~ n-1 중 한 개 ( 1 ~ n-1 [0] * [1] ) n-1개
        * n = 3 , dp[0][1] = matrix [0][0] * [0][1] * [1][1]
        * dp[0][2] = dp[0][1] + matrix[0][0] * [2][0] * [2][1]
        * 4개
        * ABCD
        * (AB)(CD) -> (AB) * (CD), ((AB)C)D, A((BC)D), (A(BC))D , A(B(CD))
        *
        * */
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][2];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];



        for(int len = 1; len < n; len++) {
            for(int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++) {
                    int temp = dp[i][k] + dp[k+1][j] +
                            matrix[i][0] * matrix[k][1] * matrix[j][1];
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
            }
        }


        bw.write(String.valueOf(dp[0][n-1]));
        bw.close();



    }


    public static void main(String[] args) throws IOException {
        solution();
    }



}
