import java.util.*;
import java.io.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  static List<Integer> steps = new ArrayList<>();
  static Map<Integer, List<Integer>> connectInfo = new HashMap<>();
  static int[][][] dp;

  public static void mapInit(){
    for(int i = 1; i<=4; i++){
      connectInfo.put(i, new ArrayList<>());
    }
    connectInfo.get(1).add(2);
    connectInfo.get(1).add(4);
    connectInfo.get(2).add(1);
    connectInfo.get(2).add(3);
    connectInfo.get(3).add(2);
    connectInfo.get(3).add(4);
    connectInfo.get(4).add(1);
    connectInfo.get(4).add(3);
  }

  public static int calcCost(int foot, int to){
    int newCost = 0;
    if(foot == 0){
      newCost = 2;
    }
    else if(connectInfo.get(foot).contains(to)){
      newCost = 3;
    }
    else if(foot == to){
      newCost = 1;
    }
    else {
      newCost = 4;
    }
    return newCost;
  }

  public static void main(String[] args) throws IOException {
    mapInit();
    StringTokenizer st = new StringTokenizer(br.readLine());
    while (st.hasMoreTokens()) {
      int n = Integer.parseInt(st.nextToken());
      if (n == 0) break;
      steps.add(n);
    }

    int N = steps.size();
    dp = new int[5][5][N + 1];

    for (int i = 0; i <= 4; i++) {
      for (int j = 0; j <= 4; j++) {
        Arrays.fill(dp[i][j], Integer.MAX_VALUE);
      }
    }

    dp[0][0][0] = 0;

    for (int k = 0; k < N; k++) {
      int next = steps.get(k);

      //4 * 4 모든 경우의 수 중 갱신되고 있는 step으로 이어가기
      for (int l = 0; l <= 4; l++) {
        for (int r = 0; r <= 4; r++) {
          if (dp[l][r][k] == Integer.MAX_VALUE) continue;

          int leftCost = dp[l][r][k] + calcCost(l, next);
          dp[next][r][k + 1] = Math.min(dp[next][r][k + 1], leftCost);

          int rightCost = dp[l][r][k] + calcCost(r, next);
          dp[l][next][k + 1] = Math.min(dp[l][next][k + 1], rightCost);
        }
      }
    }

    int answer = Integer.MAX_VALUE;
    for (int l = 0; l <= 4; l++) {
      for (int r = 0; r <= 4; r++) {
        answer = Math.min(answer, dp[l][r][N]);
      }
    }

    System.out.print(answer);
  }
}