import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0; i<N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    int l = 0, r = arr.length-1, goal = Integer.MAX_VALUE, tmpL = 0, tmpR = 0;
    while(l<r){
      int sum = arr[l] + arr[r];
      if(Math.abs(sum) < goal){
        goal = Math.abs(sum);
        tmpL = l;
        tmpR = r;
      }
      else if(sum < 0){
        l++;
      }
      else{
        r--;
      }
    }

    System.out.print(arr[tmpL]+" "+arr[tmpR]);
  }
}
