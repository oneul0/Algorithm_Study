import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, ans;
    static int[] arr, status;
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            ans = n;
            arr = new int[n+1]; status = new int[n+1];
            String[] s = br.readLine().split(" ");
            for(int i = 0; i<n; i++){
                arr[i+1] = Integer.parseInt(s[i]);
            }

            for(int i = 1; i<=n; i++){
                if(status[i] == 0){
                    dfs(i);
                }
            }

            bw.write(ans+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    //방문 상태
    // 0 : 미방문, 1: 탐색 중(각 탐색에서 탐색되었는지), 2: 탐색 완료
    static void dfs(int node){
        status[node] = 1;
        int nxt = arr[node];

        if(status[nxt] == 0){
            dfs(nxt);
        }
        else if(status[nxt] == 1){
            int cycleSize = 0;
            for(int cur = nxt; cur != node; cur=arr[cur]){
                cycleSize++;
            }
            cycleSize++;
            ans -= cycleSize;
        }

        status[node] = 2;
    }
}
