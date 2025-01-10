package 감시;

import java.util.*;
import java.io.*;

public class Main {
    public static int[][] directions = {
            {-1,0},{0,1},{1,0},{0,-1}
    };

    public static List<CCTV> cctvs = new ArrayList<>();
    static int[][] office;
    static int ans = Integer.MAX_VALUE;
    static int n;
    static int m;

    static class CCTV {
        int x, y, type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        office = new int[n][m];
        for (int i = 0; i < n; i++) {
            String [] tmp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                office[i][j] = Integer.parseInt(tmp[j]);

                // 0: 빈칸, 1 ~5 : cctv, 6: 벽
                // CCTV는 따로 저장
                if( office[i][j] >= 1 && office[i][j] <= 5 ){
                    cctvs.add(new CCTV(i, j, office[i][j]));
                }
            }
        }

        dfs(0, office); // 백트래킹 방법으로 구현하기
        bw.write(ans+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int[][] currentOffice) {
        if(depth == cctvs.size()){ // 재귀 함수 종료 조건
            ans = Math.min(ans, cntBlindSpot(currentOffice));
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int[][] direct = getDirections(cctv.type); // cctv 종류별 방향 가져오기

        for(int[] dirSet: direct)
        {
            // 입력받은 office를 그대로 사용하면 모든 방향에 대해 기록됨
            // 배열을 복사해서 사용하기
            int[][] copiedOffice = copyArray(currentOffice);
            for(int dir: dirSet){
                // 감시 가능 구역 표시
                monitor(copiedOffice, cctv.x, cctv.y, dir);
            }
            dfs(depth + 1, copiedOffice);
        }
    }

    private static void monitor(int[][] office, int x, int y, int dir) {
        int nx = x + directions[dir][0];
        int ny = y + directions[dir][1];

        while(nx >=0 && ny >= 0 && nx < n && ny < m && office[nx][ny] != 6){
            // 범위 내에 위치하고 벽이 아닌 곳
            if(office[nx][ny] == 0){
                office[nx][ny] = 7; // 감시 가능 영역 표시
            }
            nx += directions[dir][0];
            ny += directions[dir][1];
        }
    }

    private static int[][] copyArray(int[][] currentOffice) { // 배열 복사
        int[][] copy = new int[n][m];
        for(int i=0; i<n; i++){
            System.arraycopy(currentOffice[i], 0, copy[i], 0, m);
        }
        return copy;
    }

    private static int[][] getDirections(int type) {
        // 감시 카메라에 따른 방향 설정하기
        // 0 ~ 3 순서대로 상 우 하 좌
        switch(type){
            case 1:
                return new int[][] { {0}, {1}, {2}, {3}};
            case 2:
                return new int[][] { { 0, 2 }, { 1, 3 } };
            case 3:
                return new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
            case 4:
                return new int[][] { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } };
            case 5:
                return new int[][] { { 0, 1, 2, 3 } };
        }
        return new int[0][];
    }

    private static int cntBlindSpot(int[][] currentOffice) {
        int count=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(currentOffice[i][j] == 0) count++;
            }
        }
        return count;
    }
}
