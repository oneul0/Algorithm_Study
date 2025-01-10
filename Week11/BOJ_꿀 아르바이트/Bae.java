import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        * 일마다 급여 정해져있다
        * 정해진 일 수 만큼 일
        * 한번이라도 퇴직 -> 끝, 한번 취직하면 끝까지
        * n = 총 일수, m = 윈도우 길이
        *
        *
        * */

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] incomes = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            incomes[i] = Integer.parseInt(st.nextToken());
        }

//      int -> long
//      1,000,000 × 100,000 = 100,000,000,000
//      이거까지 가능
        
        long answer = 0;

        if(m == 0) {
            bw.write("0");
        } else {
            long sum = 0;

            for(int i = 0; i < m; i++) {
                sum += incomes[i];
            }
            answer = sum;

            for(int i = m; i < n; i++) {
                sum = sum - incomes[i-m] + incomes[i];
                answer = Math.max(answer, sum);
            }

            bw.write(String.valueOf(answer));
        }

        bw.flush();
        bw.close();


    }

    public static void main(String[] args) throws IOException {
        solution();
    }



}
