import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] map;
    static boolean[][] visited;
    static int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static int N;
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> ans = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        map = new String[N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i].charAt(j) == '1' && !visited[i][j]) {
                    ans.add(bfs(i, j));
                }
            }
        }
        bw.write(ans.size() + "\n");
        ans.sort((o1, o2) -> o1 - o2);
        for(int a : ans){
            bw.write(a + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int sx, int sy){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            int[] cur = q.remove();
            for(int i = 0; i<4; i++){
                int nx = cur[0] + d[i][0];
                int ny = cur[1] + d[i][1];
                if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny] || map[nx].charAt(ny) == '0') continue;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                cnt++;
            }
        }
        return cnt;
    }


}
