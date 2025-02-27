import java.util.*;
public class Solution {
  public int solution(String[] words) {
    Trie trie = new Trie();

    // Trie에 모든 단어 삽입
    for (String word : words) {
      trie.insert(word);
    }

    // 각 단어별 최소 입력 횟수 계산
    int ans = 0;
    for (String word : words) {
      ans += trie.countPrefix(word);
    }

    return ans;
  }

  //트라이의 각 노드
  class Node {
    Map<Character, Node> child;
    int count; // 이 접두사가 몇 번 등장했나

    public Node() {
      child = new HashMap<>();
      count = 0;
    }
  }

  class Trie {
    Node root;

    public Trie() {
      root = new Node();
    }

    // 단어 삽입
    public void insert(String word) {
      Node node = root;
      for (char ch : word.toCharArray()) {
        node.child.putIfAbsent(ch, new Node());
        node = node.child.get(ch);
        node.count++;
      }
    }

    // 최소 입력 횟수 계산
    public int countPrefix(String word) {
      Node node = root;
      int inputCount = 0;
      for (char ch : word.toCharArray()) {
        node = node.child.get(ch);
        inputCount++;
        if (node.count == 1) { //말단 노드 도착
          break;
        }
      }
      return inputCount;
    }
  }
}