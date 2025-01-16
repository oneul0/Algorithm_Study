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

/*
DFS를 곁들인 백트레킹... 연구소처럼 cctv가 가질 수 있는 경우에 대해 DFS로 탐색하고, 범위를 벗어나거나 벽을 만나면 해당 방향은 탐색 멈춤

-> 이때 탐색 가능한 지역은 -1로 바꿔줌. 다양한 cctv 방향에 대해 경우를 따져봐야 하기 때문에 map 자체를 수정하는게 아닌 map을 복사한 버전에서 바꿔줌 (깊은 복사)

cctv의 위치는 좌표, 번호를 담아서 리스트에 저장함
1번 ~ 5번 cctv가 감시할 방향을 담을 2차원 배열 정의 (기본형을 담아놔서 그 값 + 1 하는 방식으로 4방향 돌 수 있게)
cctv별로 진행방향 개수가 다른데, 이러면 한번에 처리할 수 있음
제일 바깥 for문은 4방향으로 돌리는 담당 (0 ~ 3까지 진행) -> 이 값을 dir 배열에 담은 값에 더해서 방향 바꿔줌
각 방향 별 경우를 나눠서 진행해야 하므로 이때 map 깊은 복사 진행함
방향 정하면 그 방향으로 쭉 직진하며 -1로 변경. 벽 만남 or map 바깥으로 나가는 경우 멈춤
그렇게 바뀐 복사 map 그대로 다음 cctv의 경우 진행 (그 다음 cctv 4방향 ... )
마지막 cctv까지 검사하면, 최종 map 기준으로 0인 칸 (사각지대) 카운팅
*/