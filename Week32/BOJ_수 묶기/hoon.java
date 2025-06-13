import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		boolean zeroExists = false;

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) zeroExists = true;
			arr[i] = num;
		}

		Arrays.sort(arr);

		int ans = 0;

		// 음수를 처리
		int left = 0;
		while (left + 1 < n && arr[left] < 0) {
			if (arr[left + 1] <= 0) {
				ans += arr[left] * arr[left + 1];
				left += 2;
			} else {
				break;
			}
		}

		// 0으로 음수 무효화
		if (left < n && arr[left] < 0) {
			if (left + 1 >= n || arr[left + 1] > 0) {
				if (zeroExists) {
					left++;
				} else {
					ans += arr[left];
					left++;
				}
			}
		}

		//양수를 처리
		int right = n - 1;
		while (right > 0 && arr[right] > 1) {
			if (arr[right - 1] > 1) {
				ans += arr[right] * arr[right - 1];
				right -= 2;
			} else {
				break;
			}
		}

		for (int i = left; i <= right; i++) {
			ans += arr[i];
		}

		System.out.println(ans);
	}
}
