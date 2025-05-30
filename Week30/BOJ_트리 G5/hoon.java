import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] tree;
	static int answer = 0, deleted;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = -1;
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) {
				root = i;
			} else {
				tree[parent].add(i);
			}
		}

		deleted = Integer.parseInt(br.readLine());

		if (deleted == root) {
			System.out.print(0);
		} else {
			dfs(root);
			System.out.print(answer);
		}
	}

	static void dfs(int node) {
		if (node == deleted) return;

		boolean hasChild = false;
		for (int child : tree[node]) {
			if (child != deleted) {
				hasChild = true;
				dfs(child);
			}
		}
		if (!hasChild) {
			answer++;
		}
	}
}
