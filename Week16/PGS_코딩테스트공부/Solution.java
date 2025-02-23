/*
* 알고력과 코딩력을 높여서 문제 해결
* output: 알고력과 코딩력을 높이는 최단 시간
* alp: 알고력
* cop: 코딩력
* problems
* alp_req: 문제를 푸는데 필요한 알고력
* cop_req: 문제를 푸는데 필요한 코딩력
* alp_rwd: 문제를 풀었을 때 증가하는 알고력
* cop_rwd: 문제를 풀었을 때 증가하는 코딩력
* cost: 문제를 푸는데 드는 시간
*
* dp[i][j] : 알고력 i, 코딩력 j 일 때 걸리는 최소시간
* */

/*
✅ 목표 알고력과 코딩력을 찾기
✅ DP 테이블을 INF로 초기화하고, 시작 위치를 0으로 설정
✅ 모든 상태 (i, j)에 대해 가능한 행동(알고력 증가, 코딩력 증가, 문제 풀이) 수행
✅ 최종적으로 목표 상태 (max_alp, max_cop)에 도달하는 최소 시간을 반환*/
import java.util.*;
class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int alp1 = 10;
        int cop1 = 10;
        int[][] problems1 = {
                {10, 15, 2, 1, 2},
                {20, 20, 3, 3, 4}
        };
        System.out.println(sol.solution(alp1, cop1, problems1)); // 15

        int alp2 = 0;
        int cop2 = 0;
        int[][] problems2 = {
                {0, 0, 2, 1, 2},
                {4, 5, 3, 1, 2},
                {4, 11, 4, 0, 2},
                {10, 4, 0, 4, 2}
        };
        System.out.println(sol.solution(alp2, cop2, problems2)); // 13
    }
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        int goal_a = 0; int goal_c = 0;
        // 목표치를 구하는 for문
        for(int p[]: problems){
            goal_a = Math.max(goal_a, p[0]);
            goal_c = Math.max(goal_c, p[1]);
        }

        if(goal_a <=alp && goal_c <=cop) return 0;

        // 초기 알고력 교정
        alp = Math.min(alp, goal_a);
        cop = Math.min(cop, goal_c);

        // dp 테이블 초기화
        int INF = Integer.MAX_VALUE;
        int[][] dp=new int[goal_a+1][goal_c+1];
        for(int[] row: dp) Arrays.fill(row, INF);

        dp[alp][cop] = 0; // 시작점은 0

        // dp 업데이트
        for(int i=alp; i<=goal_a; i++){
            for(int j=cop; j<=goal_c; j++){
                // 알고력 증가
                if (i < goal_a) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                // 코딩력 증가
                if (j < goal_c) dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);

                // 문제 풀어서 능력치 향상
                for(int[] p:problems){
                    int alpReq = p[0], copReq = p[1], alpRwd = p[2], copRwd = p[3], cost = p[4];
                    // 문제를 풀 수 있다면?
                    if(i>=alpReq && j>=copReq) {
                        int newAlp = Math.min(i + alpRwd, goal_a);
                        int newCop = Math.min(j + copRwd, goal_c);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j]+cost);
                    }
                }
            }
        }

        answer= dp[goal_a][goal_c]; // 목표 능력까지 도달하는 최소 시간
        return answer;
    }
}
