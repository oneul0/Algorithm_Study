import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int count = 0;
        int[] visited = new int[100001];

        Deque<Integer> q = new ArrayDeque<>();
        q.add(n);
        visited[n] = 1;

        while(!q.isEmpty()) {
            int temp = q.poll();

            if(visited[k] != 0) {
                break;
            }

            if(isValid(temp-1) &&visited[temp-1] == 0) {
                q.add(temp-1);
                visited[temp-1] = visited[temp]+1;
            }
            if(isValid(temp+1) && visited[temp+1] == 0) {
                q.add(temp+1);
                visited[temp+1] = visited[temp]+1;
            }
            if(isValid(temp*2) && visited[temp*2] == 0) {
                q.add(temp*2);
                visited[temp*2] = visited[temp]+1;
            }


        }

        bw.write(visited[k]-1+"");
        bw.close();
    }

    public static boolean isValid(int n) {
        return n >= 0 && n <= 100000;
    }
    public static void main(String[] args) throws IOException {
        solution();
    }



}
