import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int A, B, C;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        System.out.println(pow(A, B));
    }

    public static long pow(long base, long exp) {
        if (exp == 0) return 1;
        if (exp == 1) return base % C;

        long half = pow(base, exp / 2);
        long result = (half * half) % C;

        if (exp % 2 == 1) {
            result = (result * base) % C;
        }

        return result;
    }
}
