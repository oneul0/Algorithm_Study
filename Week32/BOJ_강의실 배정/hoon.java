import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		List<Pair> list = new ArrayList<>();
		StringTokenizer st;
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		br.close();
		Collections.sort(list);

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		pq.offer(list.get(0).e);
		for(int i = 1; i<n; i++) {
			if(pq.peek() <= list.get(i).s){
				pq.remove();
				pq.offer(list.get(i).e);
			}
			else{
				pq.offer(list.get(i).e);
			}
		}

		System.out.println(pq.size());

	}
}

class Pair implements Comparable<Pair> {
	int s, e;
	public Pair(int s, int e) {
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(Pair o) {
		if(this.s == o.s) {
			return this.e - o.e;
		}
		return this.s - o.s;
	}
}
