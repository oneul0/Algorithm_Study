import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int ans = 0, N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        re(0, new int[N]);
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }

    static void re(int col, int[] row) {
        if(col == N){
            ans++;
            return;
        }
        //row
        for(int i = 0; i<N; i++){
            boolean is_promise = true;
            //col
            for(int j = 0; j<col; j++){
                //이전에 놓았던 자리이거나 기울기가 같으면(대각선에 위치해있으면)
                if(row[j] == i || Math.abs(row[j] - i) == col-j){
                    is_promise = false;
                    break;
                }
            }
            if(is_promise){
                row[col] = i;
                re(col+1, row);
            }
        }
    }
}
