import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

/*
* 최소 스패닝 트리를 구하라
* 최소 스패닝 트리 -> 모든 정점들을 연결하는 부분 그래프 중에서 가중치의 합이 최소인 트리
* 정점 개수 V <10,000 / 간선의 개수 E <= 100,000
* 가중치는 음수일 수도 있다.
* 최소 스패닝 트리의 가중치 범위 -> 절대값 Integer.maxvalue 안에서 해결하라 같다.
* 가중치 최소 -> 이때 우선순위 큐
* 결국 모든 정점을 방문해야 한다 -> 방문 처리 하면서 계속해서 가중치의 합이 최소 -> 가중치 누적시키면서 전체 탐색
* 시작점이 따로 없다 -> 양방향으로 map을 만들어서 가중치 순으로 계속 정렬하며 그래프 방문
*
* */

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] arr = new int[e][3];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < e; i++) {
            map.putIfAbsent(arr[i][0], new ArrayList<>());
            map.putIfAbsent(arr[i][1], new ArrayList<>());
            map.get(arr[i][0]).add(new int[]{arr[i][1], arr[i][2]});
            map.get(arr[i][1]).add(new int[]{arr[i][0], arr[i][2]});
        }
//      간선 a : [간선b, 가중치]

        Queue<int[]> q = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        int[] visited = new int[v + 1];
        int count = 0;

//      시작점 1부터 그냥 해도 결국 정렬된다
        q.add(new int[] {1,-1,0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int cost = cur[2];

            if(visited[node] == 1) {
                continue;
            }

            visited[node] = 1;
            count +=cost;

            for( int[] next : map.get(node)) {
                int nextnode = next[0];
                int nextcost = next[1];
                if(visited[nextnode] == 0) {
                    q.add(new int[] {nextnode,node,nextcost});
                }
            }
        }

        bw.write(String.valueOf(count));



        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
