import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<Integer> list = new ArrayList<>();
    public static void solution() throws IOException {

/*
*  노드 개수 n, 거리를 알고 싶은 쌍 개수 m
*  n-1 개의 노드 쌍 [노드1, 노드2, 거리]
*  거리를 알고 싶은 쌍 [노드1, 노드2] m개
*  노드 쌍을 받은 후 해당 목적 노드까지 가야한다 -> bfs
*  dfs를 m번 실행하는데 그때 시작, 끝 노드의 정보를 넣고
*
* */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

//      양방향 그래프 완성
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int node_one = Integer.parseInt(st.nextToken());
            int node_two = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            map.putIfAbsent(node_one, new ArrayList<>());
            map.putIfAbsent(node_two, new ArrayList<>());
            map.get(node_one).add(new int[]{node_two, length});
            map.get(node_two).add(new int[]{node_one, length});
        }

//      트리 시 bfs를 위해 방문 처리 해야한다
//      탐색마다 방문 처리 따로
        for(int i = 0; i < m; i++) {
            int[] visited = new int[n+1];
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bfs(start, end, visited, map);
        }

        for(int i = 0; i < m; i++) {
            bw.write(String.valueOf(list.get(i)));
            if(i != m-1) {
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static void bfs(int start, int end, int[] visited, Map<Integer, List<int[]>> map) throws IOException {
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[]{start, 0});

        visited[start] = 1;

        while(!q.isEmpty()) {
            int[] node = q.poll();
            int curr = node[0];
            int length = node[1];

            if(curr == end) {
                list.add(length);
                return;
            }

            for(int[] next : map.get(node[0])) {
                if(visited[next[0]] == 0) {
                    visited[next[0]] = 1;
                    q.add(new int[]{next[0], length+next[1]});
                }
            }

        }


    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
