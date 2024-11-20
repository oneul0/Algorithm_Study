import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 2 <= n <= 100
        int l = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        for (int i = 0; i < n; i++) {
            check(matrix[i],l); // 행
            check(change(matrix, i),l); // 열
        }
        System.out.println(answer);
    }
    public static void check(int[] lst, int l) {
        boolean[] visited = new boolean[lst.length];
        for (int i = 1; i < lst.length; i++) {
            if (lst[i] == lst[i-1]) {
                continue;
            }
            if (Math.abs(lst[i]-lst[i-1]) == 1) {
                if (lst[i] > lst[i-1]) {
                    if (i < l) {
                        return;
                    }
                    for (int j = i - 1; j >= i - l; j--) {
                        if (visited[j] || lst[j] != lst[i - 1]) {
                            return;
                        }
                        visited[j] = true;
                    }
                } else {
                    if (i + l > lst.length) {
                        return;
                    }
                    for (int j = i; j < i + l; j++) {
                        if (visited[j] || lst[j] != lst[i]) {
                            return;
                        }
                        visited[j] = true;
                    }
                }
            } else {
                return;
            }
        }
        answer++;
    }

    public static int[] change(int[][] matrix, int i) {
        List<Integer> lst = new ArrayList<>();
        for (int j = 0; j < matrix.length; j++) {
            lst.add(matrix[j][i]);
        }
        int[] arr = new int[lst.size()];
        for (int k = 0; k < lst.size(); k++) {
            arr[k] = lst.get(k);
        }
        return arr;
    }

}
