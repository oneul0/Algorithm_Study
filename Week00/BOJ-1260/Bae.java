import java.io.*;
import java.util.*;

public class Main {
    public static void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

//      양방향

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[][] arr= new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            map.putIfAbsent(one, new ArrayList<Integer>());
            map.putIfAbsent(two, new ArrayList<Integer>());
            map.get(one).add(two);
            map.get(two).add(one);
        }

        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        List<Integer> dfs_list = new ArrayList<>();
        List<Integer> bfs_list = new ArrayList<>();
        int[] dfs_visited = new int[n+1];
        int[] bfs_visited = new int[n+1];

        dfs(map, start, dfs_visited, dfs_list);
        bfs(map, start, bfs_visited, bfs_list);

        for (int i = 0; i < dfs_list.size(); i++) {
            System.out.print(dfs_list.get(i));
            if (i < dfs_list.size() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
        for (int i = 0; i < bfs_list.size(); i++) {
            System.out.print(bfs_list.get(i));
            if (i < bfs_list.size() - 1) {
                System.out.print(" ");
            }
        }

        bw.flush();
        bw.close();

    }

    public static void dfs(Map<Integer,List<Integer>> map, int start, int[] dfs_visited, List<Integer> dfs_list) {
        dfs_visited[start] = 1;
        dfs_list.add(start);

        List<Integer> nexts = map.get(start);
        //          nullpointer 방지
        if(nexts!=null){
            for(int next : nexts){
                if(dfs_visited[next] == 0){
                    dfs(map, next, dfs_visited, dfs_list);
                }
            }
        }
    }

    public static void bfs(Map<Integer,List<Integer>> map, int start, int[] bfs_visited, List<Integer> bfs_list ) {
        Deque<Integer> q = new ArrayDeque<>();

        q.add(start);
        bfs_visited[start] = 1;

        while(!q.isEmpty()){
            int now = q.poll();
            bfs_list.add(now);

            List<Integer> nexts = map.get(now);

//          nullpointer 방지
            if (nexts != null) {
                for (int next : nexts) {
                    if (bfs_visited[next] == 0) {
                        bfs_visited[next] = 1;
                        q.add(next);
                    }
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
