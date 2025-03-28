/*
n행 m열의 격자
왼쪽: 열 번호가 감소하는 방향으로 dx칸 이동 query(0,dx)
오른쪽: 열 번호가 증가하는 방향으로 dx칸 이동하는 쿼리 (query(1, dx))
위: 행 번호가 감소하는 방향으로 dx칸 이동하는 쿼리 (query(2, dx))
아래: 행 번호가 증가하는 방향으로 dx칸 이동하는 쿼리 (query(3, dx))
 */

//x행 y열에 도착하는 시작점의 개수를 return
// X, Y: 도착 지점
import java.util.*;
class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long left = y;
        long right = y;
        long top = x;
        long bottom = x;

        for( int i= queries.length-1; i>=0; i-- ) {
            int direction = queries[i][0];
            int dist = queries[i][1];

            if (direction == 0) { // LEFT → 시작점은 오른쪽에 있어야 도달 가능
                if (left != 0)
                    left += dist;
                right = Math.min(m - 1, right + dist);
            } else if (direction == 1) { // RIGHT → 시작점은 왼쪽에 있어야 도달 가능
                if (right != m - 1)
                    right -= dist;
                left = Math.max(0, left - dist);
            } else if (direction == 2) { // UP → 시작점은 아래쪽에 있어야 도달 가능
                if (top != 0)
                    top += dist;
                bottom = Math.min(n - 1, bottom + dist);
            } else if (direction == 3) { // DOWN → 시작점은 위쪽에 있어야 도달 가능
                if (bottom != n - 1)
                    bottom -= dist;
                top = Math.max(0, top - dist);
            }

            // 범위를 벗어나면 종료
            if (left >= m || right < 0 || top >= n || bottom < 0) {
                return 0;
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] queries1 = {{2,1},{0,1},{1,1},{0,1},{2,1}};
        System.out.println(sol.solution(2, 2, 0, 0, queries1)); // 결과: 4

        int[][] queries2 = {{3,1},{2,2},{1,1},{2,3},{0,1},{2,1}};
        System.out.println(sol.solution(2, 5, 0, 1, queries2)); // 결과: 2
    }
}