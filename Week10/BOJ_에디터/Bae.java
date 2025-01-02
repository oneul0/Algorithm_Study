import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        * 영어 소문자만 기록 가능
        * 최대 600,000자
        * 커서의 위치 1. 맨 앞(첫 문자의 왼쪽) 2. 맨 뒤(마지막 문자의 오른쪽) 3. 문장 중간의 임의
        * -> 커서의 위치 문자열의 길이 L -> L+1가지 경우의 수
        *
        * 명령어
        * L -> 커서 왼쪽으로, 문장 맨 앞이면 무시
        * D -> 커서 오른쪽으로, 문장 맨 뒤면 무시
        * B -> 커서 왼쪽에 있는 문자 삭제(문장 맨 앞이면 무시)
        * P $ -> $문자를 커서 왼쪽에 추가
        *
        * 커서를 나타내야한다.
        *
        * */

        Stack<String> left = new Stack<>();
        Stack<String> right = new Stack<>();

        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            left.add(str.charAt(i) + "");
        }

        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            String s = br.readLine();
            if(s.equals("L")) {
                if(!left.isEmpty()){
                    String temp = left.pop();
                    right.add(temp);
                }
            } else if(s.equals("D")) {
                if(!right.isEmpty()){
                    String temp = right.pop();
                    left.add(temp);
                }
            } else if(s.equals("B")) {
                if(!left.isEmpty()){
                    left.pop();
                }
            } else {
                String temp = s.substring(2);
                left.add(temp);
            }
        }

        while(!left.isEmpty()) {
            String temp = left.pop();
            right.add(temp);
        }

        int r_size = right.size();

        for(int i=0;i<r_size;i++){
            bw.write(right.pop());
        }

        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }

}
