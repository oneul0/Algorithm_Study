import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String s, t;
    public static void main(String[] args) throws Exception{
        s = br.readLine();
        t = br.readLine();

        bw.write(canTrans());
        bw.flush();
        br.close();
        bw.close();
    }

    //t를 s로 만들 수 있을지
    static String canTrans() {
        StringBuilder str = new StringBuilder(t);

        while(str.length() > s.length()){
            if(str.charAt(str.length() -1) == 'A') str.deleteCharAt(str.length()-1);
            else{
                str.deleteCharAt(str.length()-1);
                str.reverse();
            }
        }

        return str.toString().equals(s) ? "1" : "0";
    }


}
