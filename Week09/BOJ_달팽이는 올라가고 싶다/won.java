import java.io.*;
// 달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는가

public class Main {
    public static boolean chk_height(int cur, int target){
        return cur >= target;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // (1 ≤ B < A ≤ V ≤ 1,000,000,000)
        String[] str = br.readLine().split(" ");
        int A = Integer.parseInt(str[0]); // 낮(올라감)
        int B = Integer.parseInt(str[1]); // 밤(미끄러짐)
        int V = Integer.parseInt(str[2]); // 높이
        // 정상에 올라간 후 미끄러지지 않음

        int day = 0 ;
        if((V-B)%(A-B) == 0){
            day = (V-B)/(A-B);
        }else{
            day = (V-B)/(A-B) + 1;
        }

        bw.write(day + "\n");
        bw.flush();
    }
}