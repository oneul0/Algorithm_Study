//https://jun-coding.tistory.com/693
package boj15683;

import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Pos> cctvList = new ArrayList<>();

    // 위쪽, 오른쪽, 아래쪽, 왼쪽
    private static int[] dr = { -1, 0, 1, 0 };
    private static int[] dc = { 0, 1, 0, -1 };
    // 위쪽:0, 오른쪽:1, 아래쪽:2, 왼쪽:3
    private static int[][] dir = {
            {},
            {1},
            {1, 3},
            {0, 1},
            {0, 1, 3},
            {0, 1, 2, 3}
    };

    static int N, M;
    static int[][] office;

    static int answer = 100;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                // -1:감시 구역, 0:빈칸, 1~5:CCTV, 6:벽
                office[i][j] = Integer.parseInt(st.nextToken());

                // CCTV 위치와 번호 저장
                if(1 <= office[i][j] && office[i][j] <= 5) {
                    int num = office[i][j];
                    cctvList.add(new Pos(i, j, num));
                }
            }
        }

        dfs(0, office);
        System.out.println(answer);
    }

    /**
     * dfs로 CCTV의 감시상태를 업데이트하는 함수
     * @param cctvCount 현재 dfs 단계에서의 CCTV 개수
     * @param office 현재 office의 감시 상태
     */
    private static void dfs(int cctvCount, int[][] office) {
        // 모든 CCTV 체크를 다했다면,
        if(cctvCount == cctvList.size()) {
            // 사각지대의 최소값 업데이트
            answer = Math.min(answer, getBlindSpotCount(office));
            return;
        }

        int row = cctvList.get(cctvCount).row; // 행
        int col = cctvList.get(cctvCount).col; // 열
        int num = cctvList.get(cctvCount).num; // CCTV의 번호

        // 90도씩 4번 모두 체크 (0, 90, 180, 270)
        for(int d=0; d<4; d++) {
            int[][] copiedOffice = getDeepCopiedOffice(office);

            // num번 CCTV의 감시방향
            // 위쪽:0, 오른쪽:1, 아래쪽:2, 왼쪽:3
            for(int move : dir[num]) {
                int nd = (move + d) % 4; // 4번째(360도)는 0번째(0도)와 같기 때문에 %4로 0을 만들어줌
                int nr = row;
                int nc = col;

                while(true) {
                    nr += dr[nd]; // 회전했을 때의 방향에 따른 row값 한칸씩 이동
                    nc += dc[nd]; // 회전했을 때의 방향에 따른 col값 한칸씩 이동

                    // 이동한 곳이 접근할 수 없거나 벽인 경우
                    if(!isValidIndex(nr, nc)) {
                        break;
                    }

                    // 감시 가능한 곳은 -1로 체크
                    copiedOffice[nr][nc] = -1;
                }
            }

            // 한 개의 CCTV 감시 구역 체크 완료했으므로 cctvCount + 1,
            // 현재 단계에서 감시 체크가 된 깊은 복사한 사무실을 인자로 넘겨줌
            dfs(cctvCount + 1, copiedOffice);
        }
    }

    /**
     * 사각지대의 칸 수를 얻는 함수
     * @param copiedOffice 복사된 사무실 상태
     * @return blindSpotCount : Integer
     */
    private static int getBlindSpotCount(int[][] copiedOffice) {
        int blindSpotCount = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copiedOffice[i][j] == 0) {
                    blindSpotCount++;
                }
            }
        }

        return blindSpotCount;
    }

    /**
     * 사무실 상태를 깊은 복사해서 반환하는 함수
     * @param office 사무실 상태
     * @return deepCopiedOffice : int[][]
     */
    private static int[][] getDeepCopiedOffice(int[][] office) {
        int[][] copiedOffice = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copiedOffice[i][j] = office[i][j];
            }
        }

        return copiedOffice;
    }

    /**
     * 행과 열을 인자로 받아, 접근할 수 있는 유효한 인덱스인지 판단해서 boolean 값을 반환하는 함수
     * @param row 행
     * @param col 열
     * @return isValid : Boolean
     */
    private static boolean isValidIndex(int row, int col) {
        if(row < 0 || row >= N) {
            return false;
        }
        if(col < 0 || col >= M) {
            return false;
        }
        // 벽
        if(office[row][col] == 6) {
            return false;
        }

        return true;
    }
}

/**
 * CCTV의 행, 열, 번호를 저장하는 클래스
 */
class Pos {
    int row;
    int col;
    int num;

    public Pos(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.num = num;
    }
}