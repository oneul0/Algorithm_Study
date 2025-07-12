import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<String> result;

    public static void backtrack(int cur, int N, String str) {
        if (cur == N + 1) {
            if (calculate(str) == 0) {
                result.add(str);
            }
            return;
        }

        backtrack(cur + 1, N, str + " " + cur);
        backtrack(cur + 1, N, str + "+" + cur);
        backtrack(cur + 1, N, str + "-" + cur);
    }

    public static int calculate(String expression) {
        expression = expression.replaceAll(" ", "");

        StringTokenizer st = new StringTokenizer(expression, "+-", true);
        int sum = 0;
        int sign = 1;

        if (st.hasMoreTokens()) {
            sum = Integer.parseInt(st.nextToken());
        }

        while (st.hasMoreTokens()) {
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (op.equals("+")) {
                sign = 1;
            } else if (op.equals("-")) {
                sign = -1;
            }
            sum += sign * num;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            result = new ArrayList<>();
            backtrack(2, N, "1");
            Collections.sort(result);
            for (String s : result) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}