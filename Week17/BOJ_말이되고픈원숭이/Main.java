import java.util.*;
import java.io.*;
public class Main {

    static int[][] arr;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // horse
    static int[] hdx = {2,2,-2, -2, 1, 1,-1,-1};
    static int[] hdy = {1,-1, 1, -1, 2,-2, 2, -2};

    // target
    static int targetX=0; static int targetY=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // input
        int k=Integer.parseInt(br.readLine());
        String[] wh = br.readLine().split(" ");
        int w = Integer.parseInt(wh[0]);
        int h = Integer.parseInt(wh[1]);

        arr = new int[h][w];
        for(int i=0;i<h;i++) {
            String[] s = br.readLine().split(" ");
            for(int j=0; j<w; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        targetX = h-1;
        targetY = w-1;

        bw.write(bfs(0,0, k) + "\n");
        bw.flush();
        bw.close();

    }

    public static boolean isValid(int[][] grid,int row, int col) {
        int H = grid.length;    // 세로 길이
        int W = grid[0].length; // 가로 길이
        return row >= 0 && row < H && col >= 0 && col < W && grid[row][col] == 0;
    }

    private static int bfs(int x, int y, int k){
        int H = arr.length;
        int W = arr[0].length;

        boolean[][][] visited = new boolean[H][W][k+1];// (x, y, 남은 점프 횟수)
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, k, 0});// {현재 X, 현재 Y, 남은 점프 횟수, 이동 횟수}
        visited[x][y][k] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0], curY = cur[1], jumpsLeft = cur[2], steps = cur[3];

            // 도착점 도달
            if(curX == targetX && curY == targetY){ return steps;}

            // 1. 원숭이 이동 (상하좌우)
            for (int d = 0; d < 4; d++) {
                int nextX = curX + dx[d];
                int nextY = curY + dy[d];

                if (isValid(arr, nextX, nextY) && !visited[nextX][nextY][jumpsLeft]) {
                    visited[nextX][nextY][jumpsLeft] = true;
                    q.add(new int[]{nextX, nextY, jumpsLeft, steps + 1});
                }
            }

            // 2. 말 이동 -> 점프 횟수가 남아있을 때만 가능하다.
            if(jumpsLeft>0){
                for(int d=0; d<8;d++){
                    int nextX = curX + hdx[d];
                    int nextY = curY + hdy[d];

                    if (isValid(arr, nextX, nextY) && !visited[nextX][nextY][jumpsLeft - 1]) {
                        visited[nextX][nextY][jumpsLeft - 1] = true;
                        q.add(new int[]{nextX, nextY, jumpsLeft - 1, steps + 1});
                    }
                }
            }

        }
        return -1; // 경로 없다
    }

}
