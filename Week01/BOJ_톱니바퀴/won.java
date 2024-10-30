import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1번째 3 - 2번째 7
 * 2번째 3 - 3번째 7
 * 3번째 3 - 4번째 7
 * 이렇게 맞물리는 구간임
 * */

public class Main {
    static List<int[]> gears; // 톱니바퀴

    // 시계(오른 방향으로 밀기)
    public static void clock(int start){
        int size = gears.get(start).length;
        int last = gears.get(start)[size - 1]; // 마지막 요소를 저장

        // 마지막에서 두 번째 요소부터 첫 번째 요소까지 오른쪽으로 한 칸씩 이동
        for (int i = size - 1; i > 0; i--) {
            gears.get(start)[i] = gears.get(start)[i - 1];
        }

        // 저장했던 마지막 요소를 첫 번째 위치에 할당
        gears.get(start)[0] = last;
    }

    // 반시계(왼쪽 방향으로 밀기)
    public static void declock(int start){
        // 왼쪽으로 1칸 밀기
        int size = gears.get(0).length;
        int first = gears.get(start)[0];
        for(int i = 0; i <size-1 ; i++){
            gears.get(start)[i] = gears.get(start)[i+1];
        }
        gears.get(start)[size - 1] = first;
    }

    // 회전할 톱니와 그에 따른 회전 상태를 저장
    public static boolean[][] ismove(int start, int direction){
        boolean[][] move = new boolean[4][2]; // 회전 방향을 결정

        move[start-1][0] = true; // 회전 여부
        move[start-1][1] = (direction == 1); // 회전 방향 여부

        // 왼쪽으로 회전
        for(int i=start-1; i > 0 ; i--){
            if(gears.get(i)[6] != gears.get(i-1)[2]) {
                move[i - 1][0] = true; move[i - 1][1] = !move[i][1];
            } else{
                break; // 더이상 회전 안함
            }
        }

        // 오른쪽으로 회전
        for(int i=start -1 ; i<3 ;i++){
            if(gears.get(i)[2] != gears.get(i+1)[6]){
                move[i + 1][0] = true; move[i + 1][1] = !move[i][1];
            }else{
                break;
            }
        }


        return move;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 초기 상태 저장
        gears = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            int[] gear = new int[8];
            for (int j = 0; j < 8; j++) {
                gear[j] = line.charAt(j) - '0'; // '0'을 빼서 int로 변환
            }
            gears.add(gear);
        }

        // 회전 횟수 K
        int K = Integer.parseInt(br.readLine());

        // 회전 명령 저장
        int[][] commands = new int[K][2];
        for (int i = 0; i < K; i++) {
            String[] command = br.readLine().split(" ");
            commands[i][0] = Integer.parseInt(command[0]);
            commands[i][1] = Integer.parseInt(command[1]);
        }


        // 회전 하기
        for(int i=0; i< K; i++) {
            int gear_num = commands[i][0];
            int direct_num = commands[i][1];

            boolean[][] move = ismove(gear_num, direct_num); // 회전 상태 결정하기

            for(int j=0; j<4; j++){
                if(move[j][0]){
                    if(move[j][1]){
                        clock(j);
                    }else{
                        declock(j);
                    }
                }
            }

        }

        // 계산하기
        int answer = 0;
        for(int i=0; i< 4; i++) {
            answer += gears.get(i)[0] * Math.pow(2, i);
        }

        System.out.println(answer);
    }
}
