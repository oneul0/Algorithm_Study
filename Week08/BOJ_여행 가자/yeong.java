import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int parent[];

    // 부모찾기
    static int find (int x) {
        if (x == parent[x]) return x; // 루트 노드
        return parent[x] = find(parent[x]);
    }

    // 부모 합치기
    static void union (int x, int y) {
        x = find(x);
        y = find(y);
        if (x!=y) {
            if (x>y) parent[x] = y;
            else parent [y] = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(bf.readLine());

        // 부모 배열 초기화
        parent = new int [N+1];
        for (int i=1; i<N+1; i++) parent[i] =i;

        // 연결 정보 입력
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=1; j<N+1; j++) {
                int sel = Integer.parseInt(st.nextToken());
                if (sel == 1) union(i, j);
            }
        }

        // 여행 계획 입력
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken()); // 시작점
        boolean flag = true;
        for (int i=1; i<M; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (find(start) == find(p)) continue;
            flag = false; // 부모가 같지 않으면 여행 불가능
        }

        // 출력
        if (flag == true) System.out.println("YES");
        else System.out.println("NO");
    }

}