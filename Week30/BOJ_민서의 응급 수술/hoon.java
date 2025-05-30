import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, count = 0;
	static StringTokenizer st;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N+1];
		Arrays.fill(parents,-1);

		for(int i = 0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(!union(a,b)){
				count++;
			}
		}

		int setCount = 0;
		for(int i = 1; i<=N; i++){
			if(parents[i] < 0){
				setCount++;
			}
		}

		count += (setCount-1);
		System.out.print(count);
	}

	public static boolean union(int a, int b){
		a = find(a);
		b = find(b);

		if(a== b){
			return false;
		}
		parents[a] = b;
		return true;
	}

	public static int find(int x){
		if(parents[x] == -1){
			return x;
		}
		return parents[x] = find(parents[x]);
	}
}

//유니온 파인드