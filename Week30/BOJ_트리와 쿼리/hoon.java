import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int[] subtreeSize;
	static boolean[] visited;

	public static int dfs(int node) {
		visited[node] = true;
		int result = 1;

		for (int child : tree.get(node)) {
			if (!visited[child]) {
				result += dfs(child);
			}
		}
		subtreeSize[node] = result;
		return result;
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		subtreeSize = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			tree.get(u).add(v);
			tree.get(v).add(u);
		}

		dfs(R);

		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			System.out.println(subtreeSize[q]);
		}
	}
}
