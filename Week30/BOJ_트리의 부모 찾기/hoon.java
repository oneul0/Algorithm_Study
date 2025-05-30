import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static ArrayList<ArrayList<Integer>> gr = new ArrayList<>();
	static int[] parent;
	static boolean[] visited;

	public static void dfs(int current, int par){
		visited[current] = true;
		parent[current] = par;

		for(int next : gr.get(current)){
			if (!visited[next]) {
				dfs(next, current);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			gr.add(new ArrayList<>());
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			gr.get(a).add(b);
			gr.get(b).add(a);
		}

		dfs(1, 0);

		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}
}
