import java.io.*;
import java.util.*;


public class Main {
    static Queue<Integer> q = new LinkedList<>(); // 큐 생성
    static int[] map = new int[100001]; // 각 위치에 도달하는 데 걸린 시간을 저장할 배열(문제가 100,000까지이므로 크기를 100,001로 설정)
    static int n,m; // n 수빈이의 시작 위치, m 동생의 위치를 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken()); // 수빈이의 위치
        m = Integer.parseInt(tk.nextToken()); // 동생의 위치

        map[n] = 1; // 시작 위치 시간 1로 설정
        q.offer(n);
        while (!q.isEmpty()) {
            int nx;
            int t = q.poll(); // 현재 위치 t를 큐에서 꺼낸 후 다음 위치를 저장할 nx 선언

            if(t == m){  // 현재 위치가 동생의 위치와 같다면, 걸린 시간을 출력
                System.out.println(map[t]-1); // -1은 해야 함, 시작을 1로 설정해서
            }

            for(int i=0;i<3;i++){ // 세 가지 이동 방법(+1, -1, *2)에 대해 반복, 다음 위치 nx를 계산
                if(i==0){
                    nx = t+1;
                }else if(i==1){
                    nx = t-1;
                }else{
                    nx = t*2;
                }

                if(nx<0 || nx > 100000 || map[nx]!=0) continue; // 다음 위치가 범위를 벗어나거나 이미 방문한 곳이면 건너 뜀
                q.offer(nx);
                map[nx] = map[t]+1; // 유효한 다음 위치를 큐에 넣고, 해당 위치까지의 시간을 기록
            }
        }
    }

}