import java.util.*;

class Solution {

  int ans = Integer.MAX_VALUE;
  boolean[] chkOrders;
  List<String> orders = new ArrayList<>();
  int[] cards;
  int[] dr = {0, 0, 1, -1};
  int[] dc = {1, -1, 0, 0};

  public int solution(int[][] board, int r, int c) {
    int cnt = 0;
    for (int[] b : board) {
      for (int i = 0; i < 4; i++) {
        if (b[i] > 0) {
          cnt++;
        }
      }
    }

    cnt /= 2;

    int[] card = new int[cnt];
    for (int i = 0; i < cnt; i++) {
      card[i] = i + 1;
    }

    dfs("", 0, card);

    for (String comb : orders) {
      String[] order = comb.split("");

      ans = Math.min(ans, getCount(order, cnt, board, r, c));
    }

    return ans;
  }

  //카드를 어떤 순서로 뒤집을 지 순열 구하기
  public void dfs(String comb, int depth, int[] card) {
    if (card.length == depth) {
      orders.add(comb);
      return;
    }

    for (int i = 0; i < card.length; i++) {
      int num = card[i];
      if (!comb.contains("" + num)) {
        dfs(comb + num, depth + 1, card);
      }
    }

  }

  public int getCount(String[] order, int n, int[][] board, int r, int c) {
    int moveCnt = 0;

    int[][] board2 = new int[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        board2[i][j] = board[i][j];
      }
    }

    int[] pos = new int[2];
    pos[0] = r;
    pos[1] = c;

    //순열 순서 이동
    for (String o : order) {
      int target = Integer.parseInt(o);

      moveCnt += bfs(target, board2, pos);
      board2[pos[0]][pos[1]] = 0;
      moveCnt++; //선택

      moveCnt += bfs(target, board2, pos);
      board2[pos[0]][pos[1]] = 0;
      moveCnt++; //선택
    }

    return moveCnt;
  }

  //play
  public int bfs(int target, int[][] board, int[] pos) {
    Deque<Point> q = new ArrayDeque<>();
    q.add(new Point(pos[0], pos[1], 0));
    boolean[][] chk = new boolean[4][4];
    chk[pos[0]][pos[1]] = true;

    while (!q.isEmpty()) {
      Point p = q.remove();

      //목표에 도착
      if (board[p.r][p.c] == target) {
        pos[0] = p.r;
        pos[1] = p.c;
        return p.d;
      }

      for (int i = 0; i < 4; i++) {
        int nr = p.r + dr[i];
        int nc = p.c + dc[i];

        if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || chk[nr][nc]) {
          continue;
        }

        chk[nr][nc] = true;
        q.add(new Point(nr, nc, p.d + 1));
      }

      //컨 + 방향키
      for (int i = 0; i < 4; i++) {
        Point pp = existsInRoute(p.r, p.c, i, board);
        if (chk[pp.r][pp.c] || (pp.r == p.r && pp.c == p.c)) {
          continue;
        }
        chk[pp.r][pp.c] = true;
        q.add(new Point(pp.r, pp.c, p.d + 1));
      }
    }

    return 0;
  }

  //가장 가까운 포인트로 이동
  public Point existsInRoute(int r, int c, int dir, int[][] board) {

    r += dr[dir];
    c += dc[dir];
    while (r < 4 && c < 4 && r >= 0 && c >= 0) {
      if (board[r][c] != 0) {
        return new Point(r, c, 0);
      }

      r += dr[dir];
      c += dc[dir];
    }

    //카드가 없다면 맨 끝으로 이동
    return new Point(r - dr[dir], c - dc[dir], 0);

  }

  class Point {

    int r, c, d;

    Point(int r, int c, int d) {
      this.r = r;
      this.c = c;
      this.d = d;
    }
  }
}