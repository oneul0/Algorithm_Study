import java.io.*;
import java.util.*;

public class Main {
    static int[] y_move = {1,-1,0,0};
    static int[] x_move = {0,0,1,-1};

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = 0;

        while(true) {
            int n = Integer.parseInt(br.readLine());

            if(n == 0) break;
            if(count != 0) {
                bw.newLine();
            }

            count++;


            int[][] arr = new int[n][n];

            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

//          0이 나오기 전까지 계속 진행한다.
//          현재 int[][]에 동굴에서의 도둑루피의 크기가 담겨져 있다.
//          최소비용 -> 다익스트라? or 완전 bfs
//          1. 우선순위 큐를 통해 해보자

            Queue<int[]> q = new ArrayDeque<>();
            int[][] minCost = new int[n][n];


            for(int[] temp : minCost) {
                Arrays.fill(temp,Integer.MAX_VALUE);
            }

            q.add(new int[]{0,0,arr[0][0]});
            minCost[0][0] = arr[0][0];


            while(!q.isEmpty()) {
                int[] curr = q.poll();
                int curr_y = curr[0];
                int curr_x = curr[1];
                int curr_cost = curr[2];
                for (int i = 0; i < 4; i++) {
                    int next_y = curr_y + y_move[i];
                    int next_x = curr_x + x_move[i];
                    if(isValid(next_y,next_x, n)) {
                        int next_cost = curr_cost + arr[next_y][next_x];

                        if(next_cost < minCost[next_y][next_x]) {
                            minCost[next_y][next_x] = next_cost;
                            q.add(new int[]{next_y,next_x,curr_cost+arr[next_y][next_x]});
                        }
                    }
                }
            }


            bw.write("Problem " +count+": "+minCost[n-1][n-1]);



        }




        bw.flush();
        bw.close();
    }





    public static void main(String[] args) throws IOException {
        solution();
    }

    public static boolean isValid(int y, int x, int n) {
        return x>=0 && x<n && y>=0 && y<n;
    }
}
