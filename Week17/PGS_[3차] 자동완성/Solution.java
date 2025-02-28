import java.util.*;
class Solution {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count=0;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
                node.count++; // 해당 노드를 지나간 단어의 개수 증가
            }
        }

        int countKeystrokes(String word) {
            TrieNode node = root;
            int keystrokes = 0;
            for (char c : word.toCharArray()) {
                node = node.children.get(c);
                keystrokes++;
                if(node.count == 1) break;  // 여기까지 입력하면 단어가 유일해짐
            }
            return keystrokes;
        }
    }
    public int solution(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int totalKeystrokes = 0;
        for (String word : words) {
            totalKeystrokes += trie.countKeystrokes(word);
        }
        return totalKeystrokes;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[]{"go", "gone", "guild"})); // 7
        System.out.println(sol.solution(new String[]{"abc", "def", "ghi", "jklm"})); // 4
        System.out.println(sol.solution(new String[]{"word", "war", "warrior", "world"})); // 15
    }
}
