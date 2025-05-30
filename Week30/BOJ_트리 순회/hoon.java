import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  static int N;
  static Map<Character, ArrayList<Character>> graph = new HashMap<>();

  public static void init() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      char p = st.nextToken().charAt(0);

      graph.putIfAbsent(p, new ArrayList<>());
      for(int j = 0; j<2; j++){
        graph.get(p).add(st.nextToken().charAt(0));
      }
    }
  }

  public static void preFix(char root) {
    if(root == '.') return;

    System.out.printf("%c", root);
    preFix(graph.get(root).get(0));
    preFix(graph.get(root).get(1));
  }

  public static void middleFix(char root) {
    if(root == '.') return;

    middleFix(graph.get(root).get(0));
    System.out.printf("%c", root);
    middleFix(graph.get(root).get(1));
  }

  public static void postFix(char root) {
    if(root == '.') return;

    postFix(graph.get(root).get(0));
    postFix(graph.get(root).get(1));
    System.out.printf("%c", root);
  }

  public static void main(String[] args) throws IOException {
    init();
    //0이 왼 1이 오
    //전
    preFix('A');
    System.out.println();
    //중
    middleFix('A');
    System.out.println();
    //후
    postFix('A');
    System.out.println();
  }
}
