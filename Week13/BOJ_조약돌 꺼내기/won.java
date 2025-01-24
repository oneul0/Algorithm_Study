// 1시간
/*
* input
첫째 줄에 M (1 ≤ M ≤ 50)이 주어진다.
둘째 줄에는 각 색상의 조약돌이 몇 개 있는지 주어진다. 각 색상의 조약돌 개수는 1보다 크거나 같고 50보다 작거나 같은 자연수이다.
셋째 줄에는 K가 주어진다. (1 ≤ K ≤ N)
* */
import java.io.*;
import java.math.*;
import java.util.*;
public class Main {

    public static double combi(int n, int r) {
        if (r > n || n < 0 || r < 0) {
            return 0;
        }
        double numerator = 1; // 분자
        double denominator = 1; // 분모
        for (int i = 0; i < r; ++i) {
            numerator *= (n - i);
            denominator *= (i + 1);
        }
        return numerator / denominator;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        String [] num_tmp = br.readLine().split(" ");
        int[] num_color = new int[m];
        int n=0;

        for (int i = 0; i < m; i++) {
            num_color[i] = Integer.parseInt(num_tmp[i]);
            n += num_color[i];
        }
        int k = Integer.parseInt(br.readLine());

        if(k > n) {
            // 뽑을 조약돌 수가 전체보다 많다면 0
            bw.write("0.0\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        if( m ==1 || k == 1){
            bw.write("1.0\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        // 박스에는 조약돌이 N개 들어있다. 조약돌의 색상은 1부터 M까지 중의 하나
        // 박스에서 조약돌을 랜덤하게 K개 뽑았을 때, 뽑은 조약돌이 모두 같은 색일 확률을 구하는 프로그램

        double totalCases = combi(n,k);

        // 색이 하나 뿐이거나 뽑는 조약돌이 1개이면 확률은 무조건 1
        double numCases = 0;
        for (int i = 0; i < m; i++) {
            if (num_color[i] >= k) {
                numCases += combi(num_color[i], k);
            }
        }
        bw.write("numCases: " + numCases + "\n");
        bw.write("total_cases: " + totalCases + "\n");
        double answer = numCases/ totalCases;



        // 정답 출력
        bw.write(String.format("%.9f\n", answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
