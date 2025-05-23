import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static Map<String, String> parent;
  static Map<String, Integer> size;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());

    for (int t = 0; t < T; t++) {

      parent = new HashMap<>();
      size = new HashMap<>();

      int F = Integer.parseInt(br.readLine());

      for (int i = 0; i < F; i++) {
        st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        if (!parent.containsKey(a)) {
          parent.put(a, a);
          size.put(a, 1);
        }
        if (!parent.containsKey(b)) {
          parent.put(b, b);
          size.put(b, 1);
        }

        union(find(a), find(b));

        System.out.println(size.get(find(a))); // 현재 집합 크기 출력
      }
    }
  }

  static void union(String a, String b) {
    a = find(a);
    b = find(b);

    if (!a.equals(b)) {
      parent.put(b, a);
      size.put(a, size.get(a) + size.get(b));
    }
  }

  static String find(String x) {
    if (!parent.get(x).equals(x)) {
      parent.put(x, find(parent.get(x)));
    }
    return parent.get(x);
  }
}
