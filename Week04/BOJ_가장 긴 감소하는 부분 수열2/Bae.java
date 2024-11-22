import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n  = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int index = 1;

        arr[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i < n; i++){
            int value = Integer.parseInt(st.nextToken());
            if(arr[index-1] < value){
                index++;
                arr[index-1] = value;
            } else {
                int left = 0;
                int right = index;
                while(left < right){
                    int mid = (right + left) / 2;
                    if(arr[mid] < value){
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                arr[left] = value;
            }
        }

        bw.write(String.valueOf(index));
        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
