import java.io.*;
import java.util.*;

public class Main {
	static class Flower implements Comparable<Flower> {
		int start, end;

		Flower(int sm, int sd, int em, int ed) {
			this.start = sm * 100 + sd;
			this.end = em * 100 + ed;
		}

		public int compareTo(Flower f) {
			if (this.start == f.start)
				return f.end - this.end;
			return this.start - f.start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Flower> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			list.add(new Flower(sm, sd, em, ed));
		}
		Collections.sort(list);

		int count = 0;
		int current = 301;
		int end = 1201;
		int index = 0;
		int maxEnd = 0;

		while (current < end) {
			boolean found = false;
			while (index < N && list.get(index).start <= current) {
				if (list.get(index).end > maxEnd) {
					maxEnd = list.get(index).end;
					found = true;
				}
				index++;
			}
			if (!found) {
				System.out.println(0);
				return;
			}
			current = maxEnd;
			count++;
		}

		System.out.println(count);
	}
}

//지는 날에는 꽃이 피어있지 않음 지는 날-1일 해야 꽃이 피어있는 기간
