package 구간합구하기4;
/*
 * 2 ~ 4의 구간 합을 구하려면
 * 누적합[4] - 누적합[2-1] 이다.
 * 왜냐면
 * 누적합[4] = 1+ 2+ 3+ 4
 * 누적합[1] = 1
 * */
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // 개수
        int m = Integer.parseInt(input[1]); // 합을 구해야하는 횟수

        // N개의 수가 주어진다.
        int[] nums = new int[n];
        String[] tmp_num = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tmp_num[i]);
        }

        // 누적합을 미리 구해두자
        int[] prefixSum = new int[n+1];
        prefixSum[0]=0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i]= prefixSum[i-1] + nums[i-1];
        }


        // M개의 줄에는 합을 구해야하는 구간 i와 j가 주어진다.
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] tmp = br.readLine().split(" ");
            int idx1 = Integer.parseInt(tmp[0]);
            int idx2 = Integer.parseInt(tmp[1]);

            // 여기서 부터 맞는 누적합을 구한다.
            int tmp_ans = prefixSum[idx2] - prefixSum[idx1-1];
            ans.add(tmp_ans);
        }

        // 결과 출력
        for (int s : ans) {
            bw.write(s + "\n");
        }
        bw.flush();
        bw.close();
    }
}
