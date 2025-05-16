import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  static int N, M;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());


    int[] indegree = new int[N+1];
    List<List<Integer>> arr = new ArrayList<>();

    for(int i = 0; i<=N; i++){
      arr.add(new ArrayList<>());
    }

    for(int i = 0; i<M; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      arr.get(a).add(b);
      indegree[b]++;
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for(int i = 1; i<=N; i++){
      if(indegree[i] == 0){
        pq.offer(i);
      }
    }

    StringBuilder sb = new StringBuilder();
    while(!pq.isEmpty()){
      int cur = pq.remove();
      sb.append(cur+" ");

      for(int nxt: arr.get(cur)){
        indegree[nxt]--;

        if(indegree[nxt] == 0){
          pq.offer(nxt);
        }
      }
    }

    System.out.printf(sb.toString());
  }
}
