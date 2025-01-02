import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        * 사건의 개수 n(노드의 개수), 사건의 전후관계 개수 k(노선 개수)
        * 노선에서 앞의 노드 : 먼저 일어난 사건
        * 이후 s 알고 싶은 노선의 개수
        * 앞의 사건이 먼저 -1, 뒤의 사건이 먼저 1, 모르면 0
        * 가장 먼저 일어난 사건이 존재
        * map에 추가하면서 앞 노드 key, 뒷 노드 value해서
        * 노드 중 value에 존재하지않는 노드 -> 시작노드
        * 단방향 맵으로 설정하면 노선에 대해 전체 파악 불가
        * key, value [node, 키에 대해 이전이면 -1, 이후면 1]
        * bfs가 아니라 그냥 모든 노드를 방문하면서 가중치가 -1>=이면 이전, 1>=이면 이후 0이면 모른다로 하는게 맞아보인다.
        *
        * */

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n+1][n+1];


        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int f_node = Integer.parseInt(st.nextToken());
            int b_node = Integer.parseInt(st.nextToken());
            dist[f_node][b_node] = -1;
            dist[b_node][f_node] =  1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                for(int l = 1; l <= n; l++) {
//                    System.out.println("i : "+ i+" j :" + j + " l : " + l + " " +dist[j][l]);
                    if(dist[j][i] != 0 && dist[i][l] != 0) {
                        if(j != i && i != l && dist[j][i] == -1 && dist[i][l] == -1) {
                            dist[j][l] = -1;
                        } else if(j != i && i != l && dist[j][i] == 1 && dist[i][l] == 1) {
                            dist[j][l] = 1;
                        }
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());

        for(int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int node_a = Integer.parseInt(st.nextToken());
            int node_b = Integer.parseInt(st.nextToken());
//            System.out.println("node "+node_a+", "+node_b+" length : "+dist[node_a][node_b]);
            bw.write(String.valueOf(dist[node_a][node_b]));
            if(i != s-1) {
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }



}
