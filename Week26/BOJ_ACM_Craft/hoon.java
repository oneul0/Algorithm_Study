import java.util.*;
import java.io.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int T, N, K, W;
  static int[] indegrees, costs;
  static List<List<Integer>> gr;
  static Queue<Integer> processNode;
  static StringTokenizer st;
  public static void init() throws IOException {
    gr = new ArrayList<>();
    processNode = new LinkedList<>();

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    indegrees = new int[N+1];
    costs = new int[N+1];

    st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= N; i++) {
      costs[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 0; i <= N; i++) {
      gr.add(new ArrayList<>());
    }

    for(int i = 1; i<=K; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      gr.get(a).add(b);
      indegrees[b]++;
    }
    W = Integer.parseInt(br.readLine());
  }

  public static int topologicalSort() {
    int[] memo = new int[N+1];

    //indegree 0인 노드 큐에 삽입
    for(int i = 1; i <= N; i++) {
      if(indegrees[i] == 0) {
        processNode.add(i);
        memo[i] = costs[i];
      }
    }

    //큐에 삽입된 노드들 빼서 경로 삭제(연결된 노드들의 indegree -1)
    while(!processNode.isEmpty()) {
      int cur = processNode.remove();

      for(int next : gr.get(cur)) {
        indegrees[next]--;
        memo[next] = Math.max(memo[next], memo[cur] + costs[next]); //선행 작업이 모두 완료되어야 하므로
        if(indegrees[next] == 0) {
          processNode.add(next);
        }
      }
    }

    return memo[W];
  }

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());
    for(int i = 0; i<T; i++){
      init();

      System.out.println(topologicalSort());
    }
    br.close();
  }
}
