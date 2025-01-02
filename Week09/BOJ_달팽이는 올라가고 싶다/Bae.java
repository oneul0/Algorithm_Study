import java.io.*;
import java.util.*;

public class Main {
    public static void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int v = Integer.parseInt(st.nextToken());


//일반 반복문은 시간초과
    double temp = v-b;
    double temp2 = a-b;
    double answer = Math.ceil(temp/temp2);
    int answer2 = (int)answer;


    bw.write(String.valueOf(answer2));
    bw.flush();
    bw.close();




    }


    public static void main(String[] args) throws IOException {
        solution();
    }

}
