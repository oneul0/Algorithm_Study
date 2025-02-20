import java.util.*;
class Solution {
  public int solution(int alp, int cop, int[][] problems) {
    int goalAlp = 0, goalCop=0;

    //목표 설정
    for(int[] problem : problems){
      goalAlp = Math.max(problem[0], goalAlp);
      goalCop = Math.max(problem[1], goalCop);
    }
    if(alp >= goalAlp && cop >= goalCop) return 0;

    if(alp >= goalAlp) alp = goalAlp;
    if(cop >= goalCop) cop = goalCop;

    int[][] dp = new int[200][200];

    for(int i = alp; i<=goalAlp; i++){
      for(int j = cop; j<=goalCop; j++){
        dp[i][j] = Integer.MAX_VALUE;
      }
    }

    dp[alp][cop] = 0;

    for(int i = alp; i<=goalAlp; i++){
      for(int j = cop; j<=goalCop; j++){

        //알고력 트레이닝
        dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
        //코딩력 트레이닝
        dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

        //목표보다 작은데 풀 수 있는 문제가 없으면 트레이닝 해야하는데 어케함
        //전수조사 ㄱ
        for(int[] p : problems){
          //알고력과 코딩력을 갖췄으면
          if(p[0] <= i && p[1] <= j){
            //각 상황별 과도했을 때
            if(i+p[2] > goalAlp && j+p[3] > goalCop){
              dp[goalAlp][goalCop] = Math.min(dp[goalAlp][goalCop], dp[i][j]+p[4]);
            }
            else if(i+p[2] > goalAlp){
              dp[goalAlp][j+p[3]] = Math.min(dp[goalAlp][j+p[3]], dp[i][j]+p[4]);
            }
            else if(j+p[3] > goalCop){
              dp[i+p[2]][goalCop] = Math.min(dp[i+p[2]][goalCop], dp[i][j]+p[4]);
            }
            else if(i+p[2] <= goalAlp && j+p[3] <= goalCop){
              dp[i+p[2]][j+p[3]] = Math.min(dp[i+p[2]][j+p[3]], dp[i][j]+p[4]);
            }
          }
        }

      }
    }


    return dp[goalAlp][goalCop];
  }
}