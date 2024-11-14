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

//      i에 해당하는 수가 1부터가 아니라 재귀를 거듭할수록 증가하게 되면 현재 정해진 숫자가 아닌 증가하는 방향이 된다.


        func(1, n, m, list, bw);




        bw.flush();
        bw.close();

    }

    public static void func(int num, int n, int m, List<Integer> list, BufferedWriter bw) throws IOException {

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

//      func(num+1 -> i+1)
//      i가 현재 선택한 숫자다. num+1을 하게되면 현재 i의 증가에 따른 중복없이 증가하는 값의 처리가 불가능하다.
        for(int i=num; i<=n; i++) {
            list.add(i);
            func(i+1, n, m, list, bw);
            list.remove(list.size()-1);
        }


    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
