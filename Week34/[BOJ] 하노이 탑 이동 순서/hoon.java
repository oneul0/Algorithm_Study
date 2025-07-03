import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        br.close();
        int cnt = (int)Math.pow(2, n)-1;
        bw.write(cnt+"\n");
        simulation(n, 1, 2, 3);
        bw.flush();
        bw.close();
    }

    public static void simulation(int cnt, int st1, int st2, int st3) throws IOException {
        if (cnt == 1) {
            bw.write(st1 + " " + st3+"\n");
            return;
        }
        simulation(cnt - 1, st1, st3, st2);
        bw.write(st1 + " " + st3+"\n");
        simulation(cnt - 1, st2, st1, st3);
    }
}