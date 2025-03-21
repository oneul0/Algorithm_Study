import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int T, N;
  static int[] parents;
  static boolean[] chk;

  public static void main(String[] args) throws IOException {
    StringTokenizer st;
    T = Integer.parseInt(br.readLine());
    while(T-- > 0) {
      N = Integer.parseInt(br.readLine());
      parents = new int[N+1];
      chk = new boolean[N+1];
      for(int i = 1; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        parents[y] = x;
      }

      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      //조상 찾기
      while(x > 0){
        chk[x] = true;
        x = parents[x];
      }
      while(y > 0){
        if(chk[y]){
          bw.write(y+"\n");
          break;
        }
        y = parents[y];
      }
    }
    bw.flush();
    br.close();
    bw.close();
  }
}
