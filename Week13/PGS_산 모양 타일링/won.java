import java.util.*;
class Solution {
    public int solution(int n, int[] tops) {

        int a[] = new int[n+1];
        int b[] = new int[n+1];

        a[1] = 1;
        if(tops[0] == 1) b[1] = 3;
        else b[1] = 2;

        for(int i = 2; i <= n; i++) {
            a[i] = (a[i-1] + b[i-1]) % 10007;
            if(tops[i-1] == 1) b[i] = (2*a[i-1] + 3*b[i-1]) % 10007;
            else b[i]= (a[i-1] + 2 * b[i-1]) % 10007;
        }

        int answer = (a[n] + b[n]) % 10007;
        return answer;
    }
}