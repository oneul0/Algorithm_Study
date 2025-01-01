import java.io.*;
import java.util.*;

public class Main {

    static long y = 0;
    static long x = 0;

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * 마지막에 대한 처리를 어떻게 해야할까
         * n,m 값의 범위때문에 2차원배열 사용불가
         * 미리 계산해보자
         * n*n 에서 꺾이는 부분의 개수는 y+1까지는 +1개 증가 이후 y증가에 따라 변화 x
         * (n,n-1) -> (n,n) 일때 꺾임 개수 +1, 이후 x 증가에 따른 변화 x
         * 2*2가 꺾임 2회 -> 3*2 3회 3*3 4회  4*4 6회 -> (n-1)*2
         * 종료 좌표
         * 짝수 / 홀수
         * 짝수 (index 기준)
         * 1*1 -> (0,0)
         * 2*2 -> (1,0) 에서 x,y 증가에 상관없이 고정
         * 3*3 -> (1,1) 에서 y증가에 따라 (1,1+y), x증가에 따라 (1+x,1)
         * 4*4 -> (1,2)
         * 5*5 -> (2,2)
         * 6*6 -> (3,2)
         * 7*7 -> (3,3)
         * 8*8 -> (4,3)
         * y,x,x,y 순으로
         */
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        if(y > x) {
            bw.write(String.valueOf(((x-1)*2)+1));
        } else {
            bw.write(String.valueOf((y-1)*2));
        }
        bw.newLine();


        // 종료 좌표 계산
        if(y == x) {
            if(y % 2 == 1) {
                bw.write((y/2+1) + " " + (x/2+1));
            } else {
                bw.write((y/2+1) + " " + (x/2));
            }
        } else if(y > x) {
            if(x % 2 == 0) {
                bw.write((x/2+1) + " " + (x/2));
            } else {
                bw.write((x/2+1+(y-x)) + " " + (x/2+1));
            }
        } else {
            if(y % 2 == 0) {
                bw.write((y/2+1) + " " + (y/2));
            } else {
                bw.write((y/2+1) + " " + (y/2+1+(x-y)));
            }
        }

        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }

}
