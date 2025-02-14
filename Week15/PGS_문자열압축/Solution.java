import java.util.*;
class Solution {
    public int solution(String s) {

        int s_len = s.length();
        int answer = s_len;

        // 1개 단위(step) 부터 압축 단위를 늘려가며 확인
        for (int step = 1; step <= s_len /2; step++) {
            StringBuilder sb = new StringBuilder();

            int count = 1;
            String prev = s.substring(0, step);
            for (int i = step; i < s_len; i += step) {
                String curr = s.substring(i, Math.min(i + step, s_len));
                if(curr.equals(prev)) {count++;}
                else {
                    sb.append(count >=2  ? count+prev:prev);
                    count = 1;
                    prev = curr;
                }
            }

            // 마지막 부분 처리
            sb.append(count >=2  ? count+prev:prev);
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ababcdcdababcdcd";
        System.out.println(solution.solution(s));
    }
}