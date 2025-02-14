import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr, dp;
    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        dp = new int[k+1];
        for(int i = 1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        /*
        j원이 될 수 있는 경우의 수
        * */
        dp[0] = 1;
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=k; j++){
                //현재의 작은 가방 안에 들어갈 수 있는가?
                if(arr[i] <= j){
                    dp[j] = dp[j]+dp[j-arr[i]];
                }
            }
        }
        bw.write(dp[k]+"");
        bw.flush();
        br.close();
        bw.close();
    }
}
