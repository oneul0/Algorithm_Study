import java.io.*;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		for(int i = 0; i<N; i++){
			int num = Integer.parseInt(br.readLine());
			minHeap.offer(num);
		}

		int ans = 0;
		for(int i = 0; i<N-1; i++){
			int A = minHeap.remove();
			int B = minHeap.remove();
			ans += (A+B);
			minHeap.offer(A+B);
		}
		System.out.print(ans);
	}
}


//A, B의 차이가 크지 않아야 함
//minheap, maxheap 나눌 필요 없음