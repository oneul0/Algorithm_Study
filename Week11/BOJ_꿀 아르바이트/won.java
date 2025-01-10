package BOJ_꿀아르바이트;
// 1 HOUR
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // INPUT
        //월세를 내기 바로 전 날 까지 인 n (1 ≤ n ≤ 100,000) 일과 일을 할 수 있는 날 m (0 ≤ m ≤ n) 일이 주어진다.
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        //그 다음 줄 에는 1일부터 n일 까지 일급 Ti가 순서대로 주어진다. (0 < Ti ≤ 1,000,000)
        int[] arr = new int[n];
        String[] line2 = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        long windowSum=0;
        long MaxCost = 0;

        for(int i = 0; i < n; i++) {
            windowSum += arr[i];

            if(i >= m-1) {
                MaxCost = Math.max(MaxCost, windowSum);
                windowSum = windowSum - arr[i - m +1];
            }
        }

        //System.out.println(MaxCost);
        bw.write(MaxCost+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
