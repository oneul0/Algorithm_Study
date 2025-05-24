import java.util.*;

public class Solution {
  Set<Set<Integer>> answerSet = new HashSet<>();
  List<Integer>[] db;

  public int solution(String[] user_id, String[] banned_id) {
    int n = banned_id.length;
    db = new ArrayList[n];

    // 1. banned_id[i]에 대해 매칭 가능한 user_id 인덱스를 db[i]에 저장
    for (int i = 0; i < n; i++) {
      db[i] = new ArrayList<>();
      for (int j = 0; j < user_id.length; j++) {
        if (isMatch(user_id[j], banned_id[i])) {
          db[i].add(j);
        }
      }
    }

    // 2. DFS로 가능한 조합 탐색
    dfs(0, new HashSet<>());

    return answerSet.size();
  }

  // banned pattern과 user id가 매칭되는지 확인
  private boolean isMatch(String user, String ban) {
    if (user.length() != ban.length()) return false;
    for (int i = 0; i < user.length(); i++) {
      if (ban.charAt(i) != '*' && user.charAt(i) != ban.charAt(i)) return false;
    }
    return true;
  }

  // DFS 탐색, depth는 현재 ban 인덱스, selected는 현재까지 선택한 user 인덱스
  private void dfs(int depth, Set<Integer> selected) {
    if (depth == db.length) {
      answerSet.add(new HashSet<>(selected));
      return;
    }

    for (int idx : db[depth]) {
      if (selected.contains(idx)) continue;

      selected.add(idx);
      dfs(depth + 1, selected);
      selected.remove(idx);
    }
  }
}
