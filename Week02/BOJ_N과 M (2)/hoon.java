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

        bt(0,1);
    }
    //idx는 자릿수 num은 시작수(num~n까지 방문하지 않았던 숫자를 추가하기 위함)
    static void bt(int idx, int num){
        if(idx == m){
            for(int i = 0; i<m; i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }
        for(int i = num; i<=n; i++){
            if(!chk[i]){
                chk[i] = true;
                arr[idx] = i;
                bt(idx+1, i+1);
                chk[i] = false;
            }
        }
    }
}
