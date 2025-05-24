import java.util.*;

class Solution {
  List<String> result = new ArrayList<>();

  Map<Node, List<Node>> merged = new HashMap<>();
  Map<Node, Node> coords = new HashMap<>();
  Map<Node, String> table = new HashMap<>();

  public void init() {
    for (int x = 1; x < 51; x++) {
      for (int y = 1; y < 51; y++) {
        Node tmp = new Node(x, y);
        coords.put(tmp, tmp);
        table.put(tmp, "EMPTY");
        merged.put(tmp, new ArrayList<>());
      }
    }
  }

  public void updateCommand(String[] cmd) {
    if (cmd.length == 3) {
      // 값으로 전체 변경
      String before = cmd[1], after = cmd[2];
      for (Node key : table.keySet()) {
        if (table.get(key).equals(before)) {
          table.put(key, after);
        }
      }
    } else {
      Node okey = parseNode(cmd[1], cmd[2]);
      String ov = cmd[3];
      Node origin = coords.get(okey);
      table.put(origin, ov);
    }
  }

  public void mergeCommand(String[] cmd) {
    Node coord1 = parseNode(cmd[1], cmd[2]);
    Node coord2 = parseNode(cmd[3], cmd[4]);

    Node origin1 = coords.get(coord1);
    Node origin2 = coords.get(coord2);

    if (origin1.equals(origin2)) return;

    // 병합할 때, 더 앞의 좌표를 대표로 삼는다
    Node newOrigin = origin1;

    // 기존 병합 그룹 병합
    List<Node> group1 = new ArrayList<>(merged.getOrDefault(origin1, new ArrayList<>()));
    List<Node> group2 = new ArrayList<>(merged.getOrDefault(origin2, new ArrayList<>()));

    group1.add(origin1);
    group2.add(origin2);

    List<Node> total = new ArrayList<>();
    total.addAll(group1);
    total.addAll(group2);

    // 모든 셀의 coords를 newOrigin으로 바꾸고 merged 갱신
    for (Node n : total) {
      coords.put(n, newOrigin);
    }
    merged.put(newOrigin, total);

    // 대표 셀 값은 값 있는 쪽을 유지
    String val1 = table.getOrDefault(origin1, "EMPTY");
    String val2 = table.getOrDefault(origin2, "EMPTY");
    if ("EMPTY".equals(val1) && !"EMPTY".equals(val2)) {
      table.put(newOrigin, val2);
    } else {
      table.put(newOrigin, val1);
    }
  }

  public void unmergeCommand(String[] cmd) {
    Node target = parseNode(cmd[1], cmd[2]);
    Node origin = coords.get(target);
    List<Node> mergedNodes = merged.getOrDefault(origin, new ArrayList<>());
    String keepValue = table.getOrDefault(origin, "EMPTY");

    // 병합된 모든 셀 원상 복구
    for (Node n : mergedNodes) {
      coords.put(n, n);
      table.put(n, "EMPTY");
      merged.put(n, new ArrayList<>());
    }
    coords.put(origin, origin);
    table.put(origin, "EMPTY");
    merged.put(origin, new ArrayList<>());

    // 선택한 셀 값 복원
    table.put(target, keepValue);
  }

  public void printCommand(String[] cmd) {
    Node key = parseNode(cmd[1], cmd[2]);
    Node root = coords.get(key);
    result.add(table.getOrDefault(root, "EMPTY"));
  }

  public Node parseNode(String x, String y) {
    return new Node(Integer.parseInt(x), Integer.parseInt(y));
  }

  public String[] solution(String[] commands) {
    init();
    for (String command : commands) {
      String[] cmd = command.split(" ");
      switch (cmd[0]) {
        case "UPDATE":
          updateCommand(cmd);
          break;
        case "MERGE":
          mergeCommand(cmd);
          break;
        case "UNMERGE":
          unmergeCommand(cmd);
          break;
        case "PRINT":
          printCommand(cmd);
          break;
      }
    }
    return result.toArray(new String[0]);
  }
}

class Node {
  int x, y;

  Node(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Node)) return false;
    Node other = (Node) o;
    return x == other.x && y == other.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
