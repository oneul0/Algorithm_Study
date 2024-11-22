import java.io.*;

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
        int maxlen = 0, idx;
        DP[1] = 1;
        B[++maxlen] = A[1];
        //가장 긴 증가하는 부분 수열
        for(int i = 2; i <= N; i++){
            if(A[i] > B[maxlen]){
                // 가장 마지막 수열보다 현재 수열이 클 때
                B[++maxlen] = A[i];
                DP[i] = maxlen;
            } else{
                // B 배열에서 처음으로 크거나 같아지는 원소의 index 찾기
                idx = binarySearch(1, maxlen, A[i]);
                B[idx] = A[i];
                DP[i] = idx;
            }
        }
        bw.write(maxlen+"\n");

        idx = maxlen;
        int[] arr = new int[maxlen+1];
        int x = B[maxlen]+1;
        for(int i = N; i>0; i--){
            if(DP[i] == idx && A[i] < x){
                arr[idx] = A[i];
                x = A[i];
                idx--;
            }
        }
        for(int i = 1; i<=maxlen; i++){
            bw.write(arr[i]+" ");
        }

        bw.flush();
        br.close();
        bw.close();

    }

    static int binarySearch(int l, int r, int target){
        int mid;
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
