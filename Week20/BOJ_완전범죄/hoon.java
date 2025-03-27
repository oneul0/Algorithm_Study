import java.util.*;
class Solution {
  //n, m에 도달하면 안됨 바로 잡힘
  public int solution(int[][] info, int n, int m) {
    int size = info.length;
    int MAX = 200;
    int [][] dp = new int[size+1][m];
    for(int i = 0; i <= size; i++){
      Arrays.fill(dp[i], MAX);
    }
    dp[0][0] = 0;
    for(int i = 1; i <= size; i++){
      int a = info[i-1][0];
      int b = info[i-1][1];
      for(int j = 0; j < m; j++){
        // a 선택하는 경우
        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
        // b 선택하는 경우
        if(j + b < m){
          dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]);
        }
      }
    }
    int min = MAX;
    for(int j = 0; j < m; j++){
      min = Math.min(dp[size][j], min);
    }
    return min >= n ? -1 : min;
  }
}

//i까지 훔쳤을 때 B도둑의 흔적 개수가 x , dp[i][x] = A의 도둑의 최소 흔적 개수