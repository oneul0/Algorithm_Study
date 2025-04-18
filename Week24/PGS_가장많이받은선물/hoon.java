import java.util.*;
class Solution {
  Map<String, Map<String, Integer>> giftHistory = new HashMap<>();
  Map<String, Integer> giftScore = new HashMap<>();

  public int solution(String[] friends, String[] gifts) {

    for (String friend : friends) {
      giftHistory.put(friend, new HashMap<>());
      giftScore.put(friend, 0);
    }

    for (String gift : gifts) {
      String[] parts = gift.split(" ");
      String from = parts[0];
      String to = parts[1];

      giftHistory.get(from).put(to, giftHistory.get(from).getOrDefault(to, 0) + 1);
      giftScore.put(from, giftScore.get(from) + 1);
      giftScore.put(to, giftScore.get(to) - 1);
    }

    int maxReceive = 0;
    for (int i = 0; i < friends.length; i++) {
      int count = 0;
      for (int j = 0; j < friends.length; j++) {
        if (i == j) continue;

        String a = friends[i];
        String b = friends[j];

        int aToB = giftHistory.get(a).getOrDefault(b, 0);
        int bToA = giftHistory.get(b).getOrDefault(a, 0);

        //기록 있으면 점수 더 큰 쪽에 선물 주기
        if (aToB > bToA) {
          count++;
        } else if (aToB == bToA && giftScore.get(a) > giftScore.get(b)) {
          count++;
        }
      }
      maxReceive = Math.max(maxReceive, count);
    }

    return maxReceive;
  }

}

