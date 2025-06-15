import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		List<Pair> list = new ArrayList<>();
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		br.close();

		Collections.sort(list, (a, b) -> {
			if (a.e == b.e) return Integer.compare(a.s, b.s);
			return Integer.compare(a.e, b.e);
		});

		int count = 0;
		int end = 0;
		for (Pair p : list) {
			if (p.s >= end) {
				end = p.e;
				count++;
			}
		}
		System.out.println(count);
	}
}

class Pair {
	int s, e;
	public Pair(int s, int e) {
		this.s = s;
		this.e = e;
	}
}
