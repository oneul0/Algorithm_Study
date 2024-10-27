import java.io.*;
import java.util.*;

public class Main {
    static int[] x_move = {1,-1,0,0};
    static int[] y_move = {0,0,1,-1};
    public static void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[][] arr = new int[y][x];

        for(int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < x; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//      시간마다 상하좌우 익어간다 -> BFS
//      1. 먼저 익은 토마토 index 찾기

//      예외케이스
//      1. 시작부터 토마토가 다 익음 -> 0
//      2. 다 익지못하는 상황 -> -1

        int count = bfs(y, x, arr);




        System.out.println(count);
        bw.flush();
        bw.close();

    }

    public static int bfs(int y, int x, int[][] arr) {
        int count = 0;

        List<int[]> one = new ArrayList<>();

        boolean check = false;

        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(arr[i][j] == 0) {
                    check = true;
                }
                if(arr[i][j] == 1) {
                    one.add(new int[]{i, j});
                }
            }
        }

//      초기에 다 익은 상태 확인
        if(!check) {
            return 0;
        }

        Deque<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < one.size(); i++) {
            q.add(one.get(i));
        }


        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i <size; i++) {
                int[] curr = q.poll();
                for(int k = 0; k <4; k++) {
                    int nexty = curr[0] + y_move[k];
                    int nextx = curr[1] + x_move[k];
                    int[] next = new int[]{nexty, nextx};
                    if(isValid(nexty,nextx, y, x)) {
                        if(arr[nexty][nextx] == 0) {
                            arr[nexty][nextx] = 1;
                            q.add(next);
                        }
                    }
                }
            }
            count++;
        }

        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(arr[i][j] == 0) {
                    return -1;
                }
            }
        }
        return count-1;
    }

    public static boolean isValid(int curry, int currx, int y, int x) {
        return currx >= 0 && currx < x  && curry >= 0 && curry < y;
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
