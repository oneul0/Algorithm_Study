import java.io.*;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] DP, A, B;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");

        DP = new int[N+1]; A = new int[N+1]; B = new int[N+1];

        for(int i = 0; i < N; i++){
            A[i+1] = Integer.parseInt(in[i]);
        }

        B = new int[N+1];
        int len = 0;
        for(int num:A){
            int pos = binarySearch(len+1, num);

            B[pos] = num;

            if(pos > len){
                len++;
            }
        }
        bw.write(len+"");


        bw.flush();
        br.close();
        bw.close();

    }

    static int binarySearch(int r, int target){
        int mid, l = 0;
        while(l < r){
            mid = (l+r)/2;
            if(B[mid] < target){
                l = mid + 1;
            }
            else{
                r = mid;
            }

        }
        return l;
    }
}
