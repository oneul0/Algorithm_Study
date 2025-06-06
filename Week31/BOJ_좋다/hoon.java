import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr;
	static HashSet<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		String[] st = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st[i]);
			set.add(arr[i]);
		}

		Arrays.sort(arr);
		int ans = 0;
		for(int i = 0; i<n; i++){
			ans += tp(i);
		}

		System.out.println(ans);
	}

	public static int tp(int idx){
		int low = 0;
		int high = arr.length - 1;

		while(low < high){
			int sum = arr[low] + arr[high];
			if(sum < arr[idx] || low == idx){
				low++;
			}
			else if(sum > arr[idx] || high == idx){
				high--;
			}
			else{
				return 1;
			}
		}
		return 0;
	}

}
