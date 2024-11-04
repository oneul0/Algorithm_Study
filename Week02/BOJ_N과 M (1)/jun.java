import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        visited = new boolean[n+1];
        bt(n,m,new ArrayList<>());

    }
    public static void bt(int n, int m,  List<Integer> lst) {
        if(lst.size() == m) {
            for(int i : lst) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        } else {
            for(int i = 1; i <= n; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    lst.add(i);
                    bt(n,m,lst);
                    visited[i] = false;
                    lst.remove(lst.size()-1);
                }
            }
        }
    }
}
