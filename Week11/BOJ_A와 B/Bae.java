import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        * s -> t로
        * 두 가지 연산만 가능
        * 1. 문자열 뒤에 a추가
        * 2. 뒤집고 뒤에 b추가
        *
        * 패턴
        * AB -> A 2 , AA -> A 1, BA -> B 1, BB -> B 2
        * AAA -> A 1 1, AAB -> A 1 2, ABA -> A 2 1, BAA -> B 1 1, ABB -> B 1 2, BAB -> A 2 2, BBA -> B 2 1, BBB -> B 2 2
        * AAAA -> A 1 1 1, AAAB -> A 1 1 2, AABA -> A 1 2 1, ABAA -> 2 1 1, BAAA -> B 1 1 1, AABB -> B 1 1 2, ABAB -> A 2 1 2
        * BAAB -> A 1 2 2, ABBA -> B 1 2 1, BABA -> A 2 2 1, BBAA -> B 2 1 1, ABBB ->B 2 1 2, BABB -> A 2 2 2, BBAB -> B 1 2 2
        * BBBA -> B 2 2 1, BBBB -> B 2 2 2
        *
        * 2^(t.length-s.length) 만큼
        *
        * */

        String s = br.readLine();
        String t = br.readLine();

        int answer = makeString(t, s);

        bw.write(answer + "");
        
        bw.flush();
        bw.close();


    }

    public static int makeString(String t, String s) {
        while (t.length() > s.length()) {
            if (t.charAt(t.length()-1) == 'A') {
                t = t.substring(0, t.length()-1);
            } else if (t.charAt(t.length()-1) == 'B') {
                t = t.substring(0, t.length()-1);
                t = new StringBuilder(t).reverse().toString();
            }
        }

        return s.equals(t) ? 1 : 0;
    }
//    순방향은 너무 오래 걸린다 -> 역방향
//    public static String one(String s) {
//        return s+"A";
//    }
//
//    public static String two(String s) {
//        StringBuilder sb = new StringBuilder();
//
//        for(int i=s.length()-1; i >=0; i--) {
//                sb.append(s.charAt(i));
//        }
//        return sb.toString() + "B";
//    }

    public static boolean check(String temp, String t) {
        return temp.equals(t);
    }

    public static void main(String[] args) throws IOException {
        solution();
    }



}
