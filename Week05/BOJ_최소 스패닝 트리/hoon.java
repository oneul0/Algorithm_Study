import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int V, E;
    static ArrayList<ArrayList<Node>> gr = new ArrayList<>();
    static int[] parent; // 각 노드의 부모를 저장하기 위한 부모 테이블
    public static void main(String[] args) throws IOException {
        String[] ve = br.readLine().split(" ");
        V = Integer.parseInt(ve[0]);
        E = Integer.parseInt(ve[1]);
        for(int i = 0; i<V; i++) gr.add(new ArrayList<>());

        parent = new int[V+1];
        //각 노드의 부모를 본인으로 초기화
        for(int i = 0; i<=V; i++) parent[i] = i;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i<E; i++){
            String[] abc = br.readLine().split(" ");
            // 각 노드에서 뻗어나가는 모든 경우의 수를 구함
            pq.offer(new Node(Integer.parseInt(abc[0]), Integer.parseInt(abc[1]), Integer.parseInt(abc[2])));
        }

        int useNode = 0, result = 0;
        // 사용한 노드가 V-1이 될 때 까지 찾음(사이클이 없는 정점을 연결하는 간선은 V-1개이므로)
        while(useNode < V-1){
            Node cur = pq.poll();
            // 같은 부모가 아니라면 사이클이 생기지 않으므로 연결해도 됨
            if(find(cur.from) != find(cur.to)){
                union(cur.from, cur.to);
                result = result + cur.cost;
                useNode++;
            }
        }

        bw.write(result + "");
        bw.flush();
        br.close();
        bw.close();
    }

    //각 노드끼리 이었을 때 사이클이 생기지 않도록 하고
    //각 노드의 부모 노드를 찾고, 간선을 연결하여 합치는 과정(유니온 파인드)

    // 노드끼리 합치는 메서드
    static void union(int from, int to){
        from = find(from);
        to = find(to);
        if(from != to){
            parent[to] = from;
        }
    }

    // 각 노드의 부모를 찾는 메서드
    static int find(int from){
        if(from == parent[from]) return from; // 본인이 부모라면 재귀 종료
        else return parent[from] = find(parent[from]); // 재귀적으로 최상단의 부모를 찾음
    }
}

class Node implements Comparable<Node> {
    int from, to, cost;
    Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}
