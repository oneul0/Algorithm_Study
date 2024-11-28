
import java.util.*;
import java.io.*;

// 최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
// 정점의 개수가 n이면 간선의 개수는 n-1
// https://chanhuiseok.github.io/posts/algo-33/

/* 크루스칼 알고리즘
1. 선택되지 않은 간선들 중 최소 가중치인 간선 선택
2. 만약 그 간선을 선택했을 때, 지금까지 구성된 스패닝 트리에 사이클이 없을 경우에만 선택
3. 총 v-1(정점의 개수-1)개의 간선이 선택될 때 까지 반복

구성한 스패닝 트리에 사이클이 발생하는지에 대한 여부를 판단하기 위해, 분리 집합(Disjoint Set)을 사용
* */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: 정점의 개수 V와 간선의 개수 E
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수


        // 간선 정보를 저장할 리스트
        List<int[]> edges = new ArrayList<>();

        // 다음 E개의 줄: 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 시작 정점
            int B = Integer.parseInt(st.nextToken()); // 끝 정점
            int C = Integer.parseInt(st.nextToken()); // 가중치
            edges.add(new int[]{A, B, C});
        }

        // 간선 리스트를 가중치(C) 기준으로 오름차순 정렬
        edges.sort(Comparator.comparingInt(edge -> edge[2]));

        Kruskal kruskal = new Kruskal(V);
        int minWeight = 0;
        for (int[] edge : edges) {
            if(kruskal.union(edge[0], edge[1])){
                minWeight += edge[2];
            }
        }

        System.out.println("answer = " + minWeight);

        br.close();
    }


}

class Kruskal {
    private int[] parent;
    private int[] rank;

    // 생성자
    public Kruskal(int n) {
        parent = new int[n+1];
        rank = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return  false; // cycle
        }

        // rank를 기반으로 더 낮은 트리를 높은 트리에 붙인다.
        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }

}
