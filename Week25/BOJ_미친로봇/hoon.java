import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  static int n;
  static double[] cardinal = new double[4]; // E, W, S, N
  static boolean[][] visited = new boolean[30][30];
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static double result = 0.0;

  public static void init() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    for (int i = 0; i < 4; i++) {
      cardinal[i] = Double.parseDouble(st.nextToken())*0.01;
    }
  }

  public static void simulation(int depth, double current, int cx, int cy) {
    if (depth == n) {
      result += current;
      return;
    }

    visited[cx][cy] = true;
    for (int i = 0; i < 4; i++) {
      int nx = cx + dx[i];
      int ny = cy + dy[i];

      if(nx >= 0 && ny >= 0&& nx<30&&ny<30 && !visited[nx][ny]) {
        visited[nx][ny] = true;
        simulation(depth + 1, current*cardinal[i], nx, ny);
        visited[nx][ny] = false;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    init();

    simulation(0, 1.0, 15, 15);

    System.out.printf("%.9f%n", result);
  }
}

//끝까지 갔다면 단순한 경우
//끝까지 가기 전에 같은 곳으로 이동했다면 단순하지 않은 경우(가지치기)