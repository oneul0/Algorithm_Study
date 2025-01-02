import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.valueOf(st.nextToken());
        int k=Integer.valueOf(st.nextToken());

        //위상정렬 가중치 저장
        int[] num=new int[n+1];
        //포함관계 저장
        ArrayList<Integer>[] arr=new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            arr[i]=new ArrayList<>();
        }

        //관계 입력받기
        for(int i=0; i<k; i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.valueOf(st.nextToken());
            int b=Integer.valueOf(st.nextToken());

            num[a]++;
            arr[b].add(a);
        }

        //전 후 관계 저장 배열
        boolean[][] value=new boolean[n+1][n+1];

        Queue<Integer> que=new LinkedList<>();
        //위상정렬 그래프 돌면서, value관계 저장.
        for(int i=1; i<=n; i++){
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
                //선행되는 역사들의 가중치를 낮품
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

