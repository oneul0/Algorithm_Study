import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static class Node {
    Map<String, Node> child;

    public Node(){
      this.child = new TreeMap<>();
    }
  }

  static class Trie{
    Node root;

    public Trie(){
      this.root = new Node();
    }
    public void insert(String paths){
      Node node = root;
      String[] path = paths.split("\\\\");

      for(String s : path){
        node.child.putIfAbsent(s, new Node());
        node = node.child.get(s);
      }
    }

    public void printPaths(Node cur, int depth) throws Exception{
      for(String key : cur.child.keySet()){
        for(int i = 0; i<depth; i++){
          bw.write(" ");
        }
        bw.write(key+"\n");
        printPaths(cur.child.get(key), depth+1);
      }
    }
  }



  public static void main(String[] args) throws Exception {
    int n = Integer.parseInt(br.readLine());
    Trie trie = new Trie();
    String line;
    for(int i = 0; i<n; i++){
      line = br.readLine();
      trie.insert(line);
    }
    trie.printPaths(trie.root, 0);
    bw.flush();
    bw.close();
    br.close();
  }

}
