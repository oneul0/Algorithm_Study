import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 최대 힙과 최소 힙 초기화
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 중간값 이하
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 중간값 이상

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 최대 힙과 최소 힙에 숫자를 추가
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // 힙 크기 조정 (최대 힙의 크기가 최소 힙보다 항상 1 크거나 같도록 유지)
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            // 중간값 출력 (최대 힙의 루트가 중간값)
            bw.write(maxHeap.peek() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
