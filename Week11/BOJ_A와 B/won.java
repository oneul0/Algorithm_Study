// 2 HOUR
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine(); // 시작 문자열
        String T = br.readLine(); // 목표 문자열
        int T_len = T.length();

        // BFS를 위한 큐
        Queue<String> queue = new LinkedList<>();
        // 방문한 문자열을 기록하기 위한 Set
        Set<String> visited = new HashSet<>();

        // S -> T로 바꿔야 한다.
        // 방법
        // 1) 문자열의 뒤에 A를 추가한다.
        // 2) 문자열을 뒤집고 뒤에 B를 추가한다.
        // 결과 : 바꿀 수 있으면 1 아니면 0

        /*
         * S -> T: 메모리 초과 계속 난다.
         * T -> S로 바꿔보기(역추적)
         * 1) 뒷 문자 A: 맨뒤 문자 삭제
         * 2) 뒷 문자 B: 맨뒤 문자 삭제 => 문자열 뒤집기
         * */

        /*
        * B, ABBA (깊이 0)
        * BA    BB (깊이 1)
        * BAA   ABB     BAA     ABB (깊이 2)
        * BAAA  AABB    ABBA    BBAB    BAAA    AABA    ABBA    BBAB (깊이 3)
        *
        * 다른 예
        * AB, ABB
        * ABA BAB (깊이 1)
        *
        * 거꾸로 생각해보기
        * B, ABBA

        ABBA
        1. 뒤가 A
            1. A만 삭제 시킴
            2. ABB
        2. ABB
            1. 뒤가 B
            2. 뒤에 있는 것을 삭제 시키고 문자열 뒤집기
            3. BA
        3. 뒤가 A
            1. A만 삭제 시킴
        4. B가 나옴

        * */

        int ans = 0;
        queue.add(T);
        visited.add(T);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // current가 null이거나 빈 문자열이면 스킵
            if (current == null || current.isEmpty()) {
                continue;
            }

            if(current.equals(S)) {
                //  System.out.println("current = "+current+", S = "+S);
                ans = 1;
                break;
            }

            // 방법 1
            char lastChar = current.charAt(current.length() - 1);
            String str = current.substring(0, current.length() - 1);

            // String first = current.substring(0, current.length() - 1);
            if(lastChar=='A' &&!visited.contains(str)) {
                queue.add(str);
                visited.add(str);
            }

            // 방법 2
            // String tmp = current.substring(0, current.length() - 1);
            StringBuilder sb = new StringBuilder(str);
            String second = sb.reverse().toString();

            if(lastChar=='B'&&!visited.contains(second)) {
                queue.add(second);
                visited.add(second);
            }

        }


        bw.write(ans+"\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
