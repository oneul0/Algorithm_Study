/*크루스칼 알고리즘
* 그래프 내의 모든 정점들은 가장 적은 비용으로 연결하기 위해 사용
* ‼️그래프 내의 모든 정점을 포함하고 사이클이 없는 연결 선을 그렸을 때, 가중치 합이 최소가 되는 상황을 구하고 싶은 경우 사용한다‼️
* 최소 신장 트리(최소 스패닝 트리, MST)를 구하기 위한 알고리즘이다.
* 일단, 그래프의 간선을 가중치 오름차순으로 정렬한다.*/

/* 최소 스패닝 트리 MST
* 1. 모든 정점을 포함하고,
* 2. 정점 간 서로 연결이 되며 사이클이 존재하지 않는 그래프
* 정점의 개수: n, 간선의 개수: n-1
* */
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
public class Main {
    static int[] parent;

    // 경로 압축(x가 속한 집합의 대표(루트 노드)를 찾는 함수)
    public static int find(int x){
        // 부모 찾기
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b){
        int rootA = find(a); int rootB = find(b);
        if(rootA != rootB) parent[rootB] = rootA;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 집의 개수 N, 길의 개수 M
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데
        // A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < m; i++){
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);
            edges.add(new Edge(a, b, c));
        }

        // 유지비 기준 오름차순 정렬
        Collections.sort(edges);

        // 유니온 파인드 초기화
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;

        int totalCost = 0;
        int maxCost = 0; // 가장 비용이 큰 간선


        for(Edge e : edges){
            if(find(e.from) != find(e.to)){
                union(e.from, e.to);
                totalCost += e.cost;
                maxCost = e.cost; // 가장 마지막에 추가된 간선이 최대 비용
            }
        }

        // 가장 큰 비용의 길을 제거
        System.out.println(totalCost - maxCost);
    }
}
