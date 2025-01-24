import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        /*
        * 각 컬러 확률 더하기
        * */

        int m = Integer.parseInt(br.readLine());
        int[] color = new int[m];
        double[] percent = new double[m];
        int n = 0;

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<m; i++) {
            color[i] = Integer.parseInt(st.nextToken());
            n+=color[i];
        }

        int k = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {

            int cnt = color[i];

            double temp = 1.0;

            if(color[i] < k) continue;

            for(int j=0; j<k; j++) {
                temp *= (double) (cnt - j) / (n-j);
            }

            percent[i] = temp;

        }


        double answer = 0.0;
        for(double temp : percent) {
            answer+=temp;
        }


        bw.write(String.format("%.9f", answer));
        bw.close();



    }


    public static void main(String[] args) throws IOException {
        solution();
    }



}
