import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(str[i]));
        }

        // 오름 차순 정렬
        // -99 -2 -1 4 98
        Collections.sort(nums);

        int gap = Integer.MAX_VALUE; // 0과의 차이
        int start =0;
        int ans1=0;
        int ans2=0;
        int end = nums.size() - 1;

        while (start < end) {
            int current_sum= nums.get(start) + nums.get(end);
            if(Math.abs(current_sum-0) < gap )
            {
                gap = Math.abs(current_sum-0);
                ans1=nums.get(start);
                ans2=nums.get(end);
            }

            // 포인터 이동
            if(current_sum < 0 ){
                start++;
            } else end--;
        }
        System.out.println(ans1 + " " + ans2);
    }
}
