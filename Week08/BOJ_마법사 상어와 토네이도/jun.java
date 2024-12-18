package solo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int n;
    public static int outAmount;
    public static int[][] dir = {{0,-1}, {1,0}, {0,1}, {-1,0}};
    public static int[][][] sandMoving = {
            {{-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}, {-2, 0, 2}, {2, 0, 2}, {0, -1, 55}}, // 좌
            {{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}, {0, -2, 2}, {0, 2, 2}, {1, 0, 55}}, // 하
            {{-1, -1, 1}, {1, -1, 1}, {-1, 0, 7}, {1, 0, 7}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}, {-2, 0, 2}, {2, 0, 2}, {0, 1, 55}}, // 우
            {{1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}, {0, -2, 2}, {0, 2, 2}, {-1, 0, 55}} // 상
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        outAmount = 0;
        int[] distance = makeDistance();
        int x = n/2, y = n/2;
        int d = 0;
        for (int dist : distance) {
            for (int i = 0; i < dist; i++) {
                y += dir[d][0];
                x += dir[d][1];
                move(matrix, y, x, d);
                if (y == 0 && x == 0) {
                    System.out.println(outAmount);
                    return;
                }
            }
            d = (d+1) % 4;
        }
    }
    public static int[] makeDistance() {
        int[] lst = new int[(2*n)-1];
        int i=0, j=1;
        while(true) {
            if (i == (2*n)-2) {
                lst[i] = j-1;
                break;
            }
            lst[i] = j;
            lst[i+1] = j;
            i += 2;
            j += 1;
        }
        return lst;
    }

    public static void move(int[][] matrix, int y, int x, int dir) {
        int temp = matrix[y][x];
        matrix[y][x] = 0;
        int total = 0;
        for (int[] m : sandMoving[dir]) {
            int ny = y + m[0];
            int nx = x + m[1];
            int per = m[2];
            int amount = (int)(temp * per / 100.0);
            if (per != 55) {
                total += amount;
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    outAmount += amount;
                } else {
                    matrix[ny][nx] += amount;
                }
            }
        }
        int remain = temp - total;
        int alphaY = y + sandMoving[dir][9][0];
        int alphaX = x + sandMoving[dir][9][1];
        if (alphaY < 0 || alphaY >= n || alphaX < 0 || alphaX >= n) {
            outAmount += remain;
        } else {
            matrix[alphaY][alphaX] += remain;
        }
    }
}
