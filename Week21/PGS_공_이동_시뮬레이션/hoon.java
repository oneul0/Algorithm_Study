class Solution {
  public long solution(int n, int m, int x, int y, int[][] queries) {
    // 역방향 계산
    long minX = x, maxX = x;
    long minY = y, maxY = y;

    for(int i = queries.length-1; i >= 0; i--) {
      int dir = queries[i][0];
      int step = queries[i][1];

      switch(dir) {
        case 0: // 원래 우측 이동 -> 역으로 좌측 범위 확장
          maxY = Math.min(m-1, maxY + step);
          if(minY != 0) minY += step;
          break;
        case 1: // 원래 좌측 이동 -> 역으로 우측 범위 확장
          minY = Math.max(0, minY - step);
          if(maxY != m-1) maxY -= step;
          break;
        case 2: // 원래 하단 이동 -> 역으로 상단 범위 확장
          maxX = Math.min(n-1, maxX + step);
          if(minX != 0) minX += step;
          break;
        case 3: // 원래 상단 이동 -> 역으로 하단 범위 확장
          minX = Math.max(0, minX - step);
          if(maxX != n-1) maxX -= step;
          break;
      }

      if(minX >= n || maxX < 0 || minY >= m || maxY < 0)
        return 0;
    }

    long width = Math.max(0, maxX - minX + 1);
    long height = Math.max(0, maxY - minY + 1);
    return width * height;
  }
}
