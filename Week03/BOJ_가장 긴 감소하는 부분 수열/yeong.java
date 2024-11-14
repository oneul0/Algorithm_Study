import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n+1];
        for(int i = 1; i <= n ;i++){
            int val = 0;
            for(int j = 0; j < i; j++){
                // 자신보다 큰 arr 값중 dp값이 최대인 걸 찾음
                if(arr[j] > arr[i] && dp[j] > val){
                    val = dp[j];
                }
            }
            // 최대인 Dp값에 현재 Index값을 포함하기 위해 + 1
            dp[i] = val + 1;
            // 최대값을 연산 과정에서 찾기 위해서 Max값을 찾는 연산 추가
            result = Math.max(dp[i],result);
        }
        System.out.println(result);

    }
}