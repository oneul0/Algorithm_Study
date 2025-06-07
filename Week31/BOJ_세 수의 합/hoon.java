import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] U;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		U = new int[N];

		for(int i = 0; i < N; i++) {
			U[i] = Integer.parseInt(br.readLine());
		}

		int[] sum = new int[(N*(N+1)) / 2];
		int idx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				sum[idx++] = U[i] + U[j];
			}
		}

		Arrays.sort(sum);

		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int target = U[i] - U[j];
				if(Arrays.binarySearch(sum, target) >= 0) {
					ans = Math.max(ans, U[i]);
				}
			}
		}
		System.out.print(ans);
	}
}

