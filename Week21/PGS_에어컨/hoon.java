import java.util.*;
class Solution {
  public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
    t1 += 10; // 음수 처리
    t2 += 10;
    temperature += 10;

    // DP[시간][온도] = 최소 전력값
    int[][] DP = new int[onboard.length][51];
    for (int i = 0; i < onboard.length; i++) {
      for (int j = 0; j < 51; j++) {
        DP[i][j] = 1000*50;
      }
    }

    DP[0][temperature] = 0;

    // 온도 변화 방향 결정
    int flag = 1;
    if (temperature > t2) {
      flag = -1; // 실외 온도가 목표 온도보다 높으면 온도를 내려야 함
    }

    for (int i = 1; i < onboard.length; i++) {
      for (int j = 0; j < 51; j++) {
        int minCost = 1000*50;
        // 승객이 있을 때는 온도 범위 내에 있어야 함
        if (( onboard[i] == 1 && t1 <= j && j <= t2 ) || onboard[i] == 0) {

          // 에어컨 끈 상태
          if (0 <= j+flag && j+flag <= 50) {
            minCost = Math.min(minCost, DP[i-1][j+flag]);
          }

          // 실외 온도와 같을 때는 변화 없음
          if (j == temperature) {
            minCost = Math.min(minCost, DP[i-1][j]);
          }

          // 에어컨을 켰을 때(전력 a)
          if (0 <= j-flag && j-flag <= 50) {
            minCost = Math.min(minCost, DP[i-1][j-flag] + a);
          }

          // 에어컨을 켜고 온도 유지(전력 b)
          if (t1 <= j && j <= t2) {
            minCost = Math.min(minCost, DP[i-1][j] + b);
          }

          DP[i][j] = minCost;
        }
      }
    }

    int i = onboard.length-1;
    int answer = DP[i][0];
    for (int j = 1; j < 51; j++) {
      answer = Math.min(answer, DP[i][j]);
    }
    return answer;
  }

}
