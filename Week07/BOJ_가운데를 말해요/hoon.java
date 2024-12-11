import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(br.readLine()), num;
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder()); //최대힙
        PriorityQueue<Integer> right = new PriorityQueue<>(); //최소힙
        for(int i = 0; i<N; i++){
            num = Integer.parseInt(br.readLine());

            //두 큐가 비었거나 현재 숫자가 중간에 위치하는 수인 경우
            if(left.size() == right.size()) left.offer(num);
            else right.offer(num);

            //최대힙의 top이 최소힙의 top보다 큰 경우
            //재균형 연산
            if(!left.isEmpty() && !right.isEmpty() &&(left.peek() > right.peek())){
                int num1 = left.remove();
                int num2 = right.remove();
                left.offer(num2);
                right.offer(num1);
            }

            bw.write(left.peek()+"\n");
        }
        bw.flush();

    }
}

