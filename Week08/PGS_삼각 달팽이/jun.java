class Solution {
    public int[] solution(int n) {
        int len = ((n+1) * n) / 2;
        int[] answer = new int[len];
        int[][] dir = new int[][]{{1,0},{0,1},{-1,-1}};
        int[][] tri = new int[n][];
        for (int i=0; i<n; i++) {
            tri[i] = new int[i+1];
        }
        int y = 0, x = 0;
        int d = 0;
        for (int i=1; i<=len; i++) {
            tri[y][x] = i;
            int ny = y + dir[d][0];
            int nx = x + dir[d][1];
            if(ny < 0 || nx < 0 || ny >= n || nx >= tri[y].length || tri[ny][nx] != 0) {
                d = (d+1)%3;
                ny = y + dir[d][0];
                nx = x + dir[d][1];
            }
            y = ny;
            x = nx;
        }
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = tri[i][j];
            }
        }
        return answer;
    }
}
