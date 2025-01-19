import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs(N);
        System.out.println(ans+"");
        br.close();
    }

    static void bfs(int sx){
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] chk = new boolean[100001];
        chk[N] = true;
        q.add(new int[]{sx,0});
        while(!q.isEmpty()){
            int[] cur = q.remove();
            if(cur[0] == K){
                ans = Math.min(ans, cur[1]);
                break;
            }
            for(int i : new int[]{cur[0]-1, cur[0]+1, cur[0]*2}){
                if(isValid(i, chk)) {
                    chk[i] = true;
                    q.offer(new int[]{i, cur[1]+1});
                }
            }
        }
    }

    static boolean isValid(int x, boolean[] chk){
        return x>=0 && x<100001 && !chk[x];
    }
}
