import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] inDegrees, ans;
    static ArrayList<ArrayList<Integer>> gr = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegrees = new int[N+1];
        ans = new int[N+1];
        for(int i = 0; i<=N; i++) gr.add(new ArrayList<>());
        for(int i = 0; i<M; i++){
            String[] in = br.readLine().split(" ");
            int a = Integer.parseInt(in[0]);
            int b = Integer.parseInt(in[1]);

            gr.get(a).add(b);
            inDegrees[b]++;
        }

        topologySort();
        for(int i = 1; i<=N; i++){
            bw.write(ans[i]+" ");
        }
        bw.flush();
        br.close();
        bw.close();

    }

    static void topologySort(){
        Deque<int[]> q = new ArrayDeque<>();
        int cycle = 1;
        for(int i = 1; i<=N; i++){
            if(inDegrees[i] == 0){
                q.offer(new int[]{i,cycle});
                ans[i] = cycle;
            }
        }

        while(!q.isEmpty()){
            int[] x = q.remove();
            ans[x[0]] = x[1];
            for(int j = 0; j<gr.get(x[0]).size(); j++){
                int y = gr.get(x[0]).get(j);
                if(--inDegrees[y] == 0){
                    q.offer(new int[]{y, x[1]+1});
                }
            }
        }
    }
}