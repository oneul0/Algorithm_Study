import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N, M;
  static int[] parents;
  public static void main(String[] args) throws Exception{
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    parents = new int[N+1];
    for(int i = 1; i <= N; i++){
      parents[i] = i;
    }
    PriorityQueue<Node> pq = new PriorityQueue<>();
    int maxCost = 0;
    for(int i = 0; i<M; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      pq.offer(new Node(a,b,c));
    }

    int con = 0, ans = 0;
    //모든 노드를 잇고 그 중 가장 cost가 큰 관계를 끊기
    while(con < N-1){
      Node cur = pq.poll();

      if(find(cur.from) != find(cur.to)){
        union(cur.from, cur.to);
        ans+=cur.cost;
        maxCost = Math.max(maxCost, cur.cost);
        con++;
      }
    }
    ans -= maxCost;
    bw.write(ans+"\n");
    bw.flush();
    br.close();
    bw.close();
  }

  static void union(int from, int to){
    from = find(from);
    to = find(to);

    if(from != to){
      parents[to] = from;
    }
  }

  static int find(int x){
    if(x == parents[x]){
      return x;
    }
    else{
      return parents[x] = find(parents[x]);
    }
  }

}

class Node implements Comparable<Node>{
  int from, to, cost;

  Node(int from, int to, int cost){
    this.from = from;
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Node o) {
    return Integer.compare(cost, o.cost);
  }
}