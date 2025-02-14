
import java.util.*;

class Car {
    int x; // x좌표
    int y; // y좌표
    int dir; // 현재 방향
    int cost; // 현재까지의 비용

    Car(int a, int b, int c, int d) {
        x = a;
        y = b;
        dir = c;
        cost = d;
    }
}

class Solution {
    int[][] visit;
    int len;

    public int solution(int[][] board) {
        len = board.length;
        visit = new int[len][len];

        return search(board);
    }

    // 상, 하, 좌, 우 방향
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int search(int[][] board) {
        Queue<Car> q = new ArrayDeque<>();
        q.add(new Car(0, 0, 1, 100)); // 아래쪽을 바라보는 차량
        q.add(new Car(0, 0, 3, 100)); // 오른쪽을 바라보는 차량
        visit[0][0] = 100;

        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Car cur = q.poll();

            // 목적지에 도달했으면 최소 비용을 갱신
            if (cur.x == len - 1 && cur.y == len - 1) {
                answer = Math.min(answer, cur.cost);
                continue;
            }

            // 4가지 방향으로 탐색
            for (int d = 0; d < 4; ++d) {
                int nextX = cur.x + dx[d];
                int nextY = cur.y + dy[d];

                // 범위 내부이면서, 벽이 아닌경우 도로를 건설할 수 있다.
                if (isInRange(nextX, nextY) && board[nextX][nextY] == 0) {
                    // 방향의 변화 여부에 따라 건설비용이 달라진다.
                    int weight = cur.dir == d ? 100 : 600;

                    // 한번도 가지 않은 경우
                    if (visit[nextX][nextY] == 0) {
                        visit[nextX][nextY] = cur.cost + weight;
                        q.add(new Car(nextX, nextY, d, cur.cost + weight));
                    }
                    // 간 적이 있지만, 비용적으로 이득이 발생하는 부분
                    else if (cur.cost + weight < visit[nextX][nextY] + 500) {
                        visit[nextX][nextY] = cur.cost + weight;
                        q.add(new Car(nextX, nextY, d, cur.cost + weight));
                    }
                    else {
                        // nothing
                    }
                }
            }
        }
        return answer - 100; // 시작 비용 100을 빼줍니다.
    }

    // 범위 내에 있는지 확인
    public boolean isInRange(int x, int y) {
        return 0 <= x && x < len && 0 <= y && y < len;
    }
}