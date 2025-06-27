import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] pic;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        pic = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                pic[i][j] = str.charAt(j) - '0';
            }
        }

        if(isAllSame(0,0,n)){
            sb.append(pic[0][0]);
        }
        else{
            compresstion(0, 0, n);
        }

        System.out.println(sb);
    }

    static void compresstion(int sx, int sy, int len) {
        if(len == 1 ||isAllSame(sx,sy,len)){
            sb.append(pic[sx][sy]);
            return;
        }

        int newLen = len/2;
        sb.append("(");
        for(int i = 0; i<2; i++){
            for(int j = 0; j<2; j++){
                compresstion(sx+(i*newLen),sy+(j*newLen),newLen);
            }
        }
        sb.append(")");
    }

    static boolean isAllSame(int sx, int sy, int len){
        int val = pic[sx][sy];
        for(int i = sx; i<sx+len; i++){
            for(int j = sy; j<sy+len; j++){
                if(val != pic[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}