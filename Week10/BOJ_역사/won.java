import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N, M 입력 받기
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        boolean[][] reachable = new boolean[n+1][n+1];
        for(int i = 1; i <= n; i++){
            // 자기 자신은 항상 도달 가능
            reachable[i][i] = true;
        }


        // 전 후 관계
        for(int i=0; i<k; i++){
            String [] temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]);
            int to = Integer.parseInt(temp[1]);
            reachable[from][to] = true;
        }

        // 플로이드 워셜 알고리즘
        for(int mid = 1; mid <= n; mid++){
            for(int from = 1; from <= n; from++){
                for(int to = 1; to <= n; to++){
                    if(reachable[from][mid] && reachable[mid][to]){
                        reachable[from][to] = true;
                    }
                }
            }
        }



        // s: 관계를 알고 싶은 쌍의 수
        int s = Integer.parseInt(br.readLine());
        for(int i=0; i<s; i++){
            String [] temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]);
            int to = Integer.parseInt(temp[1]);

            if(reachable[from][to]){
                bw.write("-1\n");
            } else if( reachable[to][from]) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
            bw.flush();
        }

        // flush 및 close
        bw.flush();
        bw.close();
        br.close();
    }
}
