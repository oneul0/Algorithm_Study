package BOJ_개미굴;

import java.io.*;
import java.util.*;
class TrieNode {
    // 자식 노드를 저장하는 맵 (사전순 정렬을 위해 TreeMap 사용)
    Map<String, TrieNode> children = new TreeMap<>();
}

public class Main {
    static TrieNode root = new TrieNode();

    public static void insert(String[] word) {
        TrieNode cur = root;

        for(String s : word) {
            // 해당 먹이가 없다면 새로운 노드를 추가한다.
            cur = cur.children.computeIfAbsent(s, k -> new TrieNode());
        }
    }

    // 출력 함수
    public static void printTrie(TrieNode node, int depth) {
        for(String key : node.children.keySet()) {
            System.out.println("--".repeat(depth)+key);
            printTrie(node.children.get(key), depth+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            insert(Arrays.copyOfRange(input,1, input.length));// 첫번째 값 k를 제외하고 삽입
        }
        printTrie(root, 0);

    }
}
