import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
         * 길은 한 행 or 한 열
         * 한 행 또는 한 열의 높이가 모두 같아야한다.
         * 경사로 조건 1개만 놓는다 경사로 길이 L만큼 모두 높이가 같아야한다
         * 경사로 놓아지는 높이 차는 1
         * N NxN, L 경사로 길이
         * 경사로 조건이 중요하다
         * 개수는 무제한, 애초에 높이가 다시 올라가면 안되니 결국 경사로가 놓여질 수 있는 조건은
         * 마지막에 높은 칸 -> 낮은 칸에서 낮은 칸의 개수가 L과 동일하고 그 index가 n-1 (지도의 끝)이여야 한다.
         * 먼저 경사로를 놓아서 크기를 조정하고 -> 완전 탐색으로 길 개수 세기
         * 그런데 가로,세로 놓을 수 있는 방법이 여러 개다.
         * 지나가는 길의 개수 -> 가로 세로 두개의 경우 중 최대 길의 개수는 아니니 신경 안써도 될듯하다.
         * 근데 가로, 세로 두 개 다 가능한 부분은 어짜피 lxl이 주어질 때기 때문에
         * */

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for( int i=0; i<n; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

//      1. 가로, 세로 나누어서 경사로 배치
//      가로
//      양 끝 확인
//      그냥 같다라고 하면 안된다. 예를 들어 33322 인경우 3에도 경사로가 설치된다.
//      기존에 있던 길에 대해서도 판별이 안된다.
//      증가 / 감소 오르막, 내리막 경사로가 구분되어 있다.
//      기존에 map에 설치를 하고, 검사를 하면 문제가 된다. 방법의 문제는 아닌거 같지만 수정


        int answer = 0;

//      가로 체크
        for (int i = 0; i < n; i++) {
            if (check(map[i], l)) {
                answer++;
            }
        }

//      세로 체크
//      세로를 따로 안만들고 하는 방법
        for (int i = 0; i < n; i++) {
            int[] col = new int[n];
            for (int j = 0; j < n; j++) {
                col[j] = map[j][i];
            }
            if (check(col, l)) {
                answer++;
            }
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    public static boolean check(int[] road, int l) {
        boolean[] isValid = new boolean[road.length];

        for (int i = 0; i <road.length-1 ; i++) {
            int diff = road[i] - road[i+1];

            if(diff > 1 || diff < -1) {
                return false;
            }

//           true -> 내리막, false -> 오르막
            if(diff == 1) {
                if(!build(road, isValid, i+1, l, true)) {
                    return false;
                }
            } else if (diff == -1) {
                if(!build(road, isValid, i, l, false)) {
                    return false;
                }
            }

        }

        return true;

    }

    public static boolean build(int[] road, boolean[] isValid, int i, int l, boolean updown) {
        for(int j=0; j<l; j++) {
            int k = 0;
            if(updown) {
                k = i+j;
            } else {
                k = i-j;
            }

            if(k< 0 || k>= road.length || isValid[k] || road[k] != road[i]) {
                return false;
            }
            isValid[k] = true;
        }

        return true;

    }




    public static void main(String[] args) throws IOException {
        solution();
    }
}
