import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int N, M;
  static int[][] city;
  static List<int[]> homes = new ArrayList<>();
  static List<int[]> chickens = new ArrayList<>();
  static boolean[] selected;
  static int minDistance = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    city = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        city[i][j] = Integer.parseInt(st.nextToken());
        if (city[i][j] == 1) homes.add(new int[]{i, j});
        else if (city[i][j] == 2) chickens.add(new int[]{i, j});
      }
    }

    selected = new boolean[chickens.size()];
    comb(0, 0);

    bw.write(minDistance + "\n");
    bw.flush();
    bw.close();
  }

  static void comb(int idx, int count) {
    if (count == M) {
      minDistance = Math.min(minDistance, calcChickenDistance());
      return;
    }
    for (int i = idx; i < chickens.size(); i++) {
      selected[i] = true;
      comb(i + 1, count + 1);
      selected[i] = false;
    }
  }

  static int calcChickenDistance() {
    int sum = 0;
    for (int[] home : homes) {
      int hx = home[0];
      int hy = home[1];
      int dist = Integer.MAX_VALUE;

      for (int i = 0; i < chickens.size(); i++) {
        if (selected[i]) {
          int[] chick = chickens.get(i);
          int cx = chick[0], cy = chick[1];
          dist = Math.min(dist, Math.abs(hx - cx) + Math.abs(hy - cy));
        }
      }
      sum += dist;
    }
    return sum;
  }
}
