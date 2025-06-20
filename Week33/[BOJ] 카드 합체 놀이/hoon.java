import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] nums = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        while(M > 0){
            Arrays.sort(nums);
            long tmp = nums[0] + nums[1];
            nums[0] = tmp;
            nums[1] = tmp;
            M--;
        }
        long ans = 0;
        for(long num : nums) {
            ans += num;
        }
        System.out.print(ans);
    }
}