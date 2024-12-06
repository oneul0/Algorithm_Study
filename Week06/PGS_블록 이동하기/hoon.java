import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
        int len = board.length;
        boolean[][][] chk = new boolean[2][len][len];
        Deque<Robot> q = new ArrayDeque<>();
        q.add(new Robot(0,0, new Pair(0,0), new Pair(0,1)));

        while(!q.isEmpty()){
            Robot robo = q.remove();

            //범위 초과
            if(robo.p1.x<0 || robo.p1.y<0 || robo.p1.x>=len || robo.p1.y>=len || robo.p2.x<0 || robo.p2.y<0 || robo.p2.x>=len || robo.p2.y>=len) continue;

            //벽
            if(board[robo.p1.x][robo.p1.y] == 1 || board[robo.p2.x][robo.p2.y] == 1) continue;

            //이미 방문했을 경우
            if(chk[robo.vertical][robo.p1.x][robo.p1.y] && chk[robo.vertical][robo.p2.x][robo.p2.y]) continue;

            //종료
            if((robo.p1.x==len-1 && robo.p1.y==len-1) || (robo.p2.x==len-1 && robo.p2.y==len-1)){
                answer = robo.t;
                break;
            }

            chk[robo.vertical][robo.p1.x][robo.p1.y] = true;
            chk[robo.vertical][robo.p2.x][robo.p2.y] = true;

            for(int i = 0; i<4; i++){
                int nx1 = robo.p1.x+dx[i];
                int ny1 = robo.p1.y+dy[i];
                int nx2 = robo.p2.x+dx[i];
                int ny2 = robo.p2.y+dy[i];
                q.offer(new Robot(robo.t+1, robo.vertical, new Pair(nx1, ny1), new Pair(nx2, ny2)));
            }

            //회전
            if(robo.vertical == 1){
                //수직인 경우 -> 좌우 2칸 확인
                if(robo.p1.y-1 >= 0 && board[robo.p1.x][robo.p1.y-1] == 0 && board[robo.p2.x][robo.p2.y-1] == 0){
                    q.offer(new Robot(robo.t+1, 0, new Pair(robo.p1.x, robo.p1.y), new Pair(robo.p1.x, robo.p2.y-1)));
                    q.offer(new Robot(robo.t+1, 0, new Pair(robo.p2.x, robo.p1.y-1), new Pair(robo.p2.x, robo.p2.y)));
                }
                if(robo.p1.y+1 < len && board[robo.p1.x][robo.p1.y+1] == 0 && board[robo.p2.x][robo.p2.y+1] == 0){
                    q.offer(new Robot(robo.t+1, 0, new Pair(robo.p1.x, robo.p1.y), new Pair(robo.p1.x, robo.p2.y+1)));
                    q.offer(new Robot(robo.t+1, 0, new Pair(robo.p2.x, robo.p1.y+1), new Pair(robo.p2.x, robo.p2.y)));
                }
            }
            else{
                //수평인 경우 -> 상하 2칸 확인
                if(robo.p1.x-1 >= 0 && board[robo.p1.x-1][robo.p1.y] == 0 && board[robo.p2.x-1][robo.p2.y] == 0){
                    q.offer(new Robot(robo.t+1, 1, new Pair(robo.p1.x-1, robo.p2.y), new Pair(robo.p2.x, robo.p2.y)));
                    q.offer(new Robot(robo.t+1, 1, new Pair(robo.p1.x, robo.p1.y), new Pair(robo.p2.x-1, robo.p1.y)));
                }
                if(robo.p1.x+1 < len && board[robo.p1.x+1][robo.p1.y] == 0 && board[robo.p2.x+1][robo.p2.y] == 0){
                    q.offer(new Robot(robo.t+1, 1, new Pair(robo.p1.x+1, robo.p2.y), new Pair(robo.p2.x, robo.p2.y)));
                    q.offer(new Robot(robo.t+1, 1, new Pair(robo.p1.x, robo.p1.y), new Pair(robo.p2.x+1, robo.p1.y)));
                }
            }
        }

        return answer;
    }
}

class Robot{
    Pair p1, p2;
    int t, vertical;
    Robot(int t, int vertical, Pair p1, Pair p2){
        this.t = t;
        this.vertical = vertical;
        this.p1 = p1;
        this.p2 = p2;
    }
}

class Pair{
    int x, y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}