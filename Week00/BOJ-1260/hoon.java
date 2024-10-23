import java.io.*;
import java.util.*;


// dfs의 종료 조건이 뭔지, 추가 시 어떤 작업을 해야하는지를 생각하였고,
// bfs의 시작 지점이 어딘지, 언제 노드를 출력해야하는지를 생각하며 풀어보았습니다.

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n,m,v;
    static ArrayList<ArrayList<Integer>> gr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");

        n = Integer.parseInt(in[0]);
        m = Integer.parseInt(in[1]);
        v = Integer.parseInt(in[2]);

        for(int i = 0; i<=n; i++){
            gr.add(new ArrayList<>());
        }
        for(int i = 0; i<m; i++){
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            gr.get(a).add(b);
            gr.get(b).add(a);
        }

        // 그래프 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(gr.get(i));
        }

        boolean[] chk = new boolean[n+1];
        chk[0] = chk[v] = true;
        dfs(v, 0, chk);

        chk = new boolean[n+1];
        System.out.println();
        bfs(v, chk);
    }

    static void dfs(int cur, int depth, boolean[] chk){
        System.out.print(cur+" ");
        if(depth == n) return;

        for(int nx : gr.get(cur)){
            if(!chk[nx]){
                chk[nx] = true;
                dfs(nx, depth + 1, chk);
            }
        }
    }

    static void bfs(int cur, boolean[] chk){
        Queue<Integer> q = new LinkedList<>();
        q.add(cur);

        chk[cur] = true;

        while(!q.isEmpty()){
            int cx = q.remove();
            System.out.print(cx+" ");
            for(int nx : gr.get(cx)){
                if(!chk[nx]){
                    q.add(nx);
                    chk[nx] = true;
                }
            }
        }

    }
}
