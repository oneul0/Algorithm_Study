import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dp, matrix;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1];
        matrix = new int[N][2];
        StringTokenizer st;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int len = 1; len<N; len++){
            for(int i = 0; i<N-len; i++){
                int j = i+len;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k<j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
                }
            }
        }
        bw.write(dp[0][N-1]+"");
        bw.flush();
        br.close();
        bw.close();
    }

}