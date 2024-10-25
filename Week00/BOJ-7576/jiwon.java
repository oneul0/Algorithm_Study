import java.util.*;

public class Main {
    static int N,M; // N: row, M: col
    static int[][] arr;
    static boolean[][] visited;

    public static int [][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static boolean isValid(int row, int col) {
        // 경계 안에 있으며 안익은 토마토 일때만 앞으로 갈 수 있다.
        return row >= 1 && row <= N && col >= 1 &&  col <= M  && arr[row][col] == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        M = scanner.nextInt();
        N = scanner.nextInt();

        // index를 1부터 시작하기 위해 하나씩 크게 배열의 크기를 잡음
        arr = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        System.out.println(tomato());
    }

    private static int tomato() {
        // bfs를 적용
        int ans= -1;

        Queue<int[]> q = new LinkedList<>();


        // 시작점 넣기 (배열을 돌면서 1인 것은 모두 시작점이 됨)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(arr[i][j] == 1) {
                    q.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                for(int[] direction : directions) {
                    int newRow = curRow + direction[0];
                    int newCol = curCol + direction[1];

                    if(isValid(newRow, newCol) && !visited[newRow][newCol]) {
                        q.offer(new int[]{newRow, newCol});
                        arr[newRow][newCol] = arr[curRow][curCol] + 1;
                        visited[newRow][newCol] = true; // 방문 표시
                    }
                }
            }
            ans++; // 날짜 세기
        }


        // 안익은 토마토 여부 검사
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(arr[i][j]==0) return -1;
            }
        }
        return ans;
    }

}
