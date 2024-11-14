import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        // 노드 위치
        private int x;
        private int y;

        // 거리
        private int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCost() {
            return cost;
        }

        // 우선순위 큐에서 이용
        @Override
        public int compareTo(Node other) {
            // time 작은 것이 높은 우선순위를 갖는다.
            if(other.cost > this.cost)
                return -1;
            else
                return 1;
        }
    }

    // 무한 의미
    public static final int INF = (int)1e9;

    // 반복적으로 입력 받는 크기
    public static int N;

    // 그래프
    //public static ArrayList<Node> graph;
    public static int[][] graph;

    public static int[][] d;

    // 결과를 가지고 있을 변수
    public static ArrayList<Integer> result;

    // 순서대로 좌,우,하,상 좌표 이동 배열
    public static int[] xmove = {-1,1,0,0};
    public static int[] ymove = {0,0,-1,1};

    // 다익스트라, 우선순위 큐를 이용한 최소 비용 구하기
    public static void dijkstra() {

        // 우선순위 큐 이용
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        // 초기 젤다의 위치는 0,0으로 고정
        pq.offer(new Node(0,0,graph[0][0]));

        // 큐가 빌 때까지 반복
        while(!pq.isEmpty()) {
            Node node = pq.poll();

            // 현재 위치에서 상하좌우 확인
            for(int i=0; i<4; i++) {
                int tempX = node.x+xmove[i];
                int tempY = node.y+ymove[i];

                // 맵을 벗어나는 경우 건너뛰기
                if(tempX >= N || tempX < 0 || tempY >= N || tempY < 0) {
                    continue;
                }

                // 현재 위치까지의 비용 + 한 칸 이동한 비용
                int tempCost = (node.cost+graph[tempX][tempY]);

                // 최소 비용에 등록된 값보다 작을 경우
                if(d[tempX][tempY] > tempCost ) {

                    // 갱신하고
                    d[tempX][tempY] = tempCost;

                    // 우선순위 큐에 현재 위치와 비용을 노드로 넣기
                    pq.offer(new Node(tempX, tempY, tempCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 시간 단축을 위해 BufferedReader 이용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 몇 번 입력받았는 지 체크
        int cnt = 1;

        // N을 0 입력받을 때까지 무한 반복
        while(true) {
            N = Integer.parseInt(br.readLine());

            // 0 입력 시, 전체 입력 종료
            if(N == 0) {

                // 버퍼닫고
                br.close();

                // 반복문 탈출
                break;
            }

            // 그래프 변수와 최소비용 변수 크기 할당
            graph = new int[N][N];
            d = new int[N][N];


            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                // 최소비용 변수를 무한으로 초기화
                Arrays.fill(d[i], INF);

                for(int j=0; j<N; j++) {
                    // 그래프 변수에 입력
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 다익스트라 알고리즘으로 최소비용 구하기
            dijkstra();

            // 결과 출력 (출구는 [N-1][N-1]에 위치함)
            System.out.println("Problem " + cnt + ": " + d[N-1][N-1]);
            cnt++;
        }
    }
}