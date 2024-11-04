import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        List<List<Integer>> answer = new ArrayList<>();
        bt(n,m,new ArrayList<>(), answer);
        StringBuilder sb = new StringBuilder();
        for (List<Integer> list : answer) {
            for (Integer integer : list) {
                sb.append(integer).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void bt(int n, int m,  List<Integer> lst, List<List<Integer>> answer) {
        if(lst.size() == m) {
            answer.add(new ArrayList<>(lst));
            return;
        } else {
            for(int i = 1; i <= n; i++) {
                lst.add(i);
                bt(n, m, lst, answer);
                lst.remove(lst.size() - 1);
            }
        }
    }
}
