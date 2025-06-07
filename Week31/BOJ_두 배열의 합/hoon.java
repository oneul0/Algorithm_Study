import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{

		long T = Long.parseLong(br.readLine());

		int n = Integer.parseInt(br.readLine());
		int[] arrA = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		int[] arrB = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=1; i<n; i++) {
			arrA[i] += arrA[i-1];
		}
		for(int i=1; i<m; i++) {
			arrB[i] += arrB[i-1];
		}

		int aSize = n*(n+1)/2;
		int bSize = m*(m+1)/2;
		long[] aSum = new long[aSize];
		int idx=0;
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				int av = arrA[j];
				if(i>0) av -= arrA[i-1];
				aSum[idx++] = av;
			}
		}

		long[] bSum = new long[bSize];
		idx=0;
		for(int i=0; i<m; i++) {
			for(int j=i; j<m; j++) {
				int bv = arrB[j];
				if(i>0) bv -= arrB[i-1];
				bSum[idx++] = bv;
			}
		}

		Arrays.sort(aSum);
		Arrays.sort(bSum);
		int left =0;
		int right = bSize-1;
		long cnt=0;
		while(left<aSize&& right>-1) {
			long asv = aSum[left], bsv = bSum[right];
			long sum = asv + bsv;
			if(sum == T) {
				long acnt =0, bcnt =0;
				while(left<aSize && asv == aSum[left]) {
					left++;
					acnt++;
				}

				while(right>-1 && bsv == bSum[right]) {
					right--;
					bcnt++;
				}
				cnt += acnt * bcnt;
			}
			if(sum> T) {
				right--;
			}else if(sum< T) {
				left++;
			}
		}
		System.out.println(cnt);

	}
}