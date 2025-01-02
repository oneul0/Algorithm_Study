import java.io.*;
import java.util.*;

public class Main {

    static int[] y_move = {1,-1,0,0};
    static int[] x_move = {0,0,-1,1};
    static int y = 0;
    static int x = 0;
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int[][] map = new int[y][x];
        for(int i = 0; i < y; i++) {
            String line = br.readLine();
            for(int j = 0; j < x; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[]{0,0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curry = cur[0];
            int currx = cur[1];
//            System.out.println("curry: " + curry + " currx: " + currx);
            int move = map[curry][currx];

            for(int i = 0; i < 4; i++) {
                int ny = curry + y_move[i];
                int nx = currx + x_move[i];
                if(isValid(ny, nx) && map[ny][nx] == 1) {
//                    System.out.println("ny = " + ny + ", nx = " + nx);
                    q.add(new int[]{ny, nx});
                    map[ny][nx] += move;
                }
            }
        }

//        System.out.println(map[y-1][x-1]);
        bw.write(String.valueOf(map[y-1][x-1]));


        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }

    public static boolean isValid(int ny, int nx ) {
        return ny < y && ny >= 0 && nx < x && nx >= 0;
    }
}
