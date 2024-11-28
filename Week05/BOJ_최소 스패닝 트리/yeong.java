import java.io.*;
import java.util.*;

//프림 알고리즘
// 정점 정보 클래스
class Node implements Comparable<Node>{
    int to;
    int weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    // 우선 순위 큐 활용
    @Override
    public int compareTo(Node node) {
        return weight - node.weight;
    }
}

public class Main {
    static StringTokenizer st;
    static int v; // 정점의 개수
    static int e; // 간선의 개수
    static boolean[] visit; // 정점 방문 여부
    static PriorityQueue<Node> queue; // 우선순위 큐
    static ArrayList<ArrayList<Node>> nodeList; // 정점 정보 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visit = new boolean[v + 1];
        queue = new PriorityQueue<>();

        nodeList = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            nodeList.add(new ArrayList<>());
        }

        // 간선 정보 입력 (정점 연결리스트)
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(v1).add(new Node(v2,weight));
            nodeList.get(v2).add(new Node(v1,weight));
        }

        queue.offer(new Node(1,0));

        int sum = 0;
        while(!queue.isEmpty()){
            Node n = queue.poll();
            int to = n.to;
            int weight = n.weight;

            // 이미 방문한 정점이라면 탐색 X
            if(visit[to]) {
                continue;
            }
            visit[to] = true;
            sum += weight;

            // 연결되어 있는 방문하지 않은 정점 큐에 추가
            for(Node next : nodeList.get(to)) {
                if(!visit[next.to]) {
                    queue.offer(next);
                }
            }
        }

        System.out.println(sum);
    }
}