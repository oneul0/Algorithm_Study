import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] acgt, chk;
    static int S, P, len, ans = 0;
    static String str;
    public static void main(String[] args) throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        str = br.readLine();
        st = new StringTokenizer(br.readLine());
        acgt = new int[4];
        chk = new int[4];
        for(int i = 0; i<4; i++){
            acgt[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<P; i++){
            add(i);
        }
        if(isPossible()) ans++;
        len = S-P+1;
        //시작인덱스
        for(int i = 1; i<len; i++){
            add(i+P-1); //끝 인덱스
            remove(i-1);
            if(isPossible()) ans++;
        }

        bw.write(ans+"");
        bw.flush();
        br.close();
        bw.close();
    }

    static boolean isPossible(){
        if(acgt[0] > chk[0]) return false;
        if(acgt[1] > chk[1]) return false;
        if(acgt[2] > chk[2]) return false;
        if(acgt[3] > chk[3]) return false;
        return true;
    }

    static void add(int idx){
        if(str.charAt(idx) == 'A') chk[0]++;
        else if(str.charAt(idx) == 'C') chk[1]++;
        else if(str.charAt(idx) == 'G') chk[2]++;
        else if(str.charAt(idx) == 'T') chk[3]++;
    }
    static void remove(int idx){
        if(str.charAt(idx) == 'A') chk[0]--;
        else if(str.charAt(idx) == 'C') chk[1]--;
        else if(str.charAt(idx) == 'G') chk[2]--;
        else if(str.charAt(idx) == 'T') chk[3]--;
    }
}