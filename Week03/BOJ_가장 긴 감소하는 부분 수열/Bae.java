import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());


        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

// 문제는 가장 길게 감소하는 수열의 길이
// 역으로 배열을 탐색하면서 n-2부터 (배열을 1로 채워 최소 길이 = 1)
// 현재 인덱스 i dp = 나보다 작은 i들의 dp값 +1 보다 큰가 작은가
// 계속 현재 내 dp와 +1한 나보다 작은 것들을 비교하여 미리 계산하여



        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = n-2; i>=0; i--) {
            for(int j = n-1; j > i; j--){
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }
            }
        }
        int answer = 1;
        for(int temp : dp) {
            answer = Math.max(answer, temp);
        }

        bw.write(String.valueOf(answer));




        bw.flush();
        bw.close();
    }





    public static void main(String[] args) throws IOException {
        solution();
    }
}
