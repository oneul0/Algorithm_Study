import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine()); // 입력 문자열을 분리하기 위한 StringTokenizer 생성

        int n=Integer.valueOf(st.nextToken()); // 첫 번째 줄에서 사건의 개수 n과 사건 간의 전후 관계의 개수 k를 입력받음
        int k=Integer.valueOf(st.nextToken());

        //위상정렬 가중치 저장 , 각 사건의 진입 차수(선행 사건의 수)를 저장하는 배열
        int[] num=new int[n+1];
        //포함관계 저장 , 각 사건의 후행 사건들을 저장하는 ArrayList 배열
        ArrayList<Integer>[] arr=new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            arr[i]=new ArrayList<>();
        }

        //관계 입력받기 , k개의 전후 관계 입력 받기
        for(int i=0; i<k; i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.valueOf(st.nextToken());
            int b=Integer.valueOf(st.nextToken());

            num[a]++; // b의 선행 사건 수 증가
            arr[b].add(a); // a의 후행 사건으로 b 추가
        }

        //전 후 관계 저장 2차원 배열
        boolean[][] value=new boolean[n+1][n+1];
        // 위상 정렬을 위한 큐 생성
        Queue<Integer> que=new LinkedList<>();
        //위상정렬 그래프 돌면서, value관계 저장.
        for(int i=1; i<=n; i++){ // 진입 차수가 0인 사건(시작점)을 큐에 추가
            if(num[i]==0){
                que.offer(i);
            }
        }
        //앞서는 수의 행, 뒤의 수의 열에 true로 전후 관계 저장
        //dp로 전의 수가 포함하는 수를 모두 true로 바꾸어줌
        while(!que.isEmpty()){
            int temp=que.poll();

            for(int i=0; i<arr[temp].size(); i++){
                int t=arr[temp].get(i);
                //temp 방문으로 이를 이후 사건으로 갖는
                //선행되는 역사들의 가중치를 낮춤
                num[t]--;
                if(num[t]==0){
                    que.offer(t);
                }
                //temp의 후에 일어나는 사건들을 t에 그대로 옮김
                //temp의 후에 일어나는 사건은 t의 이후에 일어나는 사건
                for(int j=1; j<=n; j++){
                    if(value[temp][j]){
                        value[t][j]=true;
                    }
                }
                //t에 temp사건 추가하기 (temp 배열에 체크가 안되어있기 때문에 해주어야 함)
                value[t][temp]=true;
            }
        }
        // 질문의 개수 s 입력 받기
        int s=Integer.valueOf(br.readLine());
        for(int i=0; i<s; i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.valueOf(st.nextToken());
            int b=Integer.valueOf(st.nextToken());

            //a가 앞서는 경우
            if(value[a][b]){
                bw.write("-1\n");
            }

            //b가 앞서는 경우
            else if(value[b][a]){
                bw.write("1\n");
            }
            //모르는 경우
            else{
                bw.write("0\n");
            }
        }

        bw.flush();

    }
}

