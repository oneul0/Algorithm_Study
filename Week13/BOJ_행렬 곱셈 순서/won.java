import java.util.*;
import java.io.*;
public class Main {
    static int matrix[][];
    static int dp[][]= new int[500][500];

    public static int matrixMulti(int x, int y){
        if(dp[x][y]>0) return dp[x][y]; // 종료 조건 x -> y 로 곱하는 값이 업뎃 되면 리턴!

        if(y -x <=0 ) return 0; // 연산하는 행렬의 길이가 1보다 작다면 연산횟수는 0이다.

        int mm = Integer.MAX_VALUE;
        for(int k=x; k< y; k++){
            mm = Math.min(mm,
                    matrixMulti(x, k) + matrixMulti(k+1, y)+ matrix[x][0]*matrix[k][1]*matrix[y][1]);
        }
        return dp[x][y]=mm;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n: 행렬의 개수(1 <= n <= 50)
        int n = Integer.parseInt(br.readLine());
        matrix = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            // 1<= r,c, <=500
            matrix[i][0] = Integer.parseInt(temp[0]);// r
            matrix[i][1] = Integer.parseInt(temp[1]);// c
        }

        int answer = matrixMulti(0, n-1);
        System.out.println(answer);

    }
}
