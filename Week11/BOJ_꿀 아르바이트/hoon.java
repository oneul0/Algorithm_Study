import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String s;
    static int M;
    static String[] in;
    static Deque<Character> left, right;
    public static void main(String[] args) throws Exception {
        s = br.readLine();
        M = Integer.parseInt(br.readLine());
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        for(int i = 0; i<s.length(); i++){
            left.push(s.charAt(i));
        }

        for(int i = 0; i<M; i++){
            in = br.readLine().split(" ");
            edit(in[0]);
        }

        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty()) {
            sb.append(left.removeLast());
        }
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static void edit(String comm) {
        //커서를 왼쪽으로 이동하는 것이므로 요소도 왼쪽 요소가 오른쪽 스택으로 이동하게 됨
        if(comm.equals("L")){
            //L
            if(!left.isEmpty()) right.push(left.pop());
        }
        //커서를 오른쪽으로 이동하는 것이므로 요소도 오른쪽 요소가 왼쪽 스택으로 이동하게 됨
        else if(comm.equals("D")){
            //D
            if(!right.isEmpty()) left.push(right.pop());
        }
        //커서의 왼쪽 문자 삭제(왼쪽 요소를 삭제)
        else if(comm.equals("B")){
            //B
            if(!left.isEmpty()) left.pop();
        }
        //왼쪽에 요소를 삽입
        else if(comm.equals("P")){ //
            //P
            left.push(in[1].charAt(0));
        }
    }
}
