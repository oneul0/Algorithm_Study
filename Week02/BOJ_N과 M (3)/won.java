/*
 * 왜 List<List<Integer>>을 사용하면 시간초과가 나고
 * StringBuilder을 사용하면 시간 초과가 나지 않을까?
 * */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 한 줄을 읽어와서 공백을 기준으로 분리
        String[] input = br.readLine().split(" ");

        // 각각을 N, M으로 변환
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // 조합을 생성하고 출력
        StringBuilder sb = new StringBuilder();
        comb(N, M, 1, new StringBuilder(), sb);
        System.out.print(sb.toString()); // 모든 조합을 한 번에 출력
    }

    private static String joinWithSpace(List<Integer> list) {
        return String.join(" ", list.stream().map(String::valueOf).toArray(String[]::new));
    }

    private static void comb(int n, int m, int start, StringBuilder current, StringBuilder output) {
        if (current.length() /2 == m) {
            output.append(current).append("\n");
            return;
        }

        // 백트래킹
        for (int i = 1; i <= n; i++) {
            // 현재 숫자를 추가하고 공백 추가
            int currentLength = current.length();
            current.append(i).append(" ");

            comb(n, m, i , current, output); // 다음 숫자는 i + 1

            // 마지막에 추가한 숫자와 공백을 제거
            current.setLength(currentLength); // 이전 길이로 복원
        }
    }
}
