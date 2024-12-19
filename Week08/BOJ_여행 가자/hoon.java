import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] pr;
    static int N, M;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        pr = new int[N+1];

        for(int i = 0; i<=N; i++) pr[i] = i;

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                //i와 j가 1~N의 형태로 맞춰져 있으므로 1로 표시된 위치에서 i,j에 대해 union 연산 실시
                if(st.nextToken().equals("1")){
                    union(i, j);
                }
            }
        }

        boolean fg = true;
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        for(int i = 1; i<M; i++){
            //모든 도착지가 출발지와 연결되어 있어야 하므로 연결되어 있지 않으면 실패
            if(find(s) != find(Integer.parseInt(st.nextToken()))) {
                fg = false;
                break;
            }
        }

        if(fg) bw.write("YES");
        else bw.write("NO");
        bw.flush();
        br.close();
        bw.close();
    }

    //parent 테이블을 이용한 부모 노드 찾기
    static int find(int a){
        if(pr[a] == a) return a;
        return find(pr[a]);
    }

    //각 노드의 부모를 찾고, 부모가 같지 않으면 부모 노드의 크기에 따라(작으면 상위 노드로 함) 합치는 연산
    static void union(int a, int b){
        int x = find(a);
        int y = find(b);
        if(x != y){
            //1~N까지 차례대로 순서가 매겨져 있으므로 숫자가 더 큰 노드를 하위 노드로 병합
            if(x > y) pr[x] = y;
            else pr[y] = x;
        }
    }
}
