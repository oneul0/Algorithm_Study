import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] arr = new int[4][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                char c = line.charAt(j);
                arr[i][j] = (c - '0');
            }
        }
        int answer = 0;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int number = Integer.parseInt(input[0]) - 1;
            int rotation = Integer.parseInt(input[1]);
            run(number, rotation);
        }
        for (int i = 0; i < 4; i++) {
            if (arr[i][0] == 1) {
                answer += Math.pow(2,i);
            }
        }
        System.out.println(answer);

    }
    public static void run(int number, int rotation) {
        int[] gear = new int[4];
        gear[number] = rotation;
        for (int i = number; i < 3; i++) { // 오른쪽 처리
            if(arr[i][2] != arr[i+1][6]) {
                gear[i+1] = (-1 * gear[i]);
            } else {
                break;
            }
        }
        for (int i = number; i > 0; i--) { // 왼쪽 처리
            if(arr[i][6] != arr[i-1][2]) {
                gear[i-1] = (-1 * gear[i]);
            } else {
                break;
            }
        }
        for(int i = 0; i < 4; i++) {
            if(gear[i] != 0) {
                rotate(arr[i], gear[i]);
            }
        }
    }
    public static void rotate(int[] x, int direction) { 
        if(direction == -1) { // 반시계
            int first = x[0];
            for (int i = 1; i < 8; i++) {
                x[i-1] = x[i];
            }
            x[7] = first;
        } else { // 시계
            int last = x[7];
            for (int i = 7; i > 0; i--) {
                x[i] = x[i-1];
            }
            x[0] = last;
        }
    }
}
