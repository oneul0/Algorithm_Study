package 숨바꼭질;
// 30분
/*
* 문제
수빈이는 동생과 숨바꼭질을 하고 있다.
* 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
* 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
* 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
= > 수빈: X+1, X-1, 2*X 만큼 이동 가능하다.
* 결과: 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지

입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
* 5 17

출력
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
4
*
수빈이가 5-10-9-18-17 순으로 가면 4초만에 동생을 찾을 수 있다.
*
BFS
* */
import java.util.*;
import java.io.*;
public class Main {
    static int nextnum(int type, int num){
        if(type == 0){
            return num+1;
        } else if(type == 1){
            return num-1;
        } else if(type == 2){
            return 2*num;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int answer =0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.add(N);
        visited[N] = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int s = 0; s < size; s++){
                int cur = queue.poll();
                if(cur == K){
                    bw.write(answer+"\n");
                    bw.flush();
                    bw.close();
                    return;
                }

                for(int i=0; i<3; i++){
                    int nextN = nextnum(i, cur);
                    if( nextN>=0 && nextN <= 100000 && !visited[nextN] ){
                        queue.add(nextN);
                        visited[nextN] = true;
                    }

                }
            }

            answer++;
        }

        bw.write("답을 찾지 못했음\n");
        bw.flush();
        bw.close();
    }
}
