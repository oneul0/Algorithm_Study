import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] arr;
	static int N, M, ans = 0, max = 0;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		bs();
		System.out.print(ans);
	}

	public static void bs() {
		int low= 1 , high = max;
		while(low <= high) {
			int count = 0;
			int mid = (low+high)>>>1;

			for(int j = 0; j < N; j++) {
				count += arr[j]/mid;
			}

			if(count >= M) {
				ans = Math.max(ans, mid);
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
	}
}
