//https://www.acmicpc.net/problem/7576
import java.io.*;
import java.util.*;

// 각 토마토를 시작점으로 하여 너비 우선 탐색을 실시한다
// 도달할 수 있는 모든 곳에 도달하게 되면 가장 마지막 탐색까지 걸린 일수를 구한다.
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        String[] mn = br.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        boolean[][] tomatos = new boolean[n][m];
        Queue<Pair> q = new ArrayDeque<>();
        for(int i = 0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0; j<m; j++){
                if(line[j].equals("1")){
                    tomatos[i][j] = true;
                    q.offer(new Pair(i, j, 0));
                }
                else if(line[j].equals("-1")){
                    tomatos[i][j] = true;
                }

            }
        }
        int answer = 0;
        while(!q.isEmpty()){
            Pair p = q.remove();
            for(int i = 0; i<4; i++){
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m && !tomatos[nx][ny]){
                    tomatos[nx][ny] = true;
                    q.offer(new Pair(nx, ny, p.day+1));
                }
            }
            answer = p.day;
        }

        boolean flag = false;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(!tomatos[i][j]){
                    flag = true;
                }
            }
        }
        if(flag){
            bw.write("-1");
        }
        else{
            bw.write(Integer.toString(answer));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}

class Pair{
    int x, y, day;
    public Pair(int x, int y, int day){
        this.x = x;
        this.y = y;
        this.day = day;
    }
}