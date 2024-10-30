import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] wheels = new int[5][9];


        for (int i = 1; i <= 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j + 1] = line.charAt(j) - '0';
            }
        }


        int n = Integer.parseInt(br.readLine());

//      1번 i 2 + 2번 i 6 // 2번 i 2 + 3번 i 6 // 3번 i 2 + 4번 i 6
//      극이 다를때만 회전, 아니면 무회전
//      n = 0, s = 1
//      1 시계 2 반시계

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

//            1. 방향
//            회전 전 극의 상태 확인 -> 회전 여부 확인 후 해당하는 바퀴만 회전
//            1 회전 -> 2 확인, 2 회전 -> 3 확인, 3 회전 -> 4 확인
//            2 회전 -> 1,3 확인, 1 회전 + (3 회전 -> 4 확인)
//            3 회전 -> 2,4 확인, 4 회전 + (2 회전 -> 1 확인)
//            4 회전 -> 3 확인, 3 회전 -> 2 확인, 2 회전 -> 1 확인

            boolean[] rotate = new boolean[5];
            int[] rotdir = new int[5];
            rotate[num] = true;
            rotdir[num] = dir;

//           오른쪽
            for (int j = num; j < 4; j++) {
                if (wheels[j][3] != wheels[j + 1][7]) {
                    rotate[j + 1] = true;
                    rotdir[j + 1] = -rotdir[j];
                } else {
                    break;
                }
            }

            // 왼쪽
            for (int j = num; j > 1; j--) {
                if (wheels[j][7] != wheels[j - 1][3]) {
                    rotate[j - 1] = true;
                    rotdir[j - 1] = -rotdir[j];
                } else {
                    break;
                }
            }



            // 바퀴 회전
            for (int j = 1; j <= 4; j++) {
                if (rotate[j]) {
                    if (rotdir[j] == 1) {
                        // 시계
                        int temp = wheels[j][8];
                        for (int k = 8; k > 1; k--) {
                            wheels[j][k] = wheels[j][k - 1];
                        }
                        wheels[j][1] = temp;
                    } else {
                        // 반시계
                        int temp = wheels[j][1];
                        for (int k = 1; k < 8; k++) {
                            wheels[j][k] = wheels[j][k + 1];
                        }
                        wheels[j][8] = temp;
                    }
                }
            }

        }

        int score = 0;
        for (int j = 1; j <= 4; j++) {
            if (wheels[j][1] == 1) {
                int Two = 1;
                for (int k = 0; k < j - 1; k++) {
                    Two *= 2;
                }
                score += Two;
            }
        }

        bw.write(String.valueOf(score));



        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
