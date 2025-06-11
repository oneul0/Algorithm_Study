import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		String[] st = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st[i]);
		}
		Arrays.sort(arr);

		int ans = binarySearch();

		System.out.println(ans);
	}

	public static int binarySearch(){
		int low = 0, high = N-1, result = Integer.MAX_VALUE;
		while(low < high){
			int sum = arr[low] + arr[high];
			if(Math.abs(sum) < Math.abs(result)){
				result = sum;
			}
			if(sum < 0) low++;
			else high--;
		}
		return result;
	}
}
