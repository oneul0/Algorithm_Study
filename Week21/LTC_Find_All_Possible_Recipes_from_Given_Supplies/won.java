// 의존성 그래프를 만들고, 위상 정렬(Topological Sort) 방식으로 해결할 수 있다

//recipes: 만들 수 있는 요리 이름 배열.
//
//ingredients: 각 레시피에 필요한 재료 목록. (ingredients[i]는 recipes[i]를 만드는 데 필요한 재료 목록)
//
//supplies: 처음부터 가지고 있는 재료 (무한히 사용 가능).

// 진입차수: 그래프에서 어떤 노드로 들어오는 간선의 수
// 재료가 supplies에 있다면 그것을 쓰는 레시피의 진입 차수를 줄인다
// 진입차수가 0이되면 그 레시피를 만들 수 있다는 뜻 !!
// 그 레시피도 이제 재료로 쓸 수 있음 -> 다른 레시피의 진입 차수를 계속 줄여나감
import java.util.*;
class Solution {

    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>();
        //graph = { "yeast": ["bread"], "flour": ["bread"] }
        //→ "yeast"와 "flour"는 "bread"를 만드는데 쓰인다.
        Map<String, Integer> inDegree = new HashMap<>();
        Set<String> supplySet = new HashSet<>(Arrays.asList(supplies));
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();


        // 모든 레시피에 대해 inDegree를 0으로 초기화
        for (String recipe : recipes) {
            inDegree.put(recipe, 0);
        }

        // 그래프와 진입차수 구성
        for( int i=0; i<recipes.length; i++ ) {
            String recipe = recipes[i];
            for(String ing: ingredients.get(i)) {
                if (!graph.containsKey(ing)) {
                    graph.put(ing, new ArrayList<>());
                }
                graph.get(ing).add(recipe);
                inDegree.put(recipe, inDegree.get(recipe)+1);
            }
        }

        // 초기 재료를 큐에 넣기
        for(String supply: supplySet) {
            queue.offer(supply);
        }

        // 위상 정렬 시작
        while(!queue.isEmpty()) {
            String item = queue.poll();

            if(!graph.containsKey(item)) continue;
            for(String recipe : graph.get(item)) {
                inDegree.put(recipe, inDegree.get(recipe)-1);
                if(inDegree.get(recipe) == 0) {
                    queue.offer(recipe);
                    result.add(recipe);
                }
            }
        }
        return result;
    }

}