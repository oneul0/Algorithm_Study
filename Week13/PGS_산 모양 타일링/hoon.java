class Solution {
    final int MOD = 10007;
    public int solution(int n, int[] tops) {
        int[] a = new int[n+1], b = new int[n+1];
        b[0] = 1;
        for(int i = 1; i<=n; i++){
            if(tops[i-1] == 1){
                a[i] = (a[i-1]+b[i-1])%MOD;
                b[i] = (a[i-1]*2+b[i-1]*3)%MOD;
            }
            else{ // tops[i-1] == 0 일때
                a[i] = (a[i-1]+b[i-1])%MOD;
                b[i] = (a[i-1]+b[i-1]*2)%MOD;
            }
        }

        return (a[n]+b[n])%MOD;
    }
}