import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] A = new int[n];
		Set<Integer> B = new HashSet<>();
		List<Integer> result = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		br.close();
		for(int i = 0; i < m; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}

		for(int i = 0; i < n; i++) {
			if(B.contains(A[i])) continue;
			result.add(A[i]);
		}
		System.out.println(result.size());
		if(result.size() > 0){
			// result.stream().sorted().forEach(
			// 	o -> System.out.print(o+" ")
			// );
			Collections.sort(result);
			for(int i : result){
				System.out.print(i+" ");
			}
		}

	}
}
