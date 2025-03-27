/*
 * 지도의 숫자 0: 주사위의 바닥면에 쓰인 수 -> 지도로
 * 지도의 숫자 0 아님 => 지도에 쓰인 수 => 주사위 바닥면 + 지도에 쓰인수는 0이 됨
 * */
import java.util.*;
import java.io.*;
public class Main {
    static int n, m,x,y,k;
    static int[][] map; // 지도 정보
    static int[] dice = new int[6]; // 주사위 면의 값 (윗면: dice[0], 아랫면: dice[1])

    // 동 서 북 남(1,2,3,4)
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int x = Integer.parseInt(input[2]);
        int y = Integer.parseInt(input[3]);
        int k = Integer.parseInt(input[4]);

        map = new int[n][m];

        // 지도 입력 받기
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 명령 실행
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) { // StringTokenizer 클래스 객체에서 다음에 읽어들일 token이 존재하면 true 아님 false
            int dir = Integer.parseInt(st.nextToken());

            // 이동할 좌표 계산
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 지도 범위 벗어나면 무시
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 주사위 굴리기
            rollDice(dir);

            // 이동
            x = nx;
            y = ny;

            // 지도와 주사위 바닥면 값 처리
            if(map[x][y]==0){
                map[x][y]= dice[1]; // 바닥면 값이 칸으로 복사됨
            } else {
                dice[1] = map[x][y];
                map[x][y]= 0;
            }
            // 윗면 값 출력하기
            System.out.println(dice[0]);
        }
    }

    // 주사위 굴리기 함수
    static void rollDice(int dir) {
        int[] temp = dice.clone();

        if(dir ==1 ) { // 동
            dice[0] = temp[3];
            dice[1] = temp[2];
            dice[2] = temp[0];
            dice[3] = temp[1];
        } else if(dir ==2 ) { // 서
            dice[0] = temp[2];
            dice[1] = temp[3];
            dice[2] = temp[1];
            dice[3] = temp[0];
        } else if(dir ==3 ) { // 북
            dice[0] = temp[4];
            dice[1] = temp[5];
            dice[4] = temp[1];
            dice[5] = temp[0];
        }else if(dir ==4 ) { //  남
            dice[0] = temp[5];
            dice[1] = temp[4];
            dice[4] = temp[0];
            dice[5] = temp[1];
        }

    }
}