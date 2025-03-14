import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());

    int[] blocks = new int[W];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < W; i++) {
      blocks[i] = Integer.parseInt(st.nextToken());
    }

    int totalWater = 0;
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < W; i++) {
      while (!stack.isEmpty() && blocks[stack.peek()] < blocks[i]) {
        int bottom = stack.pop(); //바닥에 깔린 블록
        if (stack.isEmpty()) break;

        int left = stack.peek(); //현재 위치의 왼쪽에 있는 벽
        int height = Math.min(blocks[left], blocks[i]) - blocks[bottom];
        int width = i - left - 1;
        totalWater += height * width;
      }
      stack.push(i);
    }

    bw.write(totalWater + "\n");
    bw.flush();
    br.close();
    bw.close();
  }
}
