import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * M줄 N칸으로 되어 있는 표
 * */
public class Main {
    public static int[][] direction = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0} // 오른쪽, 아래, 왼쪽, 위
    };


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int M = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);

        boolean[][] visited = new boolean[M][N];

        int sX = 0;
        int sY = 0;

        int dir = 0; // 방향

        int ans =0;
        for (int i = 0; i < M*N; i++) {
            visited[sX][sY] = true;

            // 다음 위치 계산
            int nextX = sX + direction[dir][0];
            int nextY = sY + direction[dir][1];

            // 경계 및 방문 여부 확인
            if(nextX <0 || nextX >= M || nextY <0 || nextY >= N || visited[nextX][nextY]){
                // 방향 전환
                dir= (dir+1) %4;
                System.out.println("sX : "+ sX+" sY: "+ sY);
                nextX = sX + direction[dir][0];
                nextY = sY + direction[dir][1];
                ans++;
            }

            // 위치 업데이트
            sX = nextX;
            sY = nextY;
        }
        System.out.println(ans-1);

    }
}
