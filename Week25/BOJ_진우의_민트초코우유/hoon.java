import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int N, M, H, maxMincho;
  static int[] start;
  static List<int[]> milks = new ArrayList<>();
  static int[][] town;
  static boolean[] drank;
  public static void init() throws Exception {
    try{
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      town = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          int val = Integer.parseInt(st.nextToken());
          if (val == 1) {
            start = new int[]{i, j, M};
          } else if (val == 2) {
            milks.add(new int[]{i, j});
          }
        }
      }
      drank = new boolean[milks.size()];
    } finally {
      br.close();
    }
  }

  public static void findMintchoco(int[] cur, int mincho, boolean[] drank) {
    int homeDist = Math.abs(start[0] - cur[0]) + Math.abs(start[1] - cur[1]);
    if (cur[2] >= homeDist) {
      maxMincho = Math.max(maxMincho, mincho);
    }

    for (int i = 0; i < milks.size(); i++) {
      if (drank[i]) continue;

      int[] milk = milks.get(i);
      int dist = Math.abs(milk[0] - cur[0]) + Math.abs(milk[1] - cur[1]);

      if (cur[2] >= dist) {
        drank[i] = true;
        findMintchoco(new int[]{milk[0], milk[1], cur[2] - dist + H},mincho + 1, drank);
        drank[i] = false;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    init();
    findMintchoco(start, 0, new boolean[milks.size()]);
    bw.write(maxMincho+"");
    bw.flush();
    bw.close();
  }
}
