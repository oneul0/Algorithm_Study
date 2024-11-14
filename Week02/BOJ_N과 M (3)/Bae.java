import java.io.*;
import java.util.*;

public class Main {


    public static void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


//      m 길이, n 개수
        List<Integer> list = new ArrayList<>();
        int[] visited = new int[n+1];



        func( n, m, list, bw);




        bw.flush();
        bw.close();

    }

    public static void func( int n, int m, List<Integer> list, BufferedWriter bw) throws IOException {

//      숫자 사이 공백, 줄 간격 바꾸는거 신경쓰자
        if (list.size() == m) {
            for (int i = 0; i < list.size(); i++) {
                bw.write(String.valueOf(list.get(i)));
                if (i < list.size() - 1) {
                    bw.write(" ");
                }
            }
            bw.newLine();
            return;
        }


        for(int i=1; i<=n; i++) {
            list.add(i);
            func( n, m, list, bw);
            list.remove(list.size()-1);
        }


    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
