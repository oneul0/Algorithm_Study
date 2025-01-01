import java.io.*;
import java.util.*;

public class Main {
    static int[][] movement = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    public static void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    int n = Integer.parseInt(br.readLine());
    int find_num = Integer.parseInt(br.readLine());

    /*
    * 찾고자하는 num의 좌표는 1,1기준 -> 정답 시 +1씩, (y,x) 순
    * (n/2, n/2) 부터 시작
    * 1,1,2,2,3,3,4,4,5,5,6,6
    * 애초에 규칙이 정해져있어 방문처리 안해도된다.
    *
    * */

    Deque<int[]> q = new ArrayDeque<>();
    int[][] map = new int[n][n];
    int num = 0;
    q.add(new int[]{n/2, n/2});
    int direction = 0;
    int length = 1;
    int count = 0;
    int[] find_index = new int[2];

    while(!q.isEmpty()) {
//      숫자 삽입
        num++;
        count++;
        int[] index = q.poll();
        int y = index[0];
        int x = index[1];
        map[y][x] = num;
//        System.out.println(y+" "+x);
        if(num == find_num) {
            find_index[0] = y+1;
            find_index[1] = x+1;
        }
//      이동
//      상, 하 일때 이동 거리 증가

        if(y == 0 && x == 0) break;
//        System.out.println("length : "+length + " count : "+count);
        int[] move = movement[direction];
        int move_y = move[0];
        int move_x = move[1];
        q.add(new int[]{y+move_y, x+move_x});


        if(count == length) {
            direction = (direction+1)%4;
            count = 0;
            if(direction == 0 || direction == 2) {
                length++;
            }
        }


    }

    for(int i = 0; i < map.length; i++) {
        for(int j = 0; j < map[i].length; j++) {
            if(j != map[i].length - 1) {
                bw.write(map[i][j]+" ");
            } else {
                bw.write(map[i][j]+"\n");
            }
        }
    }

    bw.write(find_index[0]+" "+find_index[1]);


    bw.flush();
    bw.close();




    }


    public static void main(String[] args) throws IOException {
        solution();
    }

}
