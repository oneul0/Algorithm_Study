package Week20.BOJ_주사위굴리기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N, L, R, days = 0;
  static int[][] map;
  static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    map = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    // init---------------

    while (true) {
      boolean moved = false;
      boolean[][] visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!visited[i][j]) {
            if (makeUnite(i, j, visited)) {
              moved = true;
            }
          }
        }
      }

      if (!moved) break;
      days++;
    }

    bw.write(days + "\n");
    bw.flush();
    br.close();
    bw.close();
  }

  static boolean makeUnite(int sx, int sy, boolean[][] visited) {
    int population = 0;
    List<int[]> unite = new ArrayList<>();
    Queue<int[]> q = new ArrayDeque<>();

    q.offer(new int[]{sx, sy});
    unite.add(new int[]{sx, sy});
    visited[sx][sy] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0], y = cur[1];
      population += map[x][y];

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i], ny = y + dy[i];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
        if (openBorder(map[x][y], map[nx][ny])) {
          visited[nx][ny] = true;
          q.offer(new int[]{nx, ny});
          unite.add(new int[]{nx, ny});
        }
      }
    }

    if (unite.size() == 1) return false;

    int newPopulation = population / unite.size();
    for (int[] pos : unite) {
      map[pos[0]][pos[1]] = newPopulation;
    }
    return true;
  }

  static boolean openBorder(int cur, int other) {
    int diff = Math.abs(cur - other);
    return diff >= L && diff <= R;
  }
}
