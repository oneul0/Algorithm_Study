import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[][] space;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    static boolean[][] chk;
    static int R,C;
    static Queue<Pair> fireq = new ArrayDeque<>(), hoonq = new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        StringTokenizer st= new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        space = new char[R][C];
        chk = new boolean[R][C];

        for(int i = 0; i<R; i++){
            char[] in = br.readLine().toCharArray();
            for(int j = 0; j<C; j++){
                space[i][j] = in[j];
                if(space[i][j] == 'J') {
                    space[i][j] = '.';
                    hoonq.offer(new Pair(i, j, 0));
                    chk[i][j] = true;
                }
                else if(space[i][j] == 'F') fireq.offer(new Pair(i,j,0));
            }
        }

        int res = bfs();
        if(res == -1) bw.write("IMPOSSIBLE");
        else bw.write(res+"");

        bw.flush();
        br.close();
        bw.close();
    }
    static int bfs(){
        while(!hoonq.isEmpty()){
            int fireSize = fireq.size();
            for (int i = 0; i < fireSize; i++) {
                Pair fire = fireq.remove();
                for (int d = 0; d < 4; d++) {
                    int nx = fire.r + dx[d];
                    int ny = fire.c + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < R && ny < C && space[nx][ny] == '.') {
                        space[nx][ny] = 'F';
                        fireq.offer(new Pair(nx, ny, fire.dist + 1));
                    }
                }
            }

            int jihoonSize = hoonq.size();
            for(int i = 0; i <jihoonSize; i++){
                Pair p = hoonq.remove();
                for(int d = 0; d <4; d++){
                    int nx = p.r+dx[d];
                    int ny = p.c+dy[d];
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) return p.dist+1;

                    if(space[nx][ny] == '.' && !chk[nx][ny]){
                        chk[nx][ny] = true;
                        hoonq.offer(new Pair(nx, ny, p.dist+1));
                    }
                }
            }
        }
        return -1;
    }
}

class Pair{
    int r, c, dist;
    Pair(int r, int c, int dist){
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}
