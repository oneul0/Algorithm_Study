import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String s = br.readLine();
        String t = br.readLine();


        dfs(t,s);

        bw.write(answer+"");

        bw.close();
    }

    public static void dfs(String t,String s) {
        int s_length = s.length();
        int t_length = t.length();


        
        if (t_length == s_length) {
            if (t.equals(s)) {
                answer = 1;
                return;
            }
        }
        
        if (t.endsWith("A")) {
            dfs(t.substring(0, t_length - 1),s);
        }
        
        if (t.startsWith("B")) {
            dfs(new StringBuilder(t.substring(1)).reverse().toString(),s);
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
    }



}
