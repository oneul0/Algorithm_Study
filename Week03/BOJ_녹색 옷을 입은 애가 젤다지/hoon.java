import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] map, d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        int cnt = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] s = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(s[j]);
                }
            }

            // Dijkstra
            PriorityQueue<Cur> pq = new PriorityQueue<>();
            int[][] loss = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(loss[i], Integer.MAX_VALUE);
            }

            loss[0][0] = map[0][0];
            pq.offer(new Cur(0, 0, map[0][0]));

            while (!pq.isEmpty()) {
                Cur cur = pq.poll();

                if (cur.coin > loss[cur.x][cur.y]) continue;

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + d[i][0];
                    int ny = cur.y + d[i][1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    int newCost = cur.coin + map[nx][ny];
                    if (newCost < loss[nx][ny]) {
                        loss[nx][ny] = newCost;
                        pq.offer(new Cur(nx, ny, newCost));
                    }
                }
            }

            bw.write("Problem " + cnt + ": " + loss[N - 1][N - 1] + "\n");
            cnt++;
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class Cur implements Comparable<Cur> {
    int x, y, coin;

    Cur(int x, int y, int coin) {
        this.x = x;
        this.y = y;
        this.coin = coin;
    }

    @Override
    public int compareTo(Cur o) {
        return this.coin - o.coin;
    }
}
