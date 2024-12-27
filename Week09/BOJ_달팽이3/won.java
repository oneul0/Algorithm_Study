import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long M = Long.parseLong(st.nextToken());
        long N = Long.parseLong(st.nextToken());

        if(M>N){
            // 선이 꺾어지는 횟수
            System.out.println((N-1)*2 +1);
            // 끝점
            if(N % 2 != 0){
                //홀수

                System.out.println((N/2+ 1 +(M-N)) +" "+ (N/2+1));
            }else{
                System.out.println((N/2+ 1)  +" "+ (N/2));
            }
        }else if(M==N){
            // 선이 꺾어지는 횟수

            System.out.println((M-1)*2);

            // 끝점
            if(M % 2 != 0){
                //홀수
                System.out.println(((M+1)/2)+" "+ ((M+1)/2));
            }else{
                System.out.println((M/2 + 1)  +" "+ (M/2));
            }
        }
        else if(M < N){
            // 선이 꺾어지는 횟수
            System.out.println((M-1)*2);

            // 끝점
            if(M % 2 != 0){
                //홀수
                System.out.println((M/2+1)+" "+ ( M/2+ 1 + (N-M)));
            }else{
                System.out.println((M/2+1)  +" "+ (M/2));
            }
        }
    }
}
