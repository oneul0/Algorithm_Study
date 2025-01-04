import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n,m;
    public static void main(String[] args) throws Exception{
        String[] in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);
        m = Integer.parseInt(in[1]);
        int[] arr = new int[n];

        in = br.readLine().split(" ");
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(in[i]);
        }

        long max = 0;
        for(int i = 0; i<n-m; i++){
            long tmp = 0;
            for(int d = i; d<i+m; d++){
                tmp+=arr[d];
            }
            max = Math.max(max, tmp);
        }

        bw.write(max+"");
        bw.flush();
        br.close();
        bw.close();
    }
}
