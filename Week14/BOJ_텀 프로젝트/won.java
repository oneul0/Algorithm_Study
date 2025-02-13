
import java.util.*;
import java.io.*;

public class Main {
    static int[] tc;
    static boolean[] visited;
    static boolean[] finished;
    static int count;// 팀에 속한 학생수

    public static void dfs(int start) {
        visited[start] = true;

        // 현재 노드가 선택한 학생
        int next = tc[start];

        if(!visited[next]) { // 방문하지 않았다면 dfs 수행
            dfs(next);
        } else if( !finished[next]) {
            // 방문은 했지만 dfs가 종료되지 않았다면, 사이클 발견

            int cur = next;
            while(cur != start) {
                // 사이클 내의 학생 수 카운드
                count++;
                cur = tc[cur];
            }
            count++; // 마지막 노드 포함
        }
        finished[start] = true; // 현재 노드의 탐색 완료
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // T 테스트 케이스 개수
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            tc = new int[n+1];
            visited = new boolean[n+1];
            finished = new boolean[n+1];
            count = 0;

            String[] input = br.readLine().split(" ");
            for(int j=1; j<n+1; j++){
                tc[j] = Integer.parseInt(input[j-1]);
            }

            // 모든 학생을 순회하면서 dfs 탐색 수행
            for(int j=1; j<n+1; j++){
                if(!visited[j]) {
                    dfs(j);
                }
            }
        }
    }
}
