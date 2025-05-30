import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, M;

	static ArrayList<ArrayList<Pair>> tree = new ArrayList<>();
	static int[][] distance;

	public static class Pair {
		int to, dist;
		public Pair(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}

	public static void bfs(int from) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		visited[from] = true;
		q.offer(from);

		while(!q.isEmpty()){
			int cur = q.remove();

			for(Pair next : tree.get(cur)){
				if(!visited[next.to]){
					visited[next.to] = true;
					distance[from][next.to] = (distance[from][cur] + next.dist);
					distance[next.to][from] = distance[from][next.to];
					q.offer(next.to);
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance = new int[N+1][N+1];

		for(int i = 0; i<= N; i++){
			tree.add(new ArrayList<>());
		}

		for(int i = 0; i < N-1; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			tree.get(a).add(new Pair(b, dist));
			tree.get(b).add(new Pair(a, dist));
		}

		for(int i = 0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(distance[a][b] == 0) bfs(a);
			System.out.println(distance[a][b]);
		}
	}
}
