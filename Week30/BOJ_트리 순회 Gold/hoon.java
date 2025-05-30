import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, count = 0;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		int[][] tree = new int[N+1][2];

		for(int i = 0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			tree[node][0] = Integer.parseInt(st.nextToken());
			tree[node][1] = Integer.parseInt(st.nextToken());
		}

		for(int i = 1; tree[i][1] != -1; i=tree[i][1]){
			count++;
		}

		System.out.print((N-1)*2 - count);

	}
}
