import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] arr;
    static Set<Character> list = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void combination(int depth, int start, String str, int vowels) {
        if(depth == L){
            if(vowels >= 1 && vowels <= L-2) System.out.println(str);
            return;
        }
        for(int i = start; i<arr.length; i++){
            if(list.contains(arr[i])){
                combination(depth+1, i+1, str+arr[i], vowels+1);
            }
            else{
                combination(depth+1, i+1, str+arr[i], vowels);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        L = Integer.parseInt(st[0]);
        C = Integer.parseInt(st[1]);
        arr = new char[C];
        st = br.readLine().split(" ");
        br.close();
        for(int i = 0; i < C; i++){
            arr[i] = st[i].charAt(0);
        }
        Arrays.sort(arr);
        combination(0, 0, "", 0);
    }
}
