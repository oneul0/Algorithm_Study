
// 30 min
/*
 * 크기 : N * M
 * 1: 이동가능함
 * 0: 이동불가능
 * (1,1) -> (N,M) 이동할 때 지나야 하는 최소의 칸 수
 * 한칸에서 다른 칸 이동할 때 서로 인접한 칸으로만 이동가능
 *
 * 최단거리는 BFS임~!
 * */
import java.util.*;
import java.io.*;
public class Main {
    static int[][] arr;
    static int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    static boolean[][] visited;
    static int n;
    static int m;

    public static boolean isValid(int x, int y) {
        if(x >=0 && x<n && y >= 0 && y< m &&arr[x][y] ==1 ) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N, M 입력 받기
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        arr = new int[n][m];
        visited = new boolean[n][m];

        // 그다음 이차원 배열 입력
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            int[] temp = new int[m];
            for (int j = 0; j < m; j++) {
                temp[j] = Integer.parseInt(str[j]);
            }
            arr[i] = temp;
        }

        int answer = 1;
        // BFS 순회
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new int[]{0,0});

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                // 도착점에 도달한 경우
                if( curRow == n-1 && curCol == m-1 ) {
                    bw.write(answer + "\n"); bw.flush();  // 버퍼 비우기
                    return;
                }

                for(int[] d : directions) {
                    int nextRow = curRow + d[0];
                    int nextCol = curCol + d[1];

                    if(isValid(nextRow, nextCol) && !visited[nextRow][nextCol]  ) {
                        visited[nextRow][nextCol] = true;
                        q.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
            answer++;
        }
        bw.flush();
        bw.close();
    }
}
