import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] ans = new int[2];
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<n; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        if(isAllSame(0, 0, n)){
            ans[paper[0][0]]++;
        }
        else{
            search(0, 0, n);
        }

        System.out.println(ans[0]);
        System.out.println(ans[1]);

    }

    static void search(int sx, int sy, int len){
        if(isAllSame(sx, sy, len)){
            ans[paper[sx][sy]]++;
            return;
        }

        int newLen = len/2;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                search(sx+(i*newLen), sy+(j*newLen), newLen);
            }
        }
    }

    static boolean isAllSame(int sx, int sy, int len){
        int val = paper[sx][sy];
        for(int i = sx; i<sx+len; i++){
            for(int j = sy; j<sy+len; j++){
                if(val != paper[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
