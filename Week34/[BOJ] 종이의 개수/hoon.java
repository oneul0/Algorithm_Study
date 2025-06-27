import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] ans = new int [3];
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        StringTokenizer st;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(isAllSame(0, 0, n)){
            ans[paper[0][0]+1]++;
        }
        else{
            //분할정복
            //9분할 쪼개고 잘린 종이에 대해 검증
            search(0, 0, n);
        }

        System.out.println(ans[0]); // -1
        System.out.println(ans[1]); // 0
        System.out.println(ans[2]); // 1

    }

    static void search(int sx, int sy, int len) {
        if (isAllSame(sx, sy, len)) {
            ans[paper[sx][sy] + 1]++;
            return;
        }

        int newLen = len / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                search(sx + i * newLen, sy + j * newLen, newLen);
            }
        }
    }

    static boolean isAllSame(int sx, int sy, int len) {
        int val = paper[sx][sy];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (paper[sx + i][sy + j] != val) {
                    return false;
                }
            }
        }
        return true;
    }

}
