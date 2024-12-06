/*
1. 로봇 방향
2. 회전 여부
3. 벽 여부
가로 상태 -> 상하 x -> 회전
세로 상태 -> 좌우 x -> 회전

3차원으로 상태 변경하며 비교 ?
(0,0) (0,1) -> (n-1,n-1)


*/




import java.util.*;

public class Solution {
    static int[] x = { -1, 1, 0, 0 };
    static int[] y = { 0, 0, -1, 1 };
    static int n;
    public int solution(int[][] board) {
        
        n = board.length;
        int answer = 0;
        boolean[][][] visited = new boolean[2][n][n]; 
        
//      가로 0, 세로 1

        Queue<int[][]> q = new ArrayDeque<>();
        q.offer(new int[][]{ { 0, 0 }, { 0, 1 }, { 0 } });
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        
        while (!q.isEmpty()) {
            int[][] cur = q.poll();
            int x1 = cur[0][0];
            int y1 = cur[0][1];
            int x2 = cur[1][0];
            int y2 = cur[1][1];
            int type = type(cur[0], cur[1]);
            int time = cur[2][0];

            if ((x1 == n - 1 && y1 == n - 1) || (x2 == n - 1 && y2 == n - 1)) {
                answer = time;
                break;
            }

            // 상하좌우
            for (int d = 0; d < 4; d++) {
                int nextx1 = x1 + x[d];
                int nexty1 = y1 + y[d];
                int nextx2 = x2 + x[d];
                int nexty2 = y2 + y[d];

                // 범위 체크 및 장애물 확인
                if (isValid(nextx1, nexty1, n) && isValid(nextx2, nexty2, n) && board[nextx1][nexty1] != 1 && board[nextx2][nexty2] != 1) {
                    
                    // 방향전환 없이 이동
                    if (!visited[type][nextx1][nexty1] || !visited[type][nextx2][nexty2]) {
                        visited[type][nextx1][nexty1] = true;
                        visited[type][nextx2][nexty2] = true;
                        q.offer(new int[][] { { nextx1, nexty1 }, { nextx2, nexty2 }, { time + 1 } });
                    }

                    // 회전 가능한 경우
                    int typeChange;
                    if (type == 0) {
                        typeChange = 1; 
                    } else {
                        typeChange = 0;
                    }

                    // 가로 -> 상하, 세로 -> 좌우로 회전 가능
                    if ((type == 0 && d < 2) || (type == 1 && d > 1)) {
                        if (!visited[typeChange][nextx1][nexty1] || !visited[typeChange][x1][y1]) {
                            visited[typeChange][nextx1][nexty1] = true;
                            visited[typeChange][x1][y1] = true;
                            q.offer(new int[][] { { nextx1, nexty1 }, { x1, y1 }, { time + 1 } });
                        }

                        if (!visited[typeChange][nextx2][nexty2] || !visited[typeChange][x2][y2]) {
                            visited[typeChange][nextx2][nexty2] = true;
                            visited[typeChange][x2][y2] = true;
                            q.offer(new int[][] { { nextx2, nexty2 }, { x2, y2 }, { time + 1 } });
                        }
                    }
                }
            }
        }

        return answer;
    }

    public boolean isValid(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    // 두 좌표가 가로 0, 세로 1 
    public int type(int[] pos1, int[] pos2) {
        if (pos1[0] == pos2[0])
            return 0;
        return 1;
    }
}
