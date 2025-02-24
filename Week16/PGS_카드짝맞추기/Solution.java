import java.util.*;

class Solution {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우
    static int minMoves = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {
        List<Integer> cardTypes = new ArrayList<>();
        Map<Integer, List<int[]>> cardPositions = new HashMap<>();

        // 카드 위치 기록
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    int num = board[i][j];
                    if (!cardPositions.containsKey(num)) {
                        cardPositions.put(num, new ArrayList<>());
                        cardTypes.add(num);
                    }
                    cardPositions.get(num).add(new int[]{i, j});
                }
            }
        }

        // 카드 제거 순서에 대한 순열 생성
        permute(cardTypes, 0, board, r, c, cardPositions);

        return minMoves;
    }

    // 순열을 생성하여 모든 카드 제거 순서를 탐색
    private void permute(List<Integer> cardTypes, int idx, int[][] board, int r, int c, Map<Integer, List<int[]>> cardPositions) {
        if (idx == cardTypes.size()) {
            int[][] tempBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                tempBoard[i] = board[i].clone();
            }
            minMoves = Math.min(minMoves, removeCards(tempBoard, new ArrayList<>(cardTypes), r, c, cardPositions));
            return;
        }

        for (int i = idx; i < cardTypes.size(); i++) {
            Collections.swap(cardTypes, i, idx);
            permute(cardTypes, idx + 1, board, r, c, cardPositions);
            Collections.swap(cardTypes, i, idx);
        }
    }

    // BFS로 최단 거리 찾기
    private int bfs(int[][] board, int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) return 0;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        queue.add(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], cost = cur[2];

            for (int[] d : dirs) { // 일반 이동 (한 칸)
                int nr = r + d[0], nc = c + d[1];
                if (isValid(nr, nc) && !visited[nr][nc]) {
                    if (nr == er && nc == ec) return cost + 1;
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, cost + 1});
                }
            }

            for (int[] d : dirs) { // Ctrl 이동
                int nr = r, nc = c;
                while (isValid(nr + d[0], nc + d[1])) {
                    nr += d[0];
                    nc += d[1];
                    if (board[nr][nc] > 0) break; // 카드가 있으면 멈춤
                }
                if (!visited[nr][nc]) {
                    if (nr == er && nc == ec) return cost + 1;
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, cost + 1});
                }
            }
        }

        return Integer.MAX_VALUE; // 이동 불가한 경우
    }

    // 모든 카드를 제거하는 최소 이동 횟수 계산
    private int removeCards(int[][] board, List<Integer> cardOrder, int r, int c, Map<Integer, List<int[]>> cardPositions) {
        int totalMoves = 0;

        for (int card : cardOrder) {
            List<int[]> pos = cardPositions.get(card);
            int[] first = pos.get(0), second = pos.get(1);

            // 첫 번째 카드부터 제거할 경우
            int move1 = bfs(board, r, c, first[0], first[1]) + 1;
            int move2 = bfs(board, first[0], first[1], second[0], second[1]) + 1;

            // 두 번째 카드부터 제거할 경우
            int move3 = bfs(board, r, c, second[0], second[1]) + 1;
            int move4 = bfs(board, second[0], second[1], first[0], first[1]) + 1;

            if (move1 + move2 < move3 + move4) {
                r = second[0];
                c = second[1];
                totalMoves += move1 + move2;
            } else {
                r = first[0];
                c = first[1];
                totalMoves += move3 + move4;
            }

            // 카드 제거
            board[first[0]][first[1]] = 0;
            board[second[0]][second[1]] = 0;
        }

        return totalMoves;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }
}