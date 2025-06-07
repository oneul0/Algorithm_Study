import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		int left = 0;
		int right = N - 1;

		long min = Long.MAX_VALUE;
		long ansLeft = arr[left], ansRight = arr[right];

		while (left < right) {
			long sum = arr[left] + arr[right];

			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				ansLeft = arr[left];
				ansRight = arr[right];
			}

			if (sum < 0) left++;
			else right--;
		}

		System.out.print(ansLeft + " " + ansRight);
	}
}
