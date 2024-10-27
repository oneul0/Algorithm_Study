import java.util.*;
import java.io.*;

public class Main {

    static int n; //정점 갯수
    static int m; //간선 갯수
    static int k; //시작 정점
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1]; //좌표를 그대로 받아들이기 위해 +1
        visited = new boolean[n+1];
        sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) arr[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) { //간선 연결 상태 저장
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }
        for(int i=0;i<arr.length;i++) {
            Collections.sort(arr[i]);
        }
        dfs(k);
        visited = new boolean[n+1];
        sb.append("\n");
        bfs(k);
        System.out.println(sb);
    }

    //시작점을 변수로 받아 확인
    //출력 후 다음 연결점을 찾아 시작점을 변경하여 재호출
    public static void dfs(int idx) {
        visited[idx] = true;
        sb.append(idx+" "); //현재 방문한 값 저장
        for(int i: arr[idx]) { // i에 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들을 하나씩 할당
            if(!visited[i]) dfs(i); //다음에 방문할 노드값으로 바꾸어 재귀함수 실행
        }
    }

    public static void bfs(int idx) {
        visited[idx] = true;
        Queue<Integer> q = new LinkedList<>(); //시작점 Queue에 넣기
        q.add(idx);

        //Queue가 비어있게 될 때 까지 반복하고 방문 정점 확인
        //출력 후 Queue에 넣어 순서대로 확인
        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur+" ");
            for(int i: arr[cur]) {
                if(!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}