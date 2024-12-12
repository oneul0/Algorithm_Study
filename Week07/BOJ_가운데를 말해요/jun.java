import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 10^7
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 10^5 이하의 n
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());

            if (maxHeap.isEmpty() || m < maxHeap.peek()) {
                maxHeap.add(m);
            } else {
                minHeap.add(m);
            }
            change(maxHeap, minHeap);
            System.out.println(maxHeap.peek());
        }
    }

    public static void change(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        } else if (minHeap.size() + 1 < maxHeap.size()) {
            minHeap.add(maxHeap.remove());
        }
    }
}
