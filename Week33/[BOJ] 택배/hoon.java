import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        List<Delivery> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int boxes = Integer.parseInt(st.nextToken());
            list.add(new Delivery(from, to, boxes));
        }

        Collections.sort(list);

        int[] load = new int[N + 1];
        int result = 0;

        for (Delivery d : list) {
            int maxLoaded = 0;

            for (int i = d.from; i < d.to; i++) {
                maxLoaded = Math.max(maxLoaded, load[i]);
            }

            int canLoad = Math.min(C - maxLoaded, d.boxes);
            if (canLoad <= 0) continue;

            for (int i = d.from; i < d.to; i++) {
                load[i] += canLoad;
            }

            result += canLoad;
        }

        System.out.println(result);
    }
}

class Delivery implements Comparable<Delivery> {
    int from, to, boxes;

    public Delivery(int from, int to, int boxes) {
        this.from = from;
        this.to = to;
        this.boxes = boxes;
    }

    @Override
    public int compareTo(Delivery o) {
        return this.to - o.to;
    }
}
