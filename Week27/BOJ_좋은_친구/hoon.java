import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  static int N, K;
  static long ans = 0;
  static Queue<String> q = new ArrayDeque<>();
  static int[] len = new int[21];
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for(int i = 0; i<N; i++){
      String cur = br.readLine();
      q.offer(cur);

      len[cur.length()]++;

      if(q.size() > K+1){
        len[q.remove().length()]--;
      }

      ans += len[cur.length()]-1;
    }

    System.out.print(ans);

  }
}

//등수의 차이가 K보다 잡거나 같으면서 이름의 길이가 같은 친구