import java.io.*;
import java.util.*;

public class Main {
  static int N, M, H;
  static boolean[][] ladder = new boolean[31][11];
  static int answer = 4;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 세로선 개수
    M = Integer.parseInt(st.nextToken()); // 가로선 개수
    H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치 수

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      ladder[a][b] = true;
    }

    playGame(0, 1);

    System.out.println(answer > 3 ? -1 : answer);
  }

  //가로선 놓는 횟수, 세로선 번호
  static void playGame(int count, int x) {
    if (count >= answer) return;
    if (check()) {
      answer = count;
      return;
    }
    if (count == 3) return;

    for (int i = x; i <= H; i++) {
      for (int j = 1; j < N; j++) {
        if (ladder[i][j] || ladder[i][j - 1] || ladder[i][j + 1]) continue;

        ladder[i][j] = true;
        playGame(count + 1, i);
        ladder[i][j] = false;
      }
    }
  }

  static boolean check() {
    for (int i = 1; i <= N; i++) {
      int pos = i;
      for (int j = 1; j <= H; j++) {
        if (ladder[j][pos]) pos++;
        else if (ladder[j][pos - 1]) pos--;
      }
      if (pos != i) return false;
    }
    return true;
  }
}
