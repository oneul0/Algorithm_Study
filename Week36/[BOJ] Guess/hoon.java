import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] matrix;
    static int N;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        matrix = new char[N+1][N+1];
        String str = br.readLine();
        for(int i = 0, idx = 0; i<N; i++){
            for(int j = i; j<N; j++){
                matrix[i][j] = str.charAt(idx++);
            }
        }
        guess(0);
    }

    static boolean guess(int idx){
        if(idx == N){
            for(int res : result){
                System.out.print(res + " ");
            }
            return true;
        }

        for(int i = -10; i<=10; i++){
            result.add(i);
            if(possible(idx)){
                if(guess(idx+1)) return true;
            }
            result.remove(result.size()-1);
        }
        return false;
    }

    static boolean possible(int idx){
        int sum = 0;
        for(int i = idx; i>=0; i--){
            sum += result.get(i);

            if(
                (matrix[i][idx] == '+' && sum <= 0)
                    || (matrix[i][idx] == '-' && sum >= 0)
                    || (matrix[i][idx] == '0' && sum != 0)
            ) return false;
        }
        return true;
    }
}