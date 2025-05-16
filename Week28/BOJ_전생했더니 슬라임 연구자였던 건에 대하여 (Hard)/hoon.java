import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BigInteger MOD = BigInteger.valueOf(1_000_000_007);

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<BigInteger> pq = new PriorityQueue<>();

			for (int i = 0; i < N; i++) {
				pq.offer(new BigInteger(st.nextToken()));
			}

			if (pq.size() == 1) {
				System.out.println(1);
				continue;
			}

			BigInteger ans = BigInteger.ONE;

			while (pq.size() > 1) {
				BigInteger a = pq.remove();
				BigInteger b = pq.remove();
				BigInteger product = a.multiply(b).mod(MOD);
				ans = ans.multiply(product).mod(MOD);
				pq.offer(a.multiply(b));
			}

			System.out.println(ans);
		}
	}
}
