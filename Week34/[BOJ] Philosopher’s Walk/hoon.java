import java.io.*;
import java.util.*;

public class Main {
    public static int[] hilbert(int k, long m) {
        if (k == 0) {
            return new int[]{0, 0};
        }

        int half = 1 << (k - 1);  // 2^(k-1)
        long quadSize = (long)half * half; // 각 사분면의 크기 (걸음 수 기준)

        int quad = (int)((m - 1) / quadSize);
        // 현재 사분면 내에서의 상대적인 걸음 수
        long pos = (m - 1) % quadSize + 1;

        int[] sub = hilbert(k - 1, pos);
        int x = sub[0], y = sub[1];

        switch (quad) {
            case 0: // 왼쪽 아래 사분면 - 시계방향 90도 회전
                return new int[]{y, x};
            case 1: // 왼쪽 위 사분면 - 그대로, y좌표만 half만큼 위로
                return new int[]{x, y + half};
            case 2: // 오른쪽 위 사분면 - 그대로, x,y 모두 half만큼 이동
                return new int[]{x + half, y + half};
            case 3: // 오른쪽 아래 사분면 - 반시계방향 90도 회전 + 반전
                return new int[]{half - 1 - y + half, half - 1 - x};
        }

        return new int[]{0, 0};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 격자 크기 (2^k)
        long m = Long.parseLong(st.nextToken());   // 걸음 수 (1-based)

        int k = 0;
        int temp = n;
        while (temp > 1) {
            temp /= 2;
            k++;
        }

        int[] result = hilbert(k, m);
        System.out.println((result[0] + 1) + " " + (result[1] + 1));

        br.close();
    }
}