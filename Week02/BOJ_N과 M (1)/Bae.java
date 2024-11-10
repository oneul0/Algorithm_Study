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

//      Bufferedwrite의 경우 선언된 메서드 내부에서만 저장되어 flush를 호출하여 출력이 된다. 각 메서드마다 따로 취급된다.
//      외부에서 생성하여 전달하자.
        func(n, m, visited, list, bw);

        bw.flush();
        bw.close();

    }

    public static void func(int n, int m, int[] visited, List<Integer> list, BufferedWriter bw) throws IOException {

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
            if(visited[i] == 0) {
                visited[i] = 1;
                list.add(i);
                func(n, m, visited, list, bw);
                list.remove(list.size()-1);
                visited[i] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
