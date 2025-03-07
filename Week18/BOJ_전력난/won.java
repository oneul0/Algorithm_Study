package BOJ_전력난;
// 최소 스패닝 트리
import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int from, to, cost;
    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) { // 오름 차순 정렬
        return this.cost - o.cost;
    }
}
// 도시에 있는 모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로를 왕래할 수 있어야 함
public class Main {
    static int[] parent;

    // 경로 압축
    public static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX!=rootY){ parent[rootY] = rootX; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            // 집의 수 m과 길의 수 n (1 ≤ m ≤ 200000, m-1 ≤ n ≤ 200000)
            String[] input = br.readLine().split(" ");
            int m = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);
            if(m == 0 && n == 0) break;

            // n개의 줄에 각 길에 대한 정보 x, y, z
            // x번 집과 y번 집 사이에 양방향 도로가 있으며 그 거리가 z미터
            List<Edge> graph = new ArrayList<>();
            int totalcost = 0;
            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                totalcost += c;
                graph.add(new Edge(a, b, c));
            }

            // 거리 기준 오름 차순 정렬
            Collections.sort(graph);

            // union-find 초기화
            parent = new int[m];
            for (int i = 0; i < m; i++) { parent[i] = i; }

            int min_cost = 0;

            for( Edge e : graph ) {
                if(find(e.from)!=find(e.to)) {
                    union(e.from, e.to);
                    min_cost += e.cost;
                }
            }
            int ans = totalcost-min_cost;
            bw.write(ans + "\n");
            bw.flush();
        }
    }
}
