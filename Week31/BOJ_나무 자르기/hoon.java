import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, num = 0;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr=  new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			num = Math.max(num, arr[i]);
		}

		System.out.println(binarySearch(M));

	}

	public static long binarySearch(int target){
		long l = 0, r=num, m;
		while(l<=r){
			m = l + (r-l)/2;
			long sum = 0;
			for(int tree : arr){
				if(tree > m)
					sum += (tree-m);
			}
			if(sum < target){
				r = m - 1;
			}
			else {
				l = m + 1;
			}
		}
		return r;
	}
}
