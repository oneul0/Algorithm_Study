import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int n,m;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        bt(new ArrayList<>(), new boolean[n+1]);

    }

    //백트래킹
    static void bt(ArrayList<Integer> arr, boolean[] chk) {
        if(arr.size() == m){
            for(int i = 0; i<arr.size(); i++){
                System.out.print(arr.get(i)+" ");
            }
            System.out.println();
            return;
        }
        for(int i = 1; i<=n; i++){
            if(!chk[i]){
                chk[i] = true; // 방문처리
                arr.add(i); // 요소 추가
                bt(arr, chk);
                chk[i] = false; // 방문처리취소
                arr.remove(arr.size()-1); // 요소 추가 취소
            }
        }
    }
}
