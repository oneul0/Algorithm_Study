import java.io.*;

// 달팽이 회로
public class Main {

    public static int[] di ={0, 1, 0, -1};
    public static int[] dj ={-1, 0 ,1, 0};

    // 위치 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 <= 9는 나머지
    public static int[] mul = {2, 10, 7, 1, 5, 10, 7, 1,2, 0}; // 비율이므로 나중에 100 나눠주기

    public static int[][] ai ={
            {-2,-1,-1,-1, 0, 1, 1, 1, 2, 0},
            { 0, 1, 0,-1, 2, 1, 0,-1, 0, 1},
            { 2, 1, 1, 1, 0,-1,-1,-1,-2, 0},
            { 0,-1, 0, 1,-2,-1, 0, 1, 0,-1}
    };

    public static int[][] aj = {
            { 0,-1, 0, 1,-2,-1, 0, 1, 0,-1},
            {-2,-1,-1,-1, 0, 1, 1, 1, 2, 0},
            {0, 1, 0,-1, 2, 1, 0,-1, 0, 1},
            {2, 1, 1, 1, 0,-1,-1,-1,-2, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N : 격자의 크기
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        int cnt_max =1;
        int ci = N/2;
        int cj = N/2;

        int dr=0;
        int cnt=0;
        int flag=0;
        int answer =0;

        while (!(ci == 0 && cj == 0)) {
            // dr방향으로 1칸 이동
            ci +=  di[dr];
            cj +=  dj[dr];

            // 1) ci, cj 기준좌표 중심으로 모래량 계산해서 추가 또는 범위 밖이면 answer 에 추가하기
            if(arr[ci][cj]>0){ // 모래가 있을 때만 진행하기
                int val = arr[ci][cj];  // 기준좌표 모래량
                arr[ci][cj] = 0; // 기준 좌표 모래 날라가서 없어짐
                int sum = 0;

                for(int i=0; i< 10; i++){ // 위치 0~9 까지 처리
                    // 좌표 계산
                    int ni = ci+ai[dr][i];
                    int nj = cj+aj[dr][i];

                    int t = val*mul[i]/ 100; // 비율 계산 (위치의 모래양 계산하기), 소수점 이하 버림

                    if(i == 9){
                        // 나머지 모래 계산
                        t= val - sum; // 전체 값 - (0 ~ 8까지의 모래량)
                    }

                    if( 0<=ni && ni<N && 0<=nj && nj<N ){
                        // 범위 내라면 누적
                        arr[ni][nj] += t;
                    }else{
                        // 좌표 밖으로 나간 모래량에 추가
                        answer+=t;
                    }
                    sum += t;
                }
            }

            cnt++;

            if(cnt == cnt_max) {
                System.out.println("cnt: "+ cnt);
                System.out.println("cnt_max: "+ cnt_max);
                cnt =0;
                dr = (dr+1)%4;  // 방향은 매번 바뀜
                if (flag==0){  // 1,1,2,2,3,3,4,4 ... 두 번에 1칸씩 +1
                    flag=1;
                }else{
                    flag=0;
                    cnt_max++;
                }
            }
        }

        System.out.println(answer);
    }
}
