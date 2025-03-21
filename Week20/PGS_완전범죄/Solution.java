import java.util.*;
/*
* A도둑은 자신이 남긴 흔적의 누적 개수가 n개 이상이면 경찰에 붙잡힙니다.
B도둑은 자신이 남긴 흔적의 누적 개수가 m개 이상이면 경찰에 붙잡힙니다.
* */
/*
dp[i][a][b] = i번째 물건까지 고려했을 때, A의 흔적이 a, B의 흔적이 b일 때 A의 최소 흔적 개수

i: 현재 고려 중인 물건 인덱스
a: 현재까지 A가 남긴 흔적 개수
b: 현재까지 B가 남긴 흔적 개수
dp[i][a][b]: 그 상태에서 A가 남긴 흔적의 최소값*/

/*
* 물건 i를 A가 훔치는 경우:
dp[i+1][a + info[i][0]][b] = min(dp[i+1][a + info[i][0]][b], dp[i][a][b] + info[i][0])*/
public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] info = {{1,2}, {2,3}, {2,1}};
        int n= 1; int m=7;
        System.out.println(sol.solution(info,n,m));

        int[][] info2 = {{3,3}, {3,3}};
        int n2= 6; int m2=1;
        System.out.println(sol.solution(info2,n2,m2));
    }
    public int solution(int[][] info, int n, int m) {
        int len = info.length;
        int MAX_VALUE =121; // 최대 흔적 제한(120 보다 크면 무의미한 값)

        int[][][] dp = new int[len+1][n+1][m+1];

        for(int[][] arr2d: dp){
            for(int[] arr1d: arr2d){
                Arrays.fill(arr1d, MAX_VALUE);
            }
        }

        dp[0][0][0] = 0; //초기상태
        for(int i=0; i<len; i++){
            int cur_a = info[i][0]; int cur_b = info[i][1];

            for(int a=0; a<n; a++){
                for(int b=0; b<m; b++){
                    if(dp[i][a][b] == MAX_VALUE){continue;}

                    // A가 훔치는 경우
                    if(a + cur_a < n){
                        dp[i+1][a+cur_a][b] = Math.min(dp[i+1][a+cur_a][b], dp[i][a][b]+cur_a );
                    }
                    // B가 훔치는 경우
                    if(b + cur_b < m){
                        dp[i+1][a][b+cur_b] = Math.min(dp[i+1][a][b+cur_b] , dp[i][a][b]+cur_b);
                    }
                }
            }
        }

        // 최솟값 찾기
        int answer = MAX_VALUE;
        for(int a=0; a<n; a++){
            for(int b=0; b<m; b++){
                answer = Math.min(answer,dp[len][a][b]);
            }
        }

        return (answer == MAX_VALUE) ? -1 : answer;
    }
}
