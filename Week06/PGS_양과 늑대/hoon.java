class Solution {
    int[] info;
    int[][] edges;
    int max = 0;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;

        dfs(0, 0, 0, new boolean[info.length]);

        return max;
    }
    //DFS(현재 노드 번호, 양의 수, 늑대의 수, 다음으로 방문할 수 있는 노드의 집합)
    void dfs(int cur, int sheep, int wolf, boolean[] chk) {
        chk[cur] = true;
        int cur_sheep = (sheep + (info[cur]^1));
        int cur_wolf = (wolf + info[cur]);

        max = Math.max(max, cur_sheep);
        //양보다 늑대가 많아지면 탐색할 수 없으므로 리턴
        if(cur_sheep <= cur_wolf) return;

        for(int[] edge : edges){
            if(chk[edge[0]] && !chk[edge[1]]){
                //각 노드에서의 방문 체크를 독립적으로 관리
                dfs(edge[1], cur_sheep, cur_wolf, chk.clone());
            }
        }
    }
}
