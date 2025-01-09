import java.io.*;
import java.util.*;

public class Main {

    static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N, M, minArea = Integer.MAX_VALUE;
    static int[][] cctvDir ={
            {},
            {0}, //1번 cctv
            {0, 2}, //2번 cctv
            {0, 1}, //3번 cctv
            {0, 1, 2}, //4번 cctv
            {0, 1, 2, 3} //5번 cctv
    };
    static List<CCTV> cctvList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                //cctv 인지 검사
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0 && map[i][j] < 6){ //cctv인 경우 -> 리스트에 추가
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        //모든 cctv에 대해서 경우의 수 실행
        checkCCTV(0, map);
        System.out.println(minArea);
    }

    private static void checkCCTV(int cctvIdx, int[][] map) {
        if (cctvIdx == cctvList.size()) { //모든 cctv 표시 완료한 경우
            //사각지대 카운팅 -> 최솟값 갱신
            minArea = Math.min(minArea, countArea(map));
            return;
        }

        //cctvIdx번째 cctv에 대해 탐색 시작
        int x = cctvList.get(cctvIdx).x;
        int y = cctvList.get(cctvIdx).y;
        int number = cctvList.get(cctvIdx).number;

        for (int i = 0; i < 4; i++) { //cctv 방향 4방향으로,..
            int[][] cMap = copyMap(map);
            for (int d : cctvDir[number]) {
                int dir = (d + i) % 4; //방향 정하기
                int nx = x;
                int ny = y;
                while (true) {
                    //dir 방향대로 벽 나올 때까지 직진
                    nx += move[dir][0];
                    ny += move[dir][1];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 6)
                        break; // 범위 나가거나 벽 만나면 멈춤
                    cMap[nx][ny] = -1; //가능한 구역은 -1로 표시
                }
            }
            //영향 구역 표시 끝나면 다음 cctv로 넘어감
            checkCCTV(cctvIdx + 1, cMap);
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copiedMap = new int[N][M];
        //map 깊은 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    private static int countArea(int[][] map) {
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0)
                    area++;
            }
        }
        return area;
    }

    static class CCTV {
        int x, y, number;

        CCTV(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.number = num;
        }
    }
}