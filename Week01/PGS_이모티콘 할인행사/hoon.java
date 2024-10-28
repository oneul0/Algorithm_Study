import java.util.*;
class Solution {
    int[] discount = {10, 20, 30, 40}, answer = new int[2], emoticons;
    int[][] users;
    ArrayList<Pair> result = new ArrayList<>();
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users; this.emoticons = emoticons;
        dfs(0, new int[emoticons.length]);
        result.sort(Comparator.reverseOrder());
        answer[0] = result.get(0).subs;
        answer[1] = result.get(0).total;
        return answer;
    }

    //모든 경우 다 구해보기
    void dfs(int depth, int[] cur_discount){
        //기저조건
        if(depth >= emoticons.length){
            int new_subs = 0, total_cart = 0;
            //유저별로
            for(int i = 0; i<users.length; i++){
                int user_cart = 0;
                //이모티콘 샀는지 비교
                for(int j = 0; j<emoticons.length; j++){
                    int saled = calcSale(emoticons[j], cur_discount[j]);
                    //유저가 못 참고 사는지 확인
                    if(cur_discount[j] >= users[i][0]){
                        user_cart += saled;
                    }
                }

                if(user_cart >= users[i][1]) new_subs++;
                else total_cart += user_cart;
            }
            result.add(new Pair(new_subs, total_cart));
            return;
        }

        for(int i = 0 ; i<4; i++){
            cur_discount[depth] = discount[i];
            dfs(depth+1, cur_discount);
        }

    }

    //세일된 가격
    int calcSale(int origin_price, int sale_pctg){
        return (origin_price * (100 - sale_pctg)) / 100;
    }
}

class Pair implements Comparable<Pair>{
    int subs, total;

    Pair(int subs, int total){
        this.subs = subs;
        this.total = total;
    }

    @Override
    public int compareTo(Pair o) {
        if (subs == o.subs) {
            return total - o.total;
        }
        return subs - o.subs;
    }
}