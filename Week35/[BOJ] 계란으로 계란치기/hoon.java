import java.io.*;
import java.util.*;

public class Main {
    static class Egg {
        int hp, weight;
        Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }
    }

    static int N, max = 0;
    static Egg[] eggs;

    public static void dfs(int idx) {
        if (idx == N) {
            int broken = 0;
            for (Egg e : eggs) {
                if (e.hp <= 0) broken++;
            }
            max = Math.max(max, broken);
            return;
        }

        if (eggs[idx].hp <= 0) {
            dfs(idx + 1);
            return;
        }

        boolean hit = false;
        for (int i = 0; i < N; i++) {
            if (i == idx || eggs[i].hp <= 0) continue;

            eggs[idx].hp -= eggs[i].weight;
            eggs[i].hp -= eggs[idx].weight;
            hit = true;

            dfs(idx + 1);

            eggs[idx].hp += eggs[i].weight;
            eggs[i].hp += eggs[idx].weight;
        }

        if (!hit) dfs(idx + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(hp, weight);
        }

        dfs(0);
        System.out.println(max);
    }
}
