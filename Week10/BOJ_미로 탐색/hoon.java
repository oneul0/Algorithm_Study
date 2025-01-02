import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        String[] map = new String[N];
        for(int i = 0; i<N; i++){
            map[i] = br.readLine();
        }

        Deque<int[]> q = new ArrayDeque<>();
        int[][] chk = new int[N][M];
        q.push(new int[]{0,0});
        chk[0][0] = 1;

        while(!q.isEmpty()){
            int[] cur = q.remove();
            if(cur[0] == N-1 && cur[1] == M-1) continue;
            for(int i = 0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0||nx>=N|| ny<0||ny>=M|| map[nx].charAt(ny) == '0' ||chk[nx][ny] != 0) continue;
                q.offer(new int[]{nx,ny});
                chk[nx][ny] = chk[cur[0]][cur[1]]+1;
            }
        }
        bw.write(chk[N-1][M-1]+"");
        bw.flush();
        br.close();
        bw.close();
    }
}
