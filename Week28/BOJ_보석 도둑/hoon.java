import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N, K; //보석 개수, 가방 개수
  static PriorityQueue<Jewel> pq = new PriorityQueue<>(
      (n1, n2) -> n1.weight - n2.weight
  );
  static PriorityQueue<Integer> C = new PriorityQueue<>(
      (n1, n2) -> n1 - n2
  );//각 가방에 담을 수 있는 최대 무게
  static PriorityQueue<Jewel> values = new PriorityQueue<>(
      (n1, n2) -> n1.value==n2.value ? n1.weight - n2.weight : n2.value-n1.value
  );

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      pq.offer(new Jewel(m, v));
    }

    for(int i = 0; i<K; i++){
      C.offer(Integer.parseInt(br.readLine()));
    }


    long ans = 0;
    while (!C.isEmpty()) {
      int bag = C.remove();
      while(!pq.isEmpty()){
        if (bag<pq.peek().weight) break;
        values.offer(pq.remove());
      }
      if(!values.isEmpty()){
        ans += values.remove().value;
      }
    }
    System.out.printf(ans + "");
  }

  static class Jewel {
    int weight, value;

    Jewel(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
  }

}
