import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map.get(a).add(new int[]{b, c});
            map.get(b).add(new int[]{a, c});
        }

        int answer = 0;
        int dep = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n+1];
        pq.add(new int[] {1,0});
        while (!pq.isEmpty() && dep < n) {
            int[] out = pq.remove();
            int node = out[0], cost = out[1];
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            answer += cost;
            dep++;

            for (int[] next : map.get(node)) {
                if (!visited[next[0]]) {
                    pq.add(new int[] {next[0], next[1]});
                }
            }
        }
        System.out.println(answer);
    }
}
