import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        // 문자열의 뒤에 A를 추가
        // 문자열을 뒤집고 뒤에 B를 추가


        // 문자열의 뒤에 A를 제거
        // 문자열을 뒤집고 뒤에 B를 제거
        // T -> S
        StringBuilder s = new StringBuilder(S);
        StringBuilder t = new StringBuilder(T);
        for (int i = t.length() - 1; i > -1; i--) {
            // S와 문자열의 길이가 같아질 때까지 반복
            if (i == S.length()-1) {
                break;
            }
            // 맨 뒤의 문자열이 B일 경우 B를 제거하고 문자열을 뒤집는다.
            if (t.charAt(i) == 'B') {
                t.deleteCharAt(i);
                t.reverse();
            }
            // 맨 뒤의 문자열이 A일 경우 제거
            else {
                t.deleteCharAt(i);
            }
        }

        if (s.compareTo(t) == 0) {
            System.out.println(1);
        } else System.out.println(0);

        br.close();
    }
}