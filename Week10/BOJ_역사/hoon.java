import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] in;
    static int n, k, s;
    static int[][] dist;
    public static void main(String[] args) throws Exception{
        in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);
        k = Integer.parseInt(in[1]);

        dist = new int[n+1][n+1];

        for(int i = 0; i<k; i++){
            in = br.readLine().split(" ");
            int n1 = Integer.parseInt(in[0]);
            int n2 = Integer.parseInt(in[1]);
            dist[n1][n2] = -1;
            dist[n2][n1] = 1;
        }
        floyd();
        s = Integer.parseInt(br.readLine());
        for(int i = 0; i<s; i++){
            in = br.readLine().split(" ");
            int n1 = Integer.parseInt(in[0]);
            int n2 = Integer.parseInt(in[1]);
            bw.write(dist[n1][n2]+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static void floyd(){
        //플로이드 워셜
        for(int m = 1; m<=n; m++){ //가운데 노드
            for(int s = 1; s<=n; s++){ //시작 노드
                for(int e = 1; e<=n; e++){
                    if(dist[s][e] == 0){
                        if(dist[s][m] == 1 && dist[m][e] == 1){
                            dist[s][e] = 1;
                        }
                        else if(dist[s][m] == -1 && dist[m][e] == -1){
                            dist[s][e] = -1;
                        }
                    }
                }
            }
        }
    }
}
