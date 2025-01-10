import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[][] cost;
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        cost = new long[n+1][n +1];
        for(int i =0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                cost[i][j] = Integer.MAX_VALUE;
                if(i == j) cost[i][j] = 0;
            }
        }

        for(int i = 0; i< M; i++){
            String[] in = br.readLine().split(" ");
            int a = Integer.parseInt(in[0]);
            int b = Integer.parseInt(in[1]);
            int c = Integer.parseInt(in[2]);
            cost[a][b] = Math.min(cost[a][b], c);
        }

        for(int m = 1; m<=n; m++){
            for(int s = 1; s<=n; s++){
                for(int e = 1; e<=n; e++){
                    cost[s][e] = Math.min(cost[s][e], cost[s][m]+cost[m][e]);
                }
            }
        }

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(cost[i][j] == Integer.MAX_VALUE) cost[i][j] = 0;
                bw.write(cost[i][j]+" ");
            }
            bw.newLine();
            bw.flush();
        }
    }
}
