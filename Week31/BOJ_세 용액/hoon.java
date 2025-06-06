import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);
		long min = Long.MAX_VALUE;
		long[] ans = new long[3];

		for(int i = 0; i < n - 2; i++) {
			int left = i + 1;
			int right = n - 1;

			while(left < right) {
				long sum = arr[i] + arr[left] + arr[right];
				if(Math.abs(sum) < min) {
					min = Math.abs(sum);
					ans[0] = arr[i];
					ans[1] = arr[left];
					ans[2] = arr[right];
				}

				if(sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}

		Arrays.sort(ans);
		for(long a : ans) {
			System.out.print(a + " ");
		}
	}
}
