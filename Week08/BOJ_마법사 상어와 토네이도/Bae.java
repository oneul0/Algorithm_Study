import java.io.*;
import java.util.*;

public class Main {
//  좌 하 우 상
    static int[] y = {0, 1, 0, -1};
    static int[] x = {-1, 0, 1, 0};
//  소수점 변환 필요
    static int answer = 0;
    static int[][][] sandPercent = {
//      좌 하 우 상
            {
                    {-1, -1, 1}, {1, -1, 1},    // 1%
                    {-2, 0, 2}, {2, 0, 2},      // 2%
                    {-1, 0, 7}, {1, 0, 7},      // 7%
                    {-1, 1, 10}, {1, 1, 10},    // 10%
                    {0, -2, 5},                  // 5%
                    {0, -1, 0}                   // alpha
            },
            {
                    {-1, -1, 1}, {-1, 1, 1},
                    {0, -2, 2}, {0, 2, 2},
                    {0, -1, 7}, {0, 1, 7},
                    {1, -1, 10}, {1, 1, 10},
                    {2, 0, 5},
                    {1, 0, 0}
            },
            {
                    {-1, 1, 1}, {1, 1, 1},
                    {-2, 0, 2}, {2, 0, 2},
                    {-1, 0, 7}, {1, 0, 7},
                    {-1, -1, 10}, {1, -1, 10},
                    {0, 2, 5},
                    {0, 1, 0}
            },
            {
                    {1, -1, 1}, {1, 1, 1},
                    {0, -2, 2}, {0, 2, 2},
                    {0, -1, 7}, {0, 1, 7},
                    {-1, -1, 10}, {-1, 1, 10},
                    {-2, 0, 5},
                    {-1, 0, 0}
            }
    };
    public static void solution() throws IOException {

        /*
        *   (r,c) -> y, x
        *   A[y][x]
        *   NxN 격자
        *   주어진 비율에 따라 모래가 이동한다.
        *   모래는 계속 누적된다.
        *   이동방향은 상,하,좌,우
        *   이동하는 경로는 정해져있다
        *   이에 따라서 방향에 따른 비율 계산을 구현
        *   비율도 정해져있다.
        *   (1,1) -> 인덱스는 (0,0)
        *   이동 거리도 정해져 있다.
        *   이동할 때마다 배열 길이 확인 + 모래 옮기기
        *   1좌 1하 2우 2상 3좌 3하 4우 4상 .. n-1 우 n-1상 n-1좌 종료 , 2칸씩 이동
        *   좌 or 우 일때 이동 거리 1칸씩 증가
        *   [좌 하 우 상] [좌 하 우 상] [좌 하 우 상] 이 배열로 돌지만 마지막에만 + 좌 하나
        * n/2    1           2            3
        *   현재 이동 방향 기준으로
        *   index - 4 > 0  || index + 4 < n 전체 이동
        *   index - 4 == -1
        *   현재 기준으로 인덱스를 분배
        *   현재 (y,x) 좌로 이동 시  = (y, x-1) 했을 때
        *   (y-1,x) (y+1,x) 0.01
        *   (y+2, x-1) (y-1,x-1) 0.02
        *   (y+1, x-1) (y+1,x-1) 0.07
        *   (y+1, x-2) (y-1, x-2) 0.1
        *   (y, x-3) 0.05
        *   (y, x-2) 0.55
        *
        *   1. 토네이도 이동
        *   2. 이동 후 모래 이동
        *   -> 반복
        *
        *
        * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] sandArray = new int[n][n];


        for(int y = 0; y < n; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < n; x++){
                sandArray[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        tornado(n, sandArray);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();









    }


    public static void main(String[] args) throws IOException {
        solution();
    }

    public static int changeDirection(int direction) {
        if(direction == 0) {
            return 1;
        } else if(direction == 1) {
            return 2;
        } else if(direction == 2) {
            return 3;
        } else {
            return 0;
        }
    }

    public static boolean isValid(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

    //      1. 토네이도 이동
    private static void tornado(int n, int[][] sandArray) {
        int centerY = n/2;
        int centerX = n/2;
        int movement = 1;
        int direction = 0;

        //      방향 전환, 이동거리
        //      애초에 이동거리와 방향 모두 다 미리 정해놔서 따로 배열범위를 넘어가는 검사는 불필요
        while(movement <= n) {


            //  마지막 이동은 한번 더
            for(int i = 0; i < 2 && movement < n; i++) {
                for(int j = 0; j < movement; j++) {
                    centerY += y[direction];
                    centerX += x[direction];

                    if(!isValid(centerY, centerX, n)) continue;
                    moveSand(centerY, centerX, direction, sandArray, n);
                }
                direction = changeDirection(direction);
            }
            movement++;
        }

        // 마지막 왼쪽 이동
        for(int i = 0; i < n-1; i++) {
            centerY += y[0];
            centerX += x[0];
            if(isValid(centerY, centerX, n)) {
                moveSand(centerY, centerX, 0, sandArray, n);
            }
        }
    }

//  2. 모래 옮기기
//  각 방향 움직이는 인덱스는 전부 미리 작성 -> 움직이면서 isValid false면 answer에 더해서 계산
//  alpha (y, x-1) 이거는 분배하고 남은
//  55퍼 고정이 아니라 소수점을 버리기 때문에 따로 계산
    public static void moveSand(int y, int x, int direction, int[][] sandArray, int n)  {
        int sand = sandArray[y][x];
        int totalSand = 0;
        if (sand == 0) {
            return;
        }
        for(int[] sandMoveArray : sandPercent[direction]) {
            int nextY = y + sandMoveArray[0];
            int nextX = x + sandMoveArray[1];
            int moveSand = (sand * sandMoveArray[2]) / 100;


            if(moveSand > 0 ) {
                totalSand += moveSand;
                if(!isValid(nextY, nextX, n)) {
                    answer +=moveSand;
                } else {
                    sandArray[nextY][nextX] += moveSand;
                }
            }


        }
        int[] alpha = sandPercent[direction][9];
        int alphaY = y + alpha[0];
        int alphaX = x + alpha[1];
        int alphaSand = sand-totalSand;

        if(!isValid(alphaY, alphaX, n)) {
            answer +=alphaSand;
        } else {
            sandArray[alphaY][alphaX] += alphaSand;
        }
        sandArray[y][x] = 0;
    }
}
