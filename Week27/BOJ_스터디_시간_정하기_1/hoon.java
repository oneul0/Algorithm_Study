import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int[] times = new int[100001];
  static int N, windowSize;

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    windowSize = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      int K = Integer.parseInt(br.readLine());
      for (int j = 0; j < K; j++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        times[a]++;
        times[b]--;
      }
    }

    for (int i = 1; i < 100001; i++) {
      times[i] += times[i - 1];
    }

    int[] prefixSum = new int[100001];
    prefixSum[0] = times[0];
    for (int i = 1; i < 100001; i++) {
      prefixSum[i] = prefixSum[i - 1] + times[i];
    }

    int max = prefixSum[windowSize-1], start= 0;
    for (int i = 1; i <= 100000 - windowSize; i++) {
      if(prefixSum[i+windowSize-1] - prefixSum[i-1] > max){
        start = i;
      }
      max = Math.max(prefixSum[i+windowSize-1] - prefixSum[i-1], max);
    }

    System.out.print(start + " " + (start + windowSize));
  }
}
