import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        L = Integer.parseInt(in[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (canRoadRow(i)) ans++;
            if (canRoadCol(i)) ans++;
        }
        bw.write(ans + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    // 가로 방향 검사
    static boolean canRoadRow(int row) {
        boolean[] installed = new boolean[N];
        for (int col = 0; col < N - 1; col++) {
            int diff = map[row][col + 1] - map[row][col];
            if (diff == 0) continue;

            if (diff > 1 || diff < -1) return false; // 1이상 차이나면 진행 불가

            if (diff == 1) {
                for (int i = 0; i < L; i++) {
                    //이미 자리에 설치된 경사로가 있거나 내려갈 수 있는 공간이 유효하지 않거나 경사로를 설치할 공간이 평평한지
                    if (col - i < 0 || installed[col - i] || map[row][col - i] != map[row][col]) {
                        return false;
                    }
                    installed[col - i] = true;
                }
            } else if (diff == -1) {
                for (int i = 1; i <= L; i++) {
                    //이미 자리에 설치된 경사로가 있거나 내려갈 수 있는 공간이 유효하지 않거나 경사로를 설치할 공간이 평평한지
                    if (col + i >= N || installed[col + i] || map[row][col + i] != map[row][col + 1]) {
                        return false;
                    }
                    installed[col + i] = true;
                }
            }
        }
        return true;
    }

    // 세로 방향 검사
    static boolean canRoadCol(int col) {
        boolean[] installed = new boolean[N];
        for (int row = 0; row < N - 1; row++) {
            int diff = map[row + 1][col] - map[row][col];
            if (diff == 0) continue;
            if (diff > 1 || diff < -1) return false; // 1 이상 차이나면 진행 불가

            if (diff == 1) {
                for (int i = 0; i < L; i++) {
                    //이미 자리에 설치된 경사로가 있거나 내려갈 수 있는 공간이 유효하지 않거나 경사로를 설치할 공간이 평평한지
                    if (row - i < 0 || installed[row - i] || map[row - i][col] != map[row][col]) {
                        return false;
                    }
                    installed[row - i] = true;
                }
            } else if (diff == -1) {
                for (int i = 1; i <= L; i++) {
                    //이미 자리에 설치된 경사로가 있거나 내려갈 수 있는 공간이 유효하지 않거나 경사로를 설치할 공간이 평평한지
                    if (row + i >= N || installed[row + i] || map[row + i][col] != map[row + 1][col]) {
                        return false;
                    }
                    installed[row + i] = true;
                }
            }
        }
        return true;
    }
}
