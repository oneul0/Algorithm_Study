import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken()); // 일(급여 배열의 크기)
        int M = Integer.parseInt(st.nextToken());  // 일 할 수 있는 일수(윈도우 사이즈) , 연속으로 일할 수 있는 날의 수
        long[] arr = new long[N+1]; // 배열 선언 시 int가 아닌 long으로 해야 함, 누적 일급 합계 배열
        long max = 0; // 최대 수익 , 0일 근무도 가능하다는 점 고려
        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=N;i++) { // 각 날짜까지의 누적 일급을 계산 , 나중에 구간 합을 쉽게 계산하기 위함
            arr[i] = arr[i-1]+Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N-M+1;i++) { // 슬라이딩 윈도우 사용, 연속 M일 동안의 최대 수익 , i일부터 M일 동안의 수익 합계
            max = Math.max(max, arr[i+M]-arr[i]);
        }
        System.out.println(max);
    }
}