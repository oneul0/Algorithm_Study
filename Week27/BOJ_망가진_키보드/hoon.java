import java.util.*;
import java.io.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int m;
  static String str;
  public static void main(String[] args) throws IOException {
    while(true){
      m = Integer.parseInt(br.readLine());
      if(m == 0){
        break;
      }
      str = br.readLine();
      findStrlen(str);
    }
    br.close();
  }

  public static void findStrlen(String str) {
    // 문자의 등장 횟수를 저장하는 배열
    int[] check = new int[128];

    // result: 최종 결과값(최대 길이)
    // count: 현재 윈도우 내에 포함된 서로 다른 문자 수
    int result = 0, count = 0, l = -1, r = -1;

    // 슬라이딩 윈도우 시작
    while (l <= r) {
      // 사용할 수 있는 키(m) 개수 이하이거나,
      // 이미 등장한 문자를 추가하려는 경우 -> 오른쪽 확장
      if (r + 1 < str.length() &&
          (count < m || (count == m && check[str.charAt(r + 1)] > 0))) {

        r++;
        check[str.charAt(r)]++;

        // 새로운 문자가 처음 등장한 경우
        if (check[str.charAt(r)] == 1) {
          count++;  // 서로 다른 문자 수 증가
        }

      } else {
        // 윈도우 축소
        l++;
        check[str.charAt(l)]--;  // 문자 등장 횟수 감소

        // 문자가 빠지면
        if (check[str.charAt(l)] == 0) {
          count--;  // 서로 다른 문자 수 감소
        }
      }

      result = Math.max(result, r - l);

      if (r == str.length() - 1) {
        break;
      }
    }

    System.out.println(result);
  }

}
