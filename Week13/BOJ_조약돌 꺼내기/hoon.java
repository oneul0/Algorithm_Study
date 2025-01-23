import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int M, K,N=0;
    static int[] arr;
    static double[] p;
    public static void main(String[] args) throws Exception{
        M = Integer.parseInt(br.readLine());
        arr = new int[M];
        p = new double[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            N += arr[i];
        }
        K = Integer.parseInt(br.readLine());

        for(int i = 0; i<M; i++){
            if(arr[i] < K) continue; //같은 색을 조약돌 갯수가 부족한 경우
            double tmp = 1.0;
            for(int j = 0; j<K; j++){
                tmp *= (double) (arr[i] - j) /(N-j);
            }
            p[i] = tmp;
        }
        double ans = 0;
        for(double num : p) ans+=num;

        bw.write(String.format("%.9f", ans));
        bw.flush();
        br.close();
        bw.close();
    }
}
