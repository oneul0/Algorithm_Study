import java.util.*;
import java.io.*;

public class Main {
    private static final String SEPARATOR = " ";

    private static int n, l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine(), SEPARATOR);

        n = Integer.parseInt(stz.nextToken());
        l = Integer.parseInt(stz.nextToken());
        int answer = 0;

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine(), SEPARATOR);
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 가로행
        for (int i = 0; i < n; i++) {
            int[] forward = map[i];
            answer += getRoadCount(forward);
        }

        // 세로행
        for (int j = 0; j < n; j++) {
            int[] forward = new int[n];
            for (int i = 0; i < n; i++) {
                forward[i] = map[i][j];
            }
            answer += getRoadCount(forward);
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getRoadCount(int[] forward) {
        if (isRoad(forward)) return 1;
        return 0;
    }

    private static boolean isRoad(int[] heights) {
        int h = heights[0];
        int len = 1;
        boolean[] incline = new boolean[n]; // 경사면이 설치유무

        for (int i = 1; i < n; i++) {
            if (heights[i] == h) { // 높이가 같다면
                len++;
            } else if (heights[i] - h == 1) { // 올라갈 때
                if (len >= l) {
                    for (int j = i - l; j < i; j++) {
                        if (incline[j]) return false; // 새로 설치하는 경사면에 다른 경사면이 겹치게 되면
                    }
                    h = heights[i];
                    len = 1;
                } else {
                    return false;
                }
            } else if (h - heights[i] == 1) { // 내려갈 때
                if (i + (l - 1) < n) {
                    for (int j = i + 1; j < i + (l); j++) {
                        if (heights[i] != heights[j] || incline[j]) { // 경사면을 설치가능 유무 확인
                            return false;
                        }
                    }
                    // 경사면 설치
                    Arrays.fill(incline, i + 1, i + l, true);
                    if (l == 1) incline[i] = true;

                    // 경사면 설치이 이후로 위치 조정
                    h = heights[i];
                    len = 1;
                    i += (l - 1);
                } else {
                    return false;
                }
            } else { // 높이 차이가 2 이상일 때
                return false;
            }
        }
        return true;
    }
}