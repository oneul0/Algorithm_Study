import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M;
	static int start, goal;
	static List<int[]> gr = new ArrayList<>();
	static int[] parents;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			gr.add(new int[]{a,b,c});
			gr.add(new int[]{b,a,c});
		}

		st = new StringTokenizer(br.readLine());
		br.close();
		start = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());

		System.out.printf("%d", calcMaxWeight());
	}

	static int calcMaxWeight() {
		parents = new int[N+1];

		for(int i = 1; i<=N; i++){
			parents[i] = i;
		}
		gr.sort((a, b) -> b[2] - a[2]);
		for(int[] nxt : gr){
			int a = nxt[0];
			int b = nxt[1];
			int c = nxt[2];
			union(a, b);

			if(find(start) == find(goal)) {
				return c;
			}
		}
		return 0;
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b){
			parents[b] = a;
		}
	}

	static int find(int node) {
		if(parents[node] != node){
			parents[node] = find(parents[node]);
		}
		return parents[node];
	}

}