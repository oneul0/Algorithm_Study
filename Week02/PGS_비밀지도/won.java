import java.util.Arrays;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] temp = new String[n];

        for (int i = 0; i < n; i++) {
            int orResult = arr1[i] | arr2[i];
            String orResult_bin = Integer.toBinaryString(orResult); // 2진수로 변경

            // n자리로 맞추기 위해 String.format을 사용하여 앞에 0을 채움
            String format_result = String.format("%" + n + "s", orResult_bin).replace(' ', '0');

            temp[i] = format_result;
        }


        for (int i = 0; i < n; i++) {
            String s = temp[i];
            String change1 = s.replace("1", "#");
            String change2 = change1.replace("0", " ");
            answer[i] = change2;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String [] ans = solution.solution(5,
                new int[]{46, 33, 33, 22, 31, 50}, new int[] {27, 56, 19, 14, 14, 10} );
        System.out.println(Arrays.toString(ans));
    }
}