import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken()); // 낮에 올라갈 수 있는 거리
        long B = Long.parseLong(st.nextToken()); // 밤에 미끄러지는 거리
        long V = Long.parseLong(st.nextToken()); // 총 나무 막대 거리

        long destination = V - B; //하루에 움직이는 거리
        long dist = A - B; // 하루에 움직이는 거리

        long div = destination / dist;
        long mod = destination % dist;

        long day = div;
        if(mod != 0) { // 나눠떨어지지 않으므로 하루를 더 추가로 가야함
            day = day + 1;
            System.out.println(day);
        }
        else { // 나눠 떨어지므로 낮에 도착
            System.out.println(day);
        }

    }
}