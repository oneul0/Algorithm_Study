import java.io.*;
import java.util.*;

public class Main {

    static int[] x_move= {1,-1,0,0};
    static int[] y_move= {0,0,1,-1};
    public static void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }


        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1) {
                    list.add(bfs(n, map, i, j));
                }
            }
        }

        Collections.sort(list);
        bw.write(String.valueOf(list.size()));
        bw.newLine();
        for(int i = 0; i < list.size(); i++) {
            bw.write(String.valueOf(list.get(i)));
            if(i != list.size() - 1) {
                bw.newLine();
            }
        }


        bw.flush();
        bw.close();

    }
    public static int bfs(int n,int[][] map, int y, int x) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y,x});
        map[y][x] = 2;
        int count = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cury = cur[0];
            int curx = cur[1];
            for(int i=0; i<4; i++) {
                int nexty = cury + y_move[i];
                int nextx = curx + x_move[i];
                if(isValid(nexty,nextx,n) && map[nexty][nextx] == 1) {
                    q.add(new int[]{nexty,nextx});
                    map[nexty][nextx] = 2;
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isValid(int nexty,int nextx, int n) {
        return nexty >= 0 && nexty <n && nextx >= 0 && nextx < n;
    }
    public static void main(String[] args) throws IOException {
        solution();
    }

}
