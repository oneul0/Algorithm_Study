import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Pair {
        int x, y;
        char type;
        int depth;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Pair(int x, int y, char type, int depth){
            this.x = x;
            this.y = y;
            this.type = type;
            this.depth = depth;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, G, R, answer = 0;
    static int[] dx = {-1,1,0,0}, dy = {0,0, -1,1};
    static int[][] garden;
    static List<Pair> canSeeding = new ArrayList<>();
    public static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        garden = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                garden[i][j] = Integer.parseInt(st.nextToken());
                if(garden[i][j] == 2){
                    canSeeding.add(new Pair(i, j));
                }
            }
        }
    }

    //배양액을 퍼트리는 함수
    //두 종류의 배양액이 유일한 조합으로 파종되어야 하므로 combination
    public static void seeding(int idx, int gCnt, int rCnt, char[][] chk){
        //조합 만들기
        if(gCnt == G && rCnt == R){
            //조합 다 만들어졌으면 return 전에 spreadOut 실행
            answer = Math.max(answer, spreadOut(chk));
            return ;
        }

        if(idx == canSeeding.size()){
            return;
        }

        seeding(idx+1, gCnt, rCnt, chk);

        Pair cur = canSeeding.get(idx);
        if(gCnt < G){
            chk[cur.x][cur.y] = 'g';
            seeding(idx+1, gCnt+1, rCnt, chk);
            chk[cur.x][cur.y] = 0;
        }
        if(rCnt < R){
            chk[cur.x][cur.y] = 'r';
            seeding(idx+1, gCnt, rCnt+1, chk);
            chk[cur.x][cur.y] = 0;
        }
    }

    //배양액이 퍼져나가는 함수 BFS
    public static int spreadOut(char[][] chk){
        char[][] curChk = new char[N][M];
        for(int i = 0; i<N; i++){
            System.arraycopy(chk[i], 0, curChk[i], 0, M);
        }
        Deque<Pair> q = new ArrayDeque<>();
        int[][] depths = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(curChk[i][j] == 'g' || curChk[i][j] == 'r') {
                    q.offer(new Pair(i, j, curChk[i][j], 1));
                    depths[i][j] = 1;
                }
            }
        }
        int flower = 0;
        while(!q.isEmpty()){
            Pair cur = q.remove();
            int cx = cur.x;
            int cy = cur.y;
            if(curChk[cx][cy] == 'f') continue;

            for(int i = 0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx < 0 || ny < 0 || nx >=N || ny >= M || garden[nx][ny] == 0|| curChk[nx][ny] == 'f') continue;
                if (depths[nx][ny] == 0) {
                    depths[nx][ny] = cur.depth + 1;
                    curChk[nx][ny] = cur.type;
                    q.offer(new Pair(nx, ny, cur.type, cur.depth + 1));
                } else if (depths[nx][ny] == cur.depth + 1 && curChk[nx][ny] != cur.type) {
                    curChk[nx][ny] = 'f';
                    flower++;
                }
            }
        }

        return flower;
    }

    public static void main(String[] args) throws IOException {
        init();
        seeding(0,0,0,new char[N][M]);
        System.out.println(answer);
    }
}


/**
 * - 주어진 배양액을 뿌릴 수 있는 위치에 모두 소비할 때까지 뿌린다.
 * - 뿌린 자리에서 매 초 인접한 땅으로 배양액이 퍼져나간다.
 * - 동시에 두 배양액이 만난 시점의 자리에 꽃이 피어나고 두 배양액은 사라진다.
 * 배양액을 심는 함수
 * 배양액을 퍼트리는 함수
 * 배양액이 만났는지 체크하는 함수?
 * 배양액은 나아갈 수 있는 모든 방향으로 나아가므로 두 배양액이 만난 시점은 항상 동일한 시점에 퍼져나갔을 때를 상정한다.
 * */