import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	public static String findTrees(int nodes, ArrayList<ArrayList<Integer>> graph) {
		boolean[] visited = new boolean[nodes + 1];
		int count = 0;
		for(int i = 1; i <= nodes; i++) {
			if(!visited[i]) {
				if(dfs(i, 0, visited, graph)) {
					count++;
				}
			}
		}

		if(count > 1){
			return "A forest of "+count+" trees.";
		}
		else if(count == 1){
			return "There is one tree.";
		}
		else{
			return "No trees.";
		}
	}

	/**
	 * 트리를 순회하며 방문처리 하는 함수*/
	public static boolean dfs(int cur, int parent, boolean[] visited, ArrayList<ArrayList<Integer>> graph) {
		visited[cur] = true;

		for (int next : graph.get(cur)) {
			if (!visited[next]) {
				if (!dfs(next, cur, visited, graph)) {
					return false;
				}
			} else if (next != parent) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int caseNum = 0;
		while(true) {
			caseNum++;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			if(n == 0 && m == 0) break;

			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			for(int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}

			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			System.out.printf("Case %d: %s\n", caseNum, findTrees(n, graph));
		}
	}
}
