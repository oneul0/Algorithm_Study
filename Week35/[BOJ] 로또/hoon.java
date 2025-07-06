import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void combination(int depth, int start, int[] arr, int[] ans) {
        if(depth == 6){
            for(int i = 0; i<6; i++){
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i<arr.length; i++){
            ans[depth] = arr[i];
            combination(depth+1, i+1, arr, ans);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 1 && st.nextToken().equals("0")) break;
            int M = Integer.parseInt(st.nextToken());
            int[] arr = new int[M];
            for(int i = 0; i<M; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            combination(0, 0, arr, new int[6]);
            System.out.println();
        }

    }
}
