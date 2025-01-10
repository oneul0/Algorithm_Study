import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] arr = new long[N+1];
        long max = 0;
        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=N;i++) {
            arr[i] = arr[i-1]+Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N-M+1;i++) {
            max = Math.max(max, arr[i+M]-arr[i]);
        }
        System.out.println(max);
    }
}