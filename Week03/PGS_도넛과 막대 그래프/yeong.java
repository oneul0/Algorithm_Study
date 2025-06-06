import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {

        // 각 노드의 들어가는 간선, 나오는 간선 수 저장하는 map
        Map<Integer, int[]> nodes = new HashMap<>();

        int extraNode = -1; // 추가된 노드
        int doughnut = 0; // 도넛 그래프 개수
        int stick = 0;	// 막대 그래프 개수
        int figure8 = 0;  // 8자 그래프 개수


        // 1단계: 각 노드의 간선 개수 계산
        for( int[] edge: edges ){
            int from = edge[0];
            int to = edge[1];

            if(!nodes.containsKey(from)){
                nodes.put(from, new int[]{0,0});
            }
            if(!nodes.containsKey(to)){
                nodes.put(to, new int[]{0,0});
            }

            nodes.get(from)[0]++;
            nodes.get(to)[1]++;

        }

        // 2단계: 노드를 탐색하며 각 그래프의 '핵심 노드' 찾으면 개수 갱신
        for( int key : nodes.keySet()){
            int[] count = nodes.get(key);

            // 나가는 간선이 2개 이상이고, 들어오는 간선이 없을 경우
            // = 생성한 정점
            if(count[0] >= 2 && count[1] == 0) {
                extraNode = key;
            }
            // 나가는 간선이 없고, 들어오는 간선이 있을 경우
            // = 막대 그래프
            else if(count[0] == 0 && count[1] > 0) {
                stick++;
            }
            // 들어오는 것과 나가는 것이 각 2개 이상일 경우
            // = 8자 그래프
            else if(count[0] >= 2 && count[1] >= 2) {
                figure8++;
            }
        }

        // 추가한 노드에서 나오는 정점의 개수 = 전체 그래프의 수
        // 전체 그래프의 수에서 stick, figure8 빼면 도넛 그래프의 수
        doughnut = nodes.get(extraNode)[0] - stick - figure8;

        return new int[]{ extraNode, doughnut, stick, figure8 };

    }
}