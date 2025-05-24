import java.util.*;
class Solution {
	public int[] solution(String[] words, String[] queries) {
		Map<Integer, Trie> forwardTries = new HashMap<>();
		Map<Integer, Trie> backwardTries = new HashMap<>();

		for (String word : words) {
			int len = word.length();
			forwardTries.computeIfAbsent(len, k -> new Trie()).insert(word);
			backwardTries.computeIfAbsent(len, k -> new Trie()).insert(new StringBuilder(word).reverse().toString());
		}

		List<Integer> result = new ArrayList<>();
		for (String query : queries) {
			int len = query.length();
			Trie trie;

			if (query.charAt(0) != '?') {
				trie = forwardTries.get(len);
				if (trie == null) {
					result.add(0);
				} else {
					result.add(trie.countMatchedWord(query));
				}
			} else {
				trie = backwardTries.get(len);
				if (trie == null) {
					result.add(0);
				} else {
					String reversedQuery = new StringBuilder(query).reverse().toString();
					result.add(trie.countMatchedWord(reversedQuery));
				}
			}
		}

		return result.stream().mapToInt(i -> i).toArray();
	}
}

class Trie {

	class Node {
		Map<Character, Node> child = new HashMap<>();
		int wordCount = 0;
	}

	Node root = new Node();

	void insert(String word) {
		Node node = root;
		node.wordCount++;
		for (char c : word.toCharArray()) {
			node = node.child.computeIfAbsent(c, k -> new Node());
			node.wordCount++;
		}
	}

	int countMatchedWord(String query) {
		Node node = root;
		for (int i = 0; i < query.length(); i++) {
			char c = query.charAt(i);
			if (c == '?') {
				return node.wordCount;
			}
			if (!node.child.containsKey(c)) {
				return 0;
			}
			node = node.child.get(c);
		}
		return node.wordCount;
	}
}