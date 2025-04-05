import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int L, C;
  static String[] arr, alp = {"a", "e", "i", "o", "u"};

  static Set<String> set = new HashSet<>();

  public static void main(String[] args) throws Exception{
    StringTokenizer st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    arr = new String[C];

    arr = br.readLine().split(" ");

    br.close();
    for(String s : alp){
      set.add(s);
    }
    Arrays.sort(arr);
    bt(0, new StringBuilder(), new boolean[C], -1, 0, 0);

    bw.close();
  }

  static void bt(int depth, StringBuilder sb, boolean[] chk, int last, int consonant, int gather) throws IOException {
    if(depth >= L){
      if(consonant < 1 || gather < 2) return;

      bw.write(sb.toString()+"\n");
      bw.flush();
      return;
    }

    for(int i = 0; i<C; i++){
      if(chk[i]) continue;
      if(i>0 && arr[i-1].compareTo(arr[i]) >= 0) continue;
      if(i <= last) continue;

      sb.append(arr[i]);
      chk[i] = true;

      if(set.contains(arr[i])){
        bt(depth+1, sb, chk, i, consonant+1, gather);
      }
      else{
        bt(depth+1, sb, chk, i, consonant, gather+1);
      }

      sb.deleteCharAt(sb.length()-1);
      chk[i] = false;

    }
  }
}