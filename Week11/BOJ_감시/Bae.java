import java.io.*;
import java.util.*;

public class Main {
    static int y;
    static int x;
    static int cctv_num;
    static int answer = Integer.MAX_VALUE;

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
         /*
        * cctv
        * 1: 한 방향 , 2: 반대 2개, 3: 직각 2개, 4: 3방향, 5: 4 방향
        * 0 : 빈칸, 6 : 벽
        * 회전을 돌려가며 최대한 많은 방 감시하도록 체크
        * dfs하며 각 cctv 방향에 따라 개수 max -> 시간복잡도는 1<= n <= 8 신경 안써도 될 듯 하다
        * 각 cctv는 경우의 수 다르다 1: 4회, 2: 2회, 3: 4회, 4: 4회, 5: 1회
        *
        * */

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int[][] map = new int[y][x];
        List<int[]> cctvs = new ArrayList<>();

        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new int[]{i, j, map[i][j]});
                    cctv_num++;
                }
            }
        }

        if (cctv_num == 0) {
            answer = countBlank(map);
        } else {
            search(0, map, cctvs);
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static int countBlank(int[][] map) {
        int blank = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (map[i][j] == 0) {
                    blank++;
                }
            }
        }
        return blank;
    }

    public static void search(int depth, int[][] map, List<int[]> cctvs) {
        if (depth == cctv_num) {
            answer = Math.min(answer, countBlank(map));
            return;
        }

        int[] cctv = cctvs.get(depth);
        int row = cctv[0];
        int col = cctv[1];
        int type = cctv[2];

        int rotateCount;
        if (type == 2) {
            rotateCount = 2;
        } else if (type == 5) {
            rotateCount = 1;
        } else {
            rotateCount = 4;
        }
        
        for (int dir = 0; dir < rotateCount; dir++) {
            int[][] newMap = copyMap(map);
            check(type, dir, row, col, newMap);
            search(depth + 1, newMap, cctvs);
        }
    }

    private static int[][] copyMap(int[][] original) {
        int[][] copied = new int[y][x];
        for (int i = 0; i < y; i++) {
            copied[i] = original[i].clone();
        }
        return copied;
    }

    public static void check(int cctv, int dir, int curry, int currx, int[][] map) {
        if (cctv == 1) {
            if (dir == 0) {
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            } else if (dir == 1) {
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            } else if (dir == 2) {
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            } else {
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            }
        } else if (cctv == 2) {
            if (dir == 0 || dir == 2) {
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            } else {
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            }
        } else if (cctv == 3) {
            if (dir == 0) {
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            } else if (dir == 1) {
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            } else if (dir == 2) {
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            } else {
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            }
        } else if (cctv == 4) {
            if (dir == 0) {
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            } else if (dir == 1) {
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            } else if (dir == 2) {
                for (int i = currx; i < x; i++) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
            } else {
                for (int i = curry; i < y; i++) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
                for (int i = currx; i >= 0; i--) {
                    if (map[curry][i] == 6) break;
                    if (i != currx) map[curry][i] = 7;
                }
                for (int i = curry; i >= 0; i--) {
                    if (map[i][currx] == 6) break;
                    if (i != curry) map[i][currx] = 7;
                }
            }
        } else {
            for (int i = currx; i < x; i++) {
                if (map[curry][i] == 6) break;
                if (i != currx) map[curry][i] = 7;
            }
            for (int i = curry; i < y; i++) {
                if (map[i][currx] == 6) break;
                if (i != curry) map[i][currx] = 7;
            }
            for (int i = currx; i >= 0; i--) {
                if (map[curry][i] == 6) break;
                if (i != currx) map[curry][i] = 7;
            }
            for (int i = curry; i >= 0; i--) {
                if (map[i][currx] == 6) break;
                if (i != curry) map[i][currx] = 7;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
