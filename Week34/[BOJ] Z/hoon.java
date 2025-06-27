import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, r, c;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(simulation(N, r, c));
    }

    static int simulation(int n, int r, int c){
        if (n == 0) return 0;

        int half = 1 << (n - 1);
        int quadrantSize = half * half;

        //1사분면
        if (r < half && c < half) {
            return simulation(n - 1, r, c);
            //2사분면
        } else if (r < half && c >= half) {
            return quadrantSize + simulation(n - 1, r, c - half);
            //3사분면
        } else if (r >= half && c < half) {
            return 2 * quadrantSize + simulation(n - 1, r - half, c);
            //4사분면
        } else {
            return 3 * quadrantSize + simulation(n - 1, r - half, c - half);
        }
    }
}
