import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static char[][] stars;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        br.close();
        stars = new char[N][2*N-1];
        for(int i = 0; i<N; i++){
            Arrays.fill(stars[i], ' ');
        }
        draw(0,N-1,N);

        for(int i = 0; i<N; i++){
            for(int j = 0; j<stars[i].length; j++){
                bw.write(stars[i][j]+"");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void draw(int sx, int sy, int n){
        if(n ==3){
            stars[sx][sy]='*';
            stars[sx+1][sy-1]='*';
            stars[sx+1][sy+1]='*';
            for(int i = -2; i<=2; i++){
                stars[sx+2][sy+i] = '*';
            }
            return;
        }

        int half = n/2;
        draw(sx, sy, half);
        draw(sx+half, sy-half, half);
        draw(sx+half, sy+half, half);
    }
}

//높이가 주어질 때 삼각형 그리기
