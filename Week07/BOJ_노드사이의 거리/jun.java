package solo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 2 * 10^8
public class Main {
    public static ArrayList<Node>[] tree;
    public static int[] parent, depth, distanceFromRoot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 10^3 이하
        int m = Integer.parseInt(st.nextToken()); // 10^3 이하

        tree = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b,c));
            tree[b].add(new Node(a,c));
        }

        parent = new int[n + 1];
        depth = new int[n + 1];
        distanceFromRoot = new int[n + 1];
        dfs(1,0,0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            System.out.println(find(x,y));
        }
    }
    public static void dfs(int node, int parentNum, int distance) {
        parent[node] = parentNum;
        distanceFromRoot[node] = distance;

        for (Node child : tree[node]) {
            if (child.next != parentNum) {
                depth[child.next] = depth[node] + 1;
                dfs(child.next, node, distance + child.cost);
            }
        }
    }
    public static int find(int u, int v) {
        int dist = 0;
        while (depth[u] != depth[v]) {
            if (depth[u] > depth[v]) {
                dist += distanceFromRoot[u] - distanceFromRoot[parent[u]];
                u = parent[u];
            } else {
                dist += distanceFromRoot[v] - distanceFromRoot[parent[v]];
                v = parent[v];
            }
        }

        while (u != v) {
            dist += (distanceFromRoot[u] - distanceFromRoot[parent[u]])
                    + (distanceFromRoot[v] - distanceFromRoot[parent[v]]);
            u = parent[u];
            v = parent[v];
        }

        return dist;
    }

}
class Node {
    int next;
    int cost;
    public Node(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }
}
