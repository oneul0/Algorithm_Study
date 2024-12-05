import java.util.*;

public class Solution {

    class Robot{
        int y1, x1, y2, x2, t; // 방향: 두 좌표의 비교를 통해서 파악, t: 시작점부터 걸린 시간

        Robot(int y1, int x1, int y2, int x2, int t) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.t = t;

        }
    }

    Queue<Robot> queue = new ArrayDeque<>();

    int N;
    boolean[][][] visit;

    //상-하-좌-우 순서 유지 (회전을 함께 처리)
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};

    public int solution(int[][] board) {
        int answer = 0;

        //초기화
        N = board.length;
        visit = new boolean[2][N][N]; // 0:가로, 1:세로

        //bfs
        visit[0][0][0] = true;
        visit[0][0][1] = true;
        queue.offer(new Robot(0,0,0,1,0));

        while(!queue.isEmpty()) {

            Robot robot = queue.poll();

            //도착 지점에 도달 확인
            if( (robot.y1 == N -1 && robot.x1 == N-1) || (robot.y2 == N -1 && robot.x2 == N-1)) {
                answer = robot.t;
                break;
            }

            // 이전 상태의 로복의 방향
            int dir = robot.y1 == robot.y2 ? 0 : 1;

            //delta를 이용해서 이동 및 회전
            for(int d = 0; d < 4; d++) {

                //같은 방향으로 2점이 움진인다.
                int ny1 = robot.y1 + dy[d];
                int nx1 = robot.x1 + dx[d];
                int ny2 = robot.y2 + dy[d];
                int nx2 = robot.x2 + dx[d];

                //range check
                if( ny1 < 0 || nx1 < 0 || ny1 >= N || nx1 >= N) continue;
                if( ny2 < 0 || nx2 < 0 || ny2 >= N || nx2 >= N) continue;

                //벽 check
                if( board[ny1][nx1] == 1 || board[ny2][nx2] == 1) continue;

                //visit
                if( visit[dir][ny1][nx1] && visit[dir][ny2][nx2]) continue;

                //로봇 이동
                visit[dir][ny1][nx1] = true;
                visit[dir][ny2][nx2] = true;
                queue.offer(new Robot(ny1, nx1, ny2, nx2, robot.t +1)); // 이전 상태 로봇 좌표까지 걸린 시간 +1

                //로봇 회전
                //가로 : 상하가 비어있는 경우
                //세로 : 좌우가 비어있는 경우
                if( (dir == 0 && d < 2) || (dir == 1 && d > 1) ) {

                    int newDir = dir == 0 ? 1 : 0; // 회전된 상태의 가로/세로

                    //y1, x1 기준
                    if(! visit[newDir][robot.y1][robot.x1] || !visit[newDir][ny1][nx1]) {
                        visit[newDir][robot.y1][robot.x1] = true;
                        visit[newDir][ny1][nx1] = true;
                        queue.offer(new Robot(robot.y1, robot.x1, ny1, nx1, robot.t + 1));
                    }

                    //y2, x2 기준
                    if(! visit[newDir][robot.y2][robot.x2] || !visit[newDir][ny2][nx2]) {
                        visit[newDir][robot.y2][robot.x2] = true;
                        visit[newDir][ny2][nx2] = true;
                        queue.offer(new Robot( ny2, nx2, robot.y2, robot.x2, robot.t + 1));
                    }

                }
            }

        }

        return answer;
    }
}
