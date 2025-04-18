class Solution {

  int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
  int[][] board;
  int xlen, ylen;
  public int solution(int[][] board, int[] aloc, int[] bloc) {
    xlen = board.length;
    ylen = board[0].length;

    this.board = board;

    return miniMax(aloc, bloc);
  }

  public int miniMax(int[] myloc, int[] oploc){
    int result = 0;

    if(board[myloc[0]][myloc[1]] == 0) return 0;
    int myx = myloc[0];
    int myy = myloc[1];
    for(int i = 0; i<4; i++){
      int nx = myx + dx[i];
      int ny = myy + dy[i];

      if(nx<0 || ny<0 || nx>=xlen || ny>=ylen) continue;
      if(board[nx][ny] == 0) continue;

      board[myx][myy] = 0;

      int pos = miniMax(oploc, new int[]{nx,ny})+1;

      board[myx][myy] = 1;

      if(result%2 == 0 && pos%2 == 1) result = pos;
      else if(result%2 == 0 && pos%2 == 0) result = Math.max(result, pos);
      else if(result%2 == 1 && pos%2 == 1) result = Math.min(result, pos);
    }
    return result;
  }
}