class Solution {
  boolean[] foods;
  List<String> ans = new ArrayList<>();
  Set<String> mine = new HashSet<>();
  public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    //현재 상태에서 만들 수 있는 음식 만들고
    //만든 음식 + 재료로 만들 수 있는 음식 확인
    foods = new boolean[recipes.length];
    for(String sup : supplies){
      mine.add(sup);
    }
    int cnt = 2;

    while(cnt>0){
      cnt--;
      for(int i = 0; i<ingredients.size(); i++){
        //만들 수 있으면
        if(canCook(ingredients.get(i))) {
          if(!foods[i]){
            mine.add(recipes[i]);
            ans.add(recipes[i]);
            cnt++;
            foods[i] = true;
          }
        }
      }
    }

    return ans;

  }

  //내가 갖고 있는 재료 중 없는 재료가 있다면
  public boolean canCook(List<String> ingredient){
    for(String ing : ingredient){
      if(!mine.contains(ing)){
        return false;
      }
    }
    return true;
  }
}