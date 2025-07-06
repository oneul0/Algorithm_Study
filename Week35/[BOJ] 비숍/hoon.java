import java.io.*;
import java.util.*;

public class Main {
    static class Coords {
        int x, y;
        Coords(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Coords> whiteIndexs = new ArrayList<>(), blackIndexs = new ArrayList<>();
    static boolean[] leftDiag, rightDiag;
    static boolean[][] board;
    static int N;

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];
        StringTokenizer st;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = (num == 1);
                if((i + j) % 2 == 0) {
                    whiteIndexs.add(new Coords(i, j));
                }
                else {
                    blackIndexs.add((new Coords(i, j)));
                }
            }
        }
        leftDiag = new boolean[2*N+1];
        rightDiag = new boolean[2*N+1];
    }

    public static int place(int idx, int count, final List<Coords> indexs) {
        if (idx == indexs.size()) return count;

        int max = 0;
        Coords cur = indexs.get(idx);
        int leftIdx = cur.x + cur.y;
        int rightIdx = cur.x - cur.y + (N-1);

        if (board[cur.x][cur.y] && !leftDiag[leftIdx] && !rightDiag[rightIdx]) {
            leftDiag[leftIdx] = true;
            rightDiag[rightIdx] = true;
            max = Math.max(max, place(idx + 1, count + 1, indexs));
            leftDiag[leftIdx] = false;
            rightDiag[rightIdx] = false;
        }

        max = Math.max(max, place(idx + 1, count, indexs));

        return max;
    }

    public static void main(String[] args) throws IOException {
        init();
        int white = place(0, 0, whiteIndexs);
        Arrays.fill(leftDiag, false);
        Arrays.fill(rightDiag, false);
        int black = place(0, 0, blackIndexs);
        System.out.println(white + black);
    }
}