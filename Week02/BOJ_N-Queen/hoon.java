import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int ans = 0, N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        bt(0, new int[N]);
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }

    static void bt(int col, int[] row){
        if(col == N){
            ans++;
            return;
        }

        //row 탐색 loop
        for(int i = 0; i<N; i++){
            boolean is_safe = true;
            //이전까지의 row 탐색 loop
            for(int j = 0; j <col; j++){
                //이전에 놓았던 row와 같거나 || 기울기가 같은 이전의 놓았던 인덱스가 있으면 pass
                if(row[j] == i || Math.abs(row[j]-i) == col- j) {
                    is_safe = false;
                    break;
                }
            }
            if(is_safe){
                row[col] = i;
                bt(col+1, row);
            }
        }

    }
}
