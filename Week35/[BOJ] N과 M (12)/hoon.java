import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] nums;
    static Set<String> set = new HashSet<>();
    public static void combination(int depth, int last, int[] ans) {
        if(depth == M) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++) {
                sb.append(ans[i]).append(" ");
            }
            if(!set.contains(sb.toString())) {
                set.add(sb.toString());
                System.out.println(sb);
            }
            return;
        }
        for(int i = 0; i<N; i++){
            if(i >= last){
                ans[depth] = nums[i];
                combination(depth+1, i, ans);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        br.close();
        nums = new int[N];
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        combination(0, 0, new int[M]);
    }
}
