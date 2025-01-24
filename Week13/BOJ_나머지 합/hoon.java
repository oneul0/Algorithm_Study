import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] count = new long[M];
        count[0] = 1;
        long ans = 0;
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sum = (sum + Integer.parseInt(st.nextToken())) % M;
            count[sum]++;
        }
        for(int i = 0; i<M; i++){
            long cnt = count[i];
            if(cnt < 0) continue;
            ans += cnt * (cnt - 1) / 2;
        }

        bw.write(ans + "");
        bw.flush();
        br.close();
        bw.close();
    }
}
