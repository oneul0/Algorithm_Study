import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N, M, sum, ans;
  static int[] m,c;
  static int[][] dp;
  public static void main(String[] args) throws Exception {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    m = new int[N+1];
    c = new int[N+1];
    dp = new int[N+1][10001];
    st = new StringTokenizer(br.readLine());
    for(int i = 1; i<=N; i++){
      m[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for(int i = 1; i<=N; i++){
      c[i] = Integer.parseInt(st.nextToken());
      sum += c[i];
    }

    for(int i = 1; i<=N; i++){
      for(int j = 0; j<= sum; j++){
        if(j - c[i] >= 0){
          dp[i][j] = Math.max(dp[i][j], dp[i-1][j-c[i]] + m[i]);
        }
        dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
      }
    }

    for(int i = 0; i<=sum; i++){
      if(dp[N][i] >= M){
        ans = i;
        break;
      }
    }

    System.out.println(ans);
  }
}
