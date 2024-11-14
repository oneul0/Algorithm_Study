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

        List<List<Integer>> ANS_comb = new ArrayList<>();

        comb(N, M, new ArrayList<>(), ANS_comb);

        for(List<Integer> list : ANS_comb){
            System.out.println(joinWithSpace(list));
        }

    }

    private static String joinWithSpace(List<Integer> list){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++){
            sb.append(list.get(i));
            if(i < list.size() - 1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static void comb(int n, int m, List<Integer> current, List<List<Integer>> output) {
        if(current.size() == m) {
            output.add(new ArrayList<>(current)); return ;
        }

        // 백트래킹
        for(int i =1 ; i <= n ; i++) {
            if(!current.contains(i)) {
                current.add(i);
                comb(n, m, current, output);
                current.remove(current.size() - 1);
            }
        }
    }
}
