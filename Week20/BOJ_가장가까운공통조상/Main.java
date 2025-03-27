// https://www.acmicpc.net/problem/3584

// 최소 공통 조상: 가장 높이가 낮으면서 두 개의 노드의 공통조상인 노드
// 하나의 노드가 먼저 부모를 거슬러 올라가며 방문처리하며 탐색한 후
// 두번째 노드가 탐색해 가면서 처음으로 첫번째 노드에 의해 방문 처리된 노드를 찾는 방식

import java.io.*;

public class Main {
    static int n;  // 노드의 개수
    static int MAX_VALUE = 10001; // 최대 노드 개수
    static int[] p = new int[MAX_VALUE]; // 각 노드의 부모를 저장하는 배열

    // 초기화: 모든 노드의 부모를 자기 자신으로 설정 (트리가 아직 구성되지 않은 상태)
    public static void init(){
        for(int i = 1; i <= n; i++){
            p[i] = i;
        }
    }

    // 부모-자식 관계 설정 (b의 부모를 a로 설정)
    public static void setParent(int a, int b){
        p[b] = a;
    }

    // 최소 공통 조상(LCA) 찾기
    public static int findSameParent(int a, int b){
        int parentA = a, parentB = b;
        boolean[] visited = new boolean[MAX_VALUE]; // 부모 탐색 방문 여부 저장

        // 첫 번째 노드(A)의 조상들을 따라가며 방문 처리
        visited[parentA] = true;
        while (true) {
            if (parentA == p[parentA]) break; // 루트 노드 도달 시 종료
            parentA = p[parentA]; // 부모 노드로 이동
            visited[parentA] = true; // 방문 표시
        }

        // 두 번째 노드(B)의 조상들을 따라가며 처음 방문된 노드를 찾는다.
        while (true) {
            if (visited[parentB]) break; // A의 경로와 겹치는 최초의 조상이 최소 공통 조상
            parentB = p[parentB]; // 부모 노드로 이동
        }

        return parentB; // 최소 공통 조상 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine()); // 노드 개수 입력
            init(); // 부모 배열 초기화

            // 부모-자식 관계 입력
            for (int j = 0; j < n - 1; j++) {
                String[] input = br.readLine().split(" ");
                int parent = Integer.parseInt(input[0]);
                int child = Integer.parseInt(input[1]);
                setParent(parent, child); // 부모 관계 설정
            }

            // 두 개의 타겟 노드 입력
            String[] input2 = br.readLine().split(" ");
            int targetA = Integer.parseInt(input2[0]);
            int targetB = Integer.parseInt(input2[1]);

            // 최소 공통 조상 찾기 및 출력
            System.out.println(findSameParent(targetA, targetB));
        }
    }
}
