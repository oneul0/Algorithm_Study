import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] st = br.readLine().split(" ");
        int[] pos = new int[N + 1];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st[i]);
            pos[num] = i;
        }

        int maxLen = 1;
        int cnt = 1;

        for (int i = 2; i <= N; i++) {
            if (pos[i] > pos[i - 1]) {
                cnt++;
                maxLen = Math.max(maxLen, cnt);
            } else {
                cnt = 1;
            }
        }

        System.out.println(N - maxLen);
    }
}
