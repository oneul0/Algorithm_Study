import java.io.*;

// N극은 0, S극은 1
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] gears = new String[4];
    static int[] rotated = new int[4];
    public static void main(String[] args) throws IOException {
        int rotate_num = 0;
        //기어 입력
        for(int i = 0; i < gears.length; i++){
            gears[i] = br.readLine();
        }

        //회전 수
        rotate_num = Integer.parseInt(br.readLine());
        //회전
        for(int i = 0; i < rotate_num; i++){
            rotated = new int[4];
            String[] status = br.readLine().split(" ");
            int gear = Integer.parseInt(status[0])-1;
            int dir = Integer.parseInt(status[1]);
            rotated[gear] = dir;
            left(gear, dir);
            right(gear, dir);
            for(int j = 0; j<4; j++){
                rotateGears(j, rotated[j]);
            }
        }

        bw.write(calcScore()+"");
        bw.flush();
        br.close();
        bw.close();
    }
    //시계 방향, 반 시계 방향 회전 메서드
    static void rotateGears(int gear_num, int direction){
        if(direction == 1){
            gears[gear_num] = gears[gear_num].substring(7) + gears[gear_num].substring(0, 7);
        }
        else if(direction == -1){
            gears[gear_num] = gears[gear_num].substring(1) + gears[gear_num].substring(0, 1);
        }
    }
    //기어 조작 메서드(좌우 확인)
    static void left(int gear_num, int direction){
        if(gear_num <= 0) return;
        //현재 기어 9시와 왼쪽 기어 3시가 다르면
        if(gears[gear_num].charAt(6) != gears[gear_num-1].charAt(2)){
            //반대로 회전
            rotated[gear_num-1] = direction * -1;
            left(gear_num-1, direction * -1);
        }
    }
    static void right(int gear_num, int direction){
        if(gear_num >= 3) return;
        //현재 기어 3시와 오른쪽 기어 9시가 다르면
        if(gears[gear_num].charAt(2) != gears[gear_num+1].charAt(6)){
            //반대로 회전
            rotated[gear_num+1] = direction * -1;
            right(gear_num+1, direction * -1);
        }
    }

    //점수 구하는 메서드
    static int calcScore(){
        int score = 0;
        for(int i = 0; i < 4; i++){
            score += (gears[i].charAt(0) == '1' ? 1*(Math.pow(2,i)) : 0);
        }
        return score;
    }
}