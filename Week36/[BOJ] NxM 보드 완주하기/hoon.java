import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int[] dx = {-1,1,0,0}, dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        int idx = 1;
        while(true){
            String line = br.readLine();
            if(line == null) break;

            StringTokenizer st = new StringTokenizer(line);
            if(!st.hasMoreTokens()) break;
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            boolean[][] board = new boolean[N+2][M+2];
            for(int i = 0; i<N+2; i++){
                Arrays.fill(board[i], true);
            }

            int blankCount = 0;
            for(int i = 0; i<N; i++){
                String str = br.readLine();
                for(int j = 0; j<M; j++){
                    board[i+1][j+1] = str.charAt(j) == '*';
                    if(!board[i+1][j+1]) blankCount++;
                }
            }
            if(blankCount == 1){
                System.out.println("Case " + idx + ": " + 0);
                idx++;
                continue;
            }
            int ans = 1_000_001;
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= M; j++){
                    if(!board[i][j]){
                        for(int d = 0; d < 4; d++){
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if(board[nx][ny]) continue;

                            board[i][j] = true;
                            board[nx][ny] = true;

                            ans = Math.min(ans, play(nx, ny, 1, d, board, blankCount - 2, N, M));

                            board[nx][ny] = false;
                            board[i][j] = false;
                        }
                    }
                }
            }

            if(ans > 1_000_000) System.out.println("Case " + idx + ": -1");
            else System.out.println("Case " + idx + ": " + ans);
            idx++;
        }
    }

    public static int play(int cx, int cy, int moveCount, int directionIdx, boolean[][] board, int blankCount, final int N, final int M){
        if(blankCount == 0){
            return moveCount;
        }

        int result = 1_000_001;

        int tx = cx + dx[directionIdx];
        int ty = cy + dy[directionIdx];

        if(board[tx][ty]){
            for(int i = 0; i < 4; i++){
                if(i == directionIdx) continue;

                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 1 || ny < 1 || nx > N || ny > M || board[nx][ny]) continue;

                board[nx][ny] = true;
                result = Math.min(result, play(nx, ny, moveCount + 1, i, board, blankCount - 1, N, M));
                board[nx][ny] = false;
            }
        } else {
            board[tx][ty] = true;
            result = Math.min(result, play(tx, ty, moveCount, directionIdx, board, blankCount - 1, N, M));
            board[tx][ty] = false;
        }


        return result;
    }
}
