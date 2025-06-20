import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> plug = new HashSet<>();
        int ans = 0;

        for (int i = 0; i < k; i++) {
            int curr = arr[i];

            if (plug.contains(curr)) continue;
            if (plug.size() < n) {
                plug.add(curr);
                continue;
            }

            // 가장 나중에 오는 것 뽑기
            int farthestIndex = -1;
            int unplug = -1;

            for (int p : plug) {
                int nextUse = Integer.MAX_VALUE;
                for (int j = i + 1; j < k; j++) {
                    if (arr[j] == p) {
                        nextUse = j;
                        break;
                    }
                }
                if (nextUse > farthestIndex) {
                    farthestIndex = nextUse;
                    unplug = p;
                }
            }

            plug.remove(unplug);
            plug.add(curr);
            ans++;
        }

        System.out.println(ans);
    }
}
