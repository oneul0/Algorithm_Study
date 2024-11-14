import java.io.*;
import java.util.*;

public class Main {

    static int count = 0;
    public static void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());


//      결국 자리 비교 -> 값으로 치면 우선순위 큐를 사용하면 그 개수가 나오지 않을까 ?
//        -> 큐 쓰면 숫자 자체를 만들기가 어렵다. -> StringBuilder
//      q에 담은 후, 다시 set에 담아 중복 처리를 하고, count ?
//      시간초과 -> 결국 dp를 사용해야한다.
//      dp가 되려면 이전에 자리수 계산 값이 n이 증가하면 n-1의 값을 통해 n이 표현이 가능해야 한다.
//      1 / 0 1 2 3 4 5 6 7 8 9 / 10개
//      2 / n =1 , 11 12 13 14 15 16 17 18 19
//         n 10
//         (n(n-1) / 2) -> 55 / 10 + 9 + 8 + ... + 1
//      3 /  0 -> 55개, 1 -> 111 부터 199까지  /n(n-1) /2 에서 (n이 10 ~ 1까지)
//      f(n) = f(n-1) + f(n) -1 + f(n) -2 +  => 10f(n-1) - 45

        int[][] func = new int[n+1][10];

//      if n = 1 func[1][0] = 1
//      if n = 2 func[2][0] = func[1][0] + func[1][1] + ... + func[1][9]
//      func[2][1] = func[1][1] + ... + func[1][9]

        for(int i = 0; i <10; i++){
            func[1][i] = 1;
        }

        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 10; j++){
                func[i][j] = 0;
                for(int k =j; k <10 ; k++){
                    func[i][j] = (func[i][j] + func[i - 1][k]) % 10007;
                }
            }
        }

        int answer = 0;
        for(int i = 0; i <10; i++){
            answer = (answer + func[n][i]) % 10007;
        }


        bw.write(String.valueOf(answer%10007));
        bw.flush();
        bw.close();

    }


    public static void main(String[] args) throws IOException {
        solution();
    }

}
