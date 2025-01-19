package A와B_2;
// VERSION 1 과의 차이점
// VERSION 1: 문자열을 뒤집고 B를 추가시킴
// VERSION 2: B를 추가하고 문자열 뒤집음.
// => 확인할 때 문자열의 앞에 B가 있으면 그때 VERSION2 적용시키기


import java.util.*;
import java.io.*;
//첫째 줄에 S가 둘째 줄에 T가 주어진다.
//(1 ≤ S의 길이 ≤ 49, 2 ≤ T의 길이 ≤ 50, S의 길이 < T의 길이)
public class Main {
    static String S;
    static String T;

    public static void dfs(String t) {
        if (t.equals(S)) {
            System.out.println(1);
            System.exit(0);
        }
        if (t.length() == 0) {
            return;
        }
        if (t.charAt(t.length() - 1) == 'A') {
            dfs(t.substring(0, t.length() - 1)); // 마지막 문자 제거
        }
        if (t.charAt(0) == 'B') {
            // 처음 문자를 제거하고, 나머지 문자열을 뒤집어서 재귀 호출
            dfs(new StringBuilder(t.substring(1)).reverse().toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine(); // 시작 문자열
        T = br.readLine(); // 목표 문자열

        // dfs로 탐색
        dfs(T);
        System.out.println(0);
    }
}
