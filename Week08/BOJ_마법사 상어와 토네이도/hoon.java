import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0, 1, 0, -1}; //좌, 하, 우, 상
    static int[] dc = {-1, 0, 1, 0};
    static int spreadR[][] = {
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {0, 0, 1, 1, 2, 2, 1, 1, 3, 2},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {0, 0, -1, -1, -2, -2, -1, -1, -3, -2}
    };
    static int spreadC[][] = {
            {0, 0, -1, -1, -2, -2, -1, -1, -3, -2},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {0, 0, 1, 1, 2, 2, 1, 1, 3, 2},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0}
    };
    static int percent[] = { 1, 1, 7, 7, 10, 10, 2, 2, 5 };

    static int[][] sand;
    static int answer;
    static int N;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        sand = new int[N][N];

        for(int i=0; i<N; i++){
            String[] in = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                sand[i][j] = Integer.parseInt(in[j]);
            }
        }

        int r = N/2, c = N/2;
        int moveCnt = 1;
        int dir = 0;

        while(true){
            //2번 같은 길이로 이동하고 변경되므로 2씩 묶어서 실행
            for(int i=0; i<2; i++){ //moveCnt;
                for(int j=0; j<moveCnt; j++){
                    spreadSand(r, c, dir);
                    r += dr[dir];
                    c += dc[dir];
                }

                dir = (dir+1)%4;
            }

            moveCnt++;

            if(moveCnt==N){
                for(int i=0; i<moveCnt-1; i++){
                    spreadSand(r, c, dir);
                    r += dr[dir];
                    c += dc[dir];
                }
                break;
            }
        }

        System.out.println(answer);
    }

    private static void spreadSand(int r, int c, int dir){
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        int curr = sand[nr][nc];

        for(int i=0; i<9; i++){
            int nnr = r + spreadR[dir][i];
            int nnc = c + spreadC[dir][i];

            int plus = curr*percent[i]/100;

            if(nnr<0 || nnc<0 || nnr>=N || nnc>=N){
                //격자 밖으로 나간 모래
                answer += plus;
            }
            else{
                sand[nnr][nnc] += plus;

            }
            sand[nr][nc] -= plus;
        }

        //a에 모래 이동
        int ar = r + spreadR[dir][9];
        int ac = c + spreadC[dir][9];
        if(ar<0 || ac<0 || ar>=N || ac>=N){
            answer += sand[nr][nc];
        }
        else{
            sand[ar][ac] += sand[nr][nc];
        }
        sand[nr][nc] = 0;

    }
}