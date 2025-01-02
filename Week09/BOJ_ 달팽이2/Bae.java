import java.io.*;
import java.util.*;

public class Main {
    static int[][] movement = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };
    public static void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int y = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());


    int[][] map = new int[y][x];


    int direction =0;

    int[] start = {0,0};
    int end = y*x;

    int count = 0;
    int answer = 0;
    Deque<int[]> q = new ArrayDeque<>();

    q.add(start);

    while(!q.isEmpty()) {
        if(count+1 >= end) break;
        count++;

        int[] curr = q.poll();
        map[curr[0]][curr[1]] = 1;
//        System.out.println(curr[0] + " " + curr[1]);
        int[] move = movement[direction];

        int move_y = curr[0]+move[0];
        int move_x = curr[1]+move[1];



        if(!isValid(y,x,move_y,move_x) || map[move_y][move_x] == 1  ) {
//            System.out.println("방향전환");
            direction = (direction+1)%4;
            move = movement[direction];
            move_y = curr[0]+move[0];
            move_x = curr[1]+move[1];
            q.add(new int[]{move_y,move_x});
            answer++;
        } else {
            q.add(new int[]{move_y,move_x});
        }
    }



    bw.write(String.valueOf(answer));
    bw.flush();
    bw.close();




    }


    public static void main(String[] args) throws IOException {
        solution();
    }

    public static boolean isValid(int y, int x, int nexty, int nextx) {
        return nexty >= 0 && nexty < y && nextx >= 0 && nextx < x;
    }
}
