import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;

    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static boolean[][] isVisited; // 방문 처리

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        sc.close();

        map = new int[M][N];
        isVisited = new boolean[M][N];

        int cnt = 0; // 출력할 꺽은 횟수
        int sum = 0; // 지나온 칸 수 -> 종료 조건으로 사용

        int idx = 0; // 다음 칸 탐색할 때 쓸 인덱스
        int nowX = 0; // 현재 좌표
        int nowY = 0;
        isVisited[nowX][nowY] = true;
        sum = 1;

        while(idx < 4) {
            int nx = nowX + dx[idx]; // 탐색한 새로운 좌표
            int ny = nowY + dy[idx];

            // 모든 칸을 지나왔다면 답 출력하고 종료
            if(sum == N*M) {
                System.out.println(cnt);
                return;
            }

            // 새로 탐색한 좌표가 범위 안에 있고 아직 방문하지 않은 곳이라면
            if(nx >= 0 && ny >= 0 && nx < M && ny < N && !isVisited[nx][ny]) {
                sum++; // 지나온 칸 수 + 1
                isVisited[nx][ny] = true; // 방문 처리
                nowX = nx; // 다음 탐색을 위해 갱신
                nowY = ny;
            }
            // 이전 방향으로 탐색했는데 범위를 벗어났거나 이미 방문한 곳이라면
            else {
                idx++; // 다음 탐색할 방향으로 전환
                cnt++; // 방향을 꺾었으므로 + 1
            }

            // 마지막 방향 인덱스까지 왔으면 다시 맨 처음 탐색 방향으로 전환
            if(idx >= 4) {
                idx = 0;
            }
        }

    }

}