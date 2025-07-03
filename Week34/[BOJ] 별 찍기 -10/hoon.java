import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static char[][] stars;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        stars = new char[N][N];
        draw(0,0,N, false);
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                bw.write(stars[i][j]+"");
            }
            bw.write("\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void draw(int sx, int sy, int n, boolean blank){
        if(blank){
            for(int i = sx; i<sx+n; i++){
                for(int j = sy; j<sy+n; j++){
                    stars[i][j] = ' ';
                }
            }
            return;
        }

        if(n == 1){
            stars[sx][sy] = '*';
            return;
        }
        int count = 0;
        int newLen = n/3;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                count++;
                if(count == 5){
                    draw(sx+i*newLen, sy+j*newLen, newLen, true);
                }
                else{
                    draw(sx+i*newLen, sy+j*newLen, newLen, false);
                }
            }
        }
    }
}
