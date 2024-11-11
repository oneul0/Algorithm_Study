import java.io.*;

public class Main {

    static int count = 0;

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        func(0, n, arr);
        bw.write(String.valueOf(count)); 
        bw.flush();
        bw.close();
    }

    public static void func(int y, int n, int[][] arr) {
        if (y == n) {
            count++; 
            return;
        }

        for (int x = 0; x < n; x++) {
            if (arr[y][x] == 0 && isValid(arr, n, y, x)) {
                arr[y][x] = 1; 
                func(y + 1, n, arr); 
                arr[y][x] = 0; 
            }
        }
    }

    public static boolean isValid(int[][] arr, int n, int y, int x) {
        // 열 검사
        for (int nexty = 0; nexty < y; nexty++) {
            if (arr[nexty][x] == 1) {
                return false;
            }
        }

        // 대각선 검사
        for (int i = 1; i < n; i++) {
            
            if (check(y - i, x - i, n)) {
                if (arr[y - i][x - i] == 1) {
                    return false;
                }
            }

            if (check(y - i, x + i, n)) {
                if (arr[y - i][x + i] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean check(int nexty, int nextx, int n) {
        return nexty >= 0 && nexty < n && nextx >= 0 && nextx < n;
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
