import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int len = lost.length;
        int answer= n - len;
        Set<Integer> clothes = new HashSet<>();
        boolean[] chk = new boolean[len];

        for(int num : reserve) {
            clothes.add(num);
        }

        Arrays.sort(lost);
        for(int i = 0; i < len; i++) {
            if(clothes.contains(lost[i])) {
                answer++;
                chk[i] = true;
                clothes.remove(lost[i]);
            }
        }

        //체육복을 빌릴 수 있는지 체크(뒤에서부터 체크하여 중복 제거)
        for(int i = len - 1; i >= 0; i--) {
            if (chk[i]) continue;

            if(clothes.contains(lost[i] + 1)) {
                clothes.remove(lost[i] + 1);
                answer++;
            } else if(clothes.contains(lost[i] - 1)) {
                clothes.remove(lost[i] - 1);
                answer++;
            }
        }


        return answer;
    }
}