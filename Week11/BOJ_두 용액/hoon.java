import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int l = 0, r = N - 1;
        int min = Integer.MAX_VALUE;
        int n1 = 0, n2 = 0;

        while (l < r) {
            int sum = arr[l] + arr[r];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                n1 = arr[l];
                n2 = arr[r];
            }

            if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }

        bw.write(n1 + " " + n2);
        bw.flush();
    }
}
