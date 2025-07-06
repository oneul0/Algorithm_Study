import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] chairs = new char[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;
    static int[] selectedStudents = new int[7]; // 0~24까지의 인덱스를 저장 (5*i + j)

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                chairs[i][j] = str.charAt(j);
            }
        }

        // 25개 좌석 중 7개를 선택하는 조합
        combination(0, 0);

        System.out.println(answer);
    }

    // 백트래킹을 이용한 7명 조합 선택
    static void combination(int start, int count) {
        if (count == 7) {
            // 7명 모두 선택 완료
            // 1. Y 학생이 4명 이상인지 확인
            int yCount = 0;
            for (int i = 0; i < 7; i++) {
                int r = selectedStudents[i] / 5;
                int c = selectedStudents[i] % 5;
                if (chairs[r][c] == 'Y') {
                    yCount++;
                }
            }
            if (yCount >= 4) { // Y 학생이 4명 이상이면 무효
                return;
            }

            // 2. 선택된 7명이 모두 인접해 있는지 BFS로 확인
            if (isConnected()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            selectedStudents[count] = i;
            combination(i + 1, count + 1);
        }
    }

    // 선택된 7명이 모두 인접해 있는지 BFS로 확인
    static boolean isConnected() {
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> q = new LinkedList<>();
        int connectedCount = 0;

        // BFS 시작점 설정 (선택된 7명 중 아무나)
        int startR = selectedStudents[0] / 5;
        int startC = selectedStudents[0] % 5;

        q.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        connectedCount++;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                // 범위 벗어나거나 이미 방문했으면 스킵
                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || visited[nr][nc]) {
                    continue;
                }

                // 선택된 7명에 포함되는 좌석인지 확인
                boolean isSelected = false;
                for (int j = 0; j < 7; j++) {
                    if (selectedStudents[j] == (nr * 5 + nc)) {
                        isSelected = true;
                        break;
                    }
                }

                if (isSelected) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    connectedCount++;
                }
            }
        }
        return connectedCount == 7;
    }
}