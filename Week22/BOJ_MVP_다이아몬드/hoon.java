import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static Map<Character, Integer> rank = new HashMap<>();

  public static void main(String[] args) throws Exception {
    int N = Integer.parseInt(br.readLine());
    char[] grade = new char[N];
    String[] input = br.readLine().split(" ");
    rank.put('S', Integer.parseInt(input[0]));
    rank.put('G', Integer.parseInt(input[1]));
    rank.put('P', Integer.parseInt(input[2]));
    rank.put('D', Integer.parseInt(input[3]));

    String str = br.readLine();
    for (int i = 0; i < str.length(); i++) {
      grade[i] = str.charAt(i);
    }

    int result = 0;
    int current = 0;
    for (int i = 0; i < grade.length; i++) {
      if (grade[i] == 'B') {
        current = rank.get('S') - current - 1;
      } else if (grade[i] == 'S') {
        current = rank.get('G') - current - 1;
      } else if (grade[i] == 'G') {
        current = rank.get('P') - current - 1;
      } else if (grade[i] == 'P') {
        current = rank.get('D') - current - 1;
      } else if (grade[i] == 'D') {
        current = rank.get('D');
      }

      result += current;
    }

    bw.write(result + "\n");
    bw.flush();
    bw.close();
    br.close();

  }
}
