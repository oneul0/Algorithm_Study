import java.io.*;
import java.util.Arrays;

public class Main {
    //입력 배열의 인덱스까지 중 가장 긴 감소하는 부분 수열의 길이를 저장
    static int[] dp = new int[1001];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.fill(dp, 1);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }
        int ans = 0;
        //마지막에 오는 길이가 가장 긴 범위가 아닐 수도 있음
        //(중간에 위치한 부분 수열이 가장 길 수도 있기 때문에)
        for(int d : dp){
            ans = Math.max(ans, d);
        }
        bw.write(ans + "");
        bw.flush();
        br.close();
        bw.close();
    }
}
