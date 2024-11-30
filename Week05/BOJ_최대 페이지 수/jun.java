import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;
    private static int[] day, page;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 최대 200
        m = Integer.parseInt(st.nextToken()); // 최대 20
        day = new int[m];
        page = new int[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            page[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0,n);
        System.out.println(answer);

    }

    public static void dfs(int idx, int pages, int remain) {
        if (idx == m || remain < 0) {
            answer = Math.max(answer, pages);
            return;
        }

        dfs(idx + 1, pages, remain);
        if (remain >= day[idx]) {
            dfs(idx + 1, pages + page[idx], remain - day[idx]);
        }
    }
}
