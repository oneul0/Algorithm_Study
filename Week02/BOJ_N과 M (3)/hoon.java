import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n,m;
    static boolean[] chk = new boolean[9];
    static int[] arr = new int[9];
    public static void main(String[] args) throws IOException {
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        bt(0);
        br.close();
        bw.flush();
        bw.close();
    }
    static void bt(int idx) throws IOException {
        if(idx == m){
            for(int i = 0; i<m; i++){
                bw.write(arr[i]+" ");
            }
            bw.newLine();
            return;
        }
        for(int i = 1; i<=n; i++){
            chk[i] = false;
            if(!chk[i]){
                chk[i] = true;
                arr[idx] = i;
                bt(idx+1);
            }
        }
    }
}
