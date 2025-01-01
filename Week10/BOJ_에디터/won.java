
import java.util.*;
import java.io.*;

// 40 min
/*
명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치

L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
    삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
P $	$라는 문자를 커서 왼쪽에 추가함
*/

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 문자열
        String str = br.readLine();
        int N = str.length(); // N: 문자열의 원래 길이 , 현재 커서는 N
        int cursor = N;

        StringBuilder sb = new StringBuilder(str); // 문자 중간에 글자 추가 가능


        // M: 입력할 명령어의 개수
        int M = Integer.parseInt(br.readLine());

        // 명령어를 저장하고 명령어가 P로 시작하면 어디에 추가시켜야 할지도 정해야함
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");

            if (line[0].equals("P")) {
                sb.insert(cursor, line[1]);
                cursor = cursor+1; // 문자열 추가하고 cursor 오른쪽 이동
                System.out.println("P");

            } else if (line[0].equals("L")) {
                if(cursor <= 0) cursor = 0; // 범위 밖
                else cursor = cursor-1;

                System.out.println("L");
            } else if (line[0].equals("D")) {
                if(cursor < sb.length()) cursor = cursor+1;
                System.out.println("D");

            } else if (line[0].equals("B")) {
                // 커서 왼쪽 문자 삭제 (맨 앞이면 무시)
                if(cursor == 0) cursor=0;
                else {
                    sb.deleteCharAt(cursor - 1);
                    cursor = cursor-1;
                }
                System.out.println("B");
            }
            // 현재 상태 출력
            bw.write(sb.toString());
            bw.write("\n");
        }

        bw.write("===================================");
        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}
