import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class hoon {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int K, W, H;
  static boolean[][] map;
  static int[][][] dp;
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1}; // 걸어서 이동
  static int[] hx = {-2,-1,-2,-1,1,2,2,1}, hy = {-1,-2,1,2,-2,-1,1,2}; // 말처럼 이동

  static class Pair{
    int x, y, k;
    Pair(int x, int y, int k){
      this.x = x;
      this.y = y;
      this.k = k;
    }
  }

  public static void main(String[] args) throws IOException {
    StringTokenizer st;
    K = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    W = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());
    map = new boolean[H][W];
    dp = new int[H][W][K+1];

    for(int i = 0; i<H; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j<W; j++){
        map[i][j] = Integer.parseInt(st.nextToken()) == 1;
        for(int k = 0; k <= K; k++){
          dp[i][j][k] = Integer.MAX_VALUE;
        }
      }
    }

    //bfs
    Deque<Pair> q = new ArrayDeque<>();
    q.offer(new Pair(0, 0, 0));
    dp[0][0][0] = 0;

    while(!q.isEmpty()){
      Pair p = q.poll();

      // 걸어서 이동
      for(int i = 0; i < 4; i++){
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];

        if(nx<0 || ny<0 || nx>=H || ny>=W || map[nx][ny]) continue;
        if(dp[nx][ny][p.k] <= dp[p.x][p.y][p.k] + 1) continue;

        dp[nx][ny][p.k] = dp[p.x][p.y][p.k] + 1;
        q.offer(new Pair(nx, ny, p.k));
      }

      // 말처럼 이동
      if(p.k < K) {
        for(int i = 0; i < 8; i++){
          int nx = p.x + hx[i];
          int ny = p.y + hy[i];

          if(nx<0 || ny<0 || nx>=H || ny>=W || map[nx][ny]) continue;
          if(dp[nx][ny][p.k+1] <= dp[p.x][p.y][p.k] + 1) continue;

          dp[nx][ny][p.k+1] = dp[p.x][p.y][p.k] + 1;
          q.offer(new Pair(nx, ny, p.k+1));
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for(int i = 0; i <= K; i++){
      min = Math.min(min, dp[H-1][W-1][i]);
    }

    int ans = min == Integer.MAX_VALUE ? -1 : min;

    bw.write(ans+"");
    bw.flush();
    br.close();
    bw.close();
  }
}
