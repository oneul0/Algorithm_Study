import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int m, n;
  static int[] parents;
  static class Node implements Comparable<Node> {
    int from, to, cost;

    Node(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.cost, o.cost);
    }
  }

  static void union(int from, int to) {
    from = find(from);
    to = find(to);

    if(from != to){
      parents[to] = from;
    }
  }

  static int find(int x) {
    if(parents[x] == x) return x;
    return parents[x] = find(parents[x]);
  }

  public static void main(String[] args) throws IOException {
    StringTokenizer st;

    while(true) {
      st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      if(m == 0 && n == 0) break;
      PriorityQueue<Node> pq = new PriorityQueue<>();
      int ans = 0;
      for(int i = 0; i<n; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        pq.add(new Node(from, to, cost));
        ans += cost;
      }

      parents = new int[m];
      for(int i = 0; i < m; i++) {
        parents[i] = i;
      }

      int con = 0;
      while(con < m-1){
        Node node = pq.remove();
        if(find(node.from) != find(node.to)){
          union(node.from, node.to);
          con++;
          ans -= node.cost;
        }
      }

      bw.write(ans+"\n");
    }
    bw.flush();
    br.close();
    bw.close();
  }

}

