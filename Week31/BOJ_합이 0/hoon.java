import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr;
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		long ans = 0;
		int left, right;
		for(int i = 0; i<n-2; i++) {
			left = i+1;
			right = n-1;

			while(left < right) {
				int tmp = arr[i] + arr[left] + arr[right];
				if(tmp == 0){
					//정렬되어 있고, 구간의 양쪽 값이 같다면 구간의 모든 값이 같음이 보장됨
					if(arr[left] == arr[right]){
						int len = right - left+1;
						ans += (len*(len-1)/2);
						break;
					}
					else{
						int lCnt = 1, rCnt = 1;

						while(left < right && arr[left] == arr[left+1]) {
							lCnt++;
							left++;
						}
						while(left < right && arr[right] == arr[right-1]) {
							rCnt++;
							right--;
						}

						ans+=lCnt*rCnt;
						left++;
						right--;
					}
				}
				else if(tmp < 0){
					left++;
				}
				else{
					right--;
				}
			}
		}

		System.out.println(ans);
	}
}
