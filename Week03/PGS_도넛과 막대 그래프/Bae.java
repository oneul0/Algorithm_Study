// 그래프 유형
// 1. 도넛 크기 n -> n개의 정점, n-1개의 간선, 임의의 출발점에서 모든 정점을 방문 후 출발점으로 되돌아옴. 단방향
// 2. 막대 크기 n -> n개의 정점, n-1개 간선, 출발 점, 도착 점이 정해져 잇다. 단방향
// 3. 6자 크기 n -> 2n+1의 정점, 2n+2개 간선 (n개의 도넛 그래프 *2 + 중간 점 1), (n개의 도넛 그래프의 간선 (n-1) * 2 + 4(중간 점과 두 개의 도넛 그래프를 잇는 간선 4개))
// 임의의 그래프들에 하나의 정점을 생성 후, 존재하는 그래프들에 간선을 생성 (그래프 3개 , 정점 생성 후 3개의 간선이 각각 그래프를 연결)
// 생성된 정점의 번호, 도넛 그래프 수 , 막대 그래프 수, 8자 그래프 수 [4] 배열로 return

// 1. 생성된 정점 = 입력만 2개이상 (모든 그래프의 수의 합은 2 이상)
//   1-1 edges의 길이 10^5 
// 2. 그래프 판단
//   2-1 도넛 : a-b b-a 쌍으로 존재하는 경우 (1개인 경우 출발- 도착 일치발- 도착 일치)
//   2-2 막대 : 단방향
//   2-3 8자 : a-b, c-b + b-d, b-f 보내는거 2개 받는거 2개 
import java.util.*;
    
class Solution {
    static int[] answer = new int[4];
    public int[] solution(int[][] edges) {
        
// answer = {새로운 정점, 도넛 개수, 막대 개수, 8자 개수}
// map 기준
// 새로운 정점 : 생성된 정점 = 입력만 2개이상 (모든 그래프의 수의 합은 2 이상)
// 막대 : 1~ n중 key가 없는 정점의 개수
// 도넛과 8자 : value값 양방향 존재 -> 도넛/ 노드에서 입력 2개, 출력 2개 -> 8자
        
// 현재 5개의 테스트 케이스 통과 X
// 현재 새로운 정점은 각 모든 그래프들과 연결이 되어잇다.
        
        Map<Integer,List<Integer>> inPoint= new HashMap<>();
        Map<Integer,List<Integer>> outPoint = new HashMap<>();
        Set<Integer> sets = new HashSet<>();
        
        int maxPoint = 0;
        for(int i=0; i<edges.length; i++) {
            sets.add(edges[i][0]);
            sets.add(edges[i][1]);
            inPoint.putIfAbsent(edges[i][0],new ArrayList<>());
            outPoint.putIfAbsent(edges[i][1],new ArrayList<>());
            inPoint.get(edges[i][0]).add(edges[i][1]);
            outPoint.get(edges[i][1]).add(edges[i][0]);
            maxPoint = Math.max(maxPoint,Math.max(edges[i][0],edges[i][1]));
        }
        
        int[] newPoint = new int[2];
        
//              새로운 정점
        for(int set : sets) {
            if(inPoint.get(set) != null) {
                if(inPoint.get(set).size() >1 && !outPoint.containsKey(set)) {
                    newPoint[0] = set;
                    newPoint[1] = inPoint.get(set).size();
                } 
            }
        }
        
        int[] visited = new int[maxPoint+1];
        
        for(int temp : inPoint.get(newPoint[0])) {
            Deque<Integer>q = new ArrayDeque<>();
            q.add(temp);
            bfs(temp,inPoint, outPoint,q,visited,newPoint[0]);
        }
        
        answer[0] = newPoint[0];
        answer[1] = newPoint[1]-answer[2]-answer[3];
        
        
        return answer;
        
    }
    
    public static void bfs(int start, Map<Integer,List<Integer>> inPoint, Map<Integer,List<Integer>> outPoint, Deque<Integer>q,int[] visited, int newPoint) {
        while(!q.isEmpty()) {
            int curr= q.remove();
            if(visited[curr]>2) {
                return;
            }
            
            if(!inPoint.containsKey(curr) && outPoint.get(curr).size()>0) {
                answer[2]++;
                return;
            }
            
            if(inPoint.get(curr) != null&& outPoint.get(curr) != null) {
                if(inPoint.get(curr).size()>= 2 && outPoint.get(curr).size() >= 2) {
                    answer[3]++;
                    return;
                }
            }
            if(inPoint.get(curr) !=null) {
                for(int next : inPoint.get(curr)) {
                    visited[next]++;
                    q.add(next);
                }
            }
        }
    }
}
