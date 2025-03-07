import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static class Node {
    Map<String, Node> child;
    public Node() {
      child = new TreeMap<>();
    }
  }

  static class Trie{
    Node root;

    public Trie() {
      root = new Node();
    }

    public void insert(List<String> foods) {
      Node cur = root;
      for(String food : foods) {
        cur.child.putIfAbsent(food, new Node());
        cur = cur.child.get(food);
      }
    }

    public void print(Node cur, int depth) throws IOException {
      for (String key : cur.child.keySet()) {
        for (int i = 0; i < depth; i++) {
          bw.write("--");
        }
        bw.write(key + "\n");
        print(cur.child.get(key), depth + 1);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    Trie trie = new Trie();

    StringTokenizer st;

    for(int i = 0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      int K = Integer.parseInt(st.nextToken());
      List<String> foods = new ArrayList<>();
      for(int j = 0; j<K; j++) {
        foods.add(st.nextToken());
      }
      trie.insert(foods);
    }

    trie.print(trie.root, 0);
    bw.flush();
    br.close();
    bw.close();
  }

}
