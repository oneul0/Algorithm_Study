import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String S, T;
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        S = br.readLine();
        T = br.readLine();
        canTrans(new StringBuilder(T));
        if(flag) bw.write("1");
        else bw.write("0");
        bw.flush();
        br.close();
        bw.close();
    }

    static void canTrans(StringBuilder sb) throws Exception{
        if(sb.length() == S.length()){
            if(sb.toString().equals(S)){
                flag = true;
                return;
            }
            return;
        }
        if(sb.charAt(sb.length()-1) == 'A'){
            sb.deleteCharAt(sb.length()-1);
            canTrans(sb);
            sb.append('A');
        }
        if(sb.charAt(0) == 'B'){
            sb.reverse();
            sb.deleteCharAt(sb.length()-1);
            canTrans(sb);
            sb.append('B');
            sb.reverse();
        }
    }
}
