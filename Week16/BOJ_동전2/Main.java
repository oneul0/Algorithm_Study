import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        //첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
        //다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int coin[] = new int[n];
        int dp[] = new int[k+1];
        Arrays.fill(dp, 10003);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j < k+1; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin[i]] + 1) ;
            }
        }

        // 출력
        // 첫째 줄에 동전의 개수가 최소가 되는 동전의 개수를 출력한다.
        int output = (dp[k] == 10003) ? -1 : dp[k];
        bw.write(output + "\n");
        bw.flush();
        bw.close();
    }
}
