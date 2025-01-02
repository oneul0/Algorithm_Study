import java.util.*;
import java.io.*;

public class Main {
    static int N, M; // N*M 미로
    static int map[][]; // 미로를 담아줄 배열
    static boolean visited[][]; // 방문 여부 확인
    static int[] nearX = {-1, 0, 1, 0}; // x 방향 상하좌우
    static int[] nearY = {0, 1, 0, -1}; // y방향 상하좌우
    private static class Node {  //각 칸의 x,y
        int x, y;
        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //행
        M = Integer.parseInt(st.nextToken());  //열
        visited = new boolean[N + 1][M + 1];
        map = new int[N + 1][M + 1];

        //입력받아서 map에 저장
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                // map[i][j] = Character.getNumericValue(s.charAt(j - 1)); //char to integer
                map[i][j] = s.charAt(j-1) - '0'; //char to integer, 아스키코드사용
            }
        }
        //너비우선탐색
        bfs();
        System.out.println(map[N][M]);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1));
        visited[1][1] = true;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();  //현재 노드
            for (int i = 0; i < 4; i++) {  //현재 노드에서 갈 수 있는 방향 4가지
                int curNearY = curNode.y + nearY[i];
                int curNearX = curNode.x+ nearX[i];
                //조건문성립: 존재하는칸, 값이 0아니며, 아직방문안함
                if (curNearX > 0 && curNearY > 0 && curNearY <= N && curNearX <= M && map[curNearY][curNearX] != 0 && visited[curNearY][curNearX] == false) {
                    queue.offer(new Node(curNearY, curNearX));
                    map[curNearY][curNearX] = map[curNode.y][curNode.x]+1;
                    visited[curNearY][curNearX] = true;
                }
            }
        }
    }
}