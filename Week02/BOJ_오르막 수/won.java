
import java.io.*;

public class Main {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 한 줄을 읽어와서 공백을 기준으로 분리
        String[] input = br.readLine().split(" ");

        // 각각을 N, M으로 변환
        int N = Integer.parseInt(input[0]);
        arr = new int[N+1][10]; // 행의 idx는 1부터 사용함

        // N =1 일때 작업(전부 1로 하기)
        for (int i = 0; i <= 9; i++) {
            arr[1][i] = 1;
        }

        // N >= 2 일 경우
        for(int k = 2; k <= N; k++){
            for (int i = 0; i <= 9; i++) {
                int calSumResult =cal_sum(k - 1, i ) ;
                // 각 단계 에서도 MOD 연산을 하는 것이 좋다. 왜냐면 안하면 arr 안의 값이 너무 커짐
                arr[k][i] = calSumResult % 10007 ;
            }
        }

        int ans = cal_sum(N,9) % 10007;

        System.out.println(ans);
    }

    // cal_sum: k번째 행에서 0~n번째 값의 합 (arr[k][0] ~ arr[k][n])
    private static int cal_sum(int k, int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum+=arr[k][i];
        }

        return sum;
    }
}
