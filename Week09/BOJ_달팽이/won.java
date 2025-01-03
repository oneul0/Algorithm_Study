
import java.io.*;

public class Main {
    public static int[][] direction = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1} // 위, 오른쪽, 아래, 왼쪽
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 홀수 크기 입력
        int findNum = Integer.parseInt(br.readLine()); // 찾는 숫자

        int[][] answer = new int[N][N];
        int dir = 0; // 초기 방향 (위쪽)
        int sX = N / 2, sY = N / 2; // 시작 위치 (가운데)

        int cnt =0; int cnt_max =1; int flag = 0;
        int currentNumber = 1; // 1부터 시작

        int findX=0; int findY=0; // 찾는 숫자의 위치 저장

        while (currentNumber <= N * N) {
            answer[sX][sY] = currentNumber++; // 현재 칸에 숫자 할당
            // 다음 위치 계산
            int nextX = sX + direction[dir][0];
            int nextY = sY + direction[dir][1];

            cnt++;

            if(currentNumber -1 == findNum) {
                findX = sX;
                findY = sY;
            }

            if(cnt == cnt_max){
                cnt =0;
                dir = (dir + 1) % 4;
                if(flag == 0){
                    flag = 1;
                }else{
                    flag = 0;
                    cnt_max++;
                }
            }
            sX = nextX;
            sY = nextY;
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(answer[i][j] + " ");
            }
            bw.newLine(); // 줄 바꿈
        }

        // 찾는 인덱스 출력
        bw.write((findX + 1) + " " + (findY + 1));
        bw.newLine();
        bw.flush();
    }
}

