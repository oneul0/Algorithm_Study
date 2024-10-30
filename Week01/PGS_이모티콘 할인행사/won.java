import java.util.*;

class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        boolean isEmoticon = false; // 임티 플러스 가입 여부

        int[] rate = {10,20,30,40}; // 전체 할인율

        List<List<Integer>> rate_comb = new ArrayList<>();
        // 가능한 모든 조합 생성(중복 순열)
        comb(rate,  new ArrayList<>(),  rate_comb,  emoticons.length);

        for( List<Integer> r : rate_comb){ // 조합된 할인율 : r
            int totalService = 0; // 총 서비스 가입자 수
            int totalCost = 0;  // 총 임티 구입 비용


            for(int[] user: users) {
                int curr_discount = user[0]; // 사용자가 원하는 할인율
                int curr_cost = user[1]; // 사용자 임계점
                isEmoticon = false;
                int user_cost = 0; // 사용자가 지출한 총액

                for(int i=0; i< emoticons.length; i++){
                    int discountRate = r.get(i); // 현재 할인율
                    if(discountRate >= curr_discount ){
                        user_cost += emoticons[i] * (100 - discountRate) / 100;
                    }
                }


                if(user_cost >= curr_cost){
                    isEmoticon = true;
                    totalService ++;
                } else {
                    totalCost += user_cost;
                }
            }

            // answer에 들어갈 값을 넣어주기
            if(totalService > answer[0] || (totalService == answer[0] && totalCost > answer[1])){
                answer[0] = totalService;
                answer[1] = totalCost;
            }
        }


        return answer;
    }

    // 모든 조합 계산
    private void comb(int[] rates, List<Integer> current, List<List<Integer>> output, int size) {

        if(current.size() == size) {
            output.add(new ArrayList<>(current));
            return;
        }

        for(int rate: rates) {
            current.add(rate);
            comb(rates, current, output, size);
            current.remove(current.size() - 1);
        }

    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] users1 = {{40, 10000}, {25, 10000}};
        int[] emoticons1 = {7000, 9000};
        int[] result1 = solution.solution(users1, emoticons1);
        System.out.println(Arrays.toString(result1)); // 예상 결과: [1, 5400]

        int[][] users2 = {
                {40, 2900},
                {23, 10000},
                {11, 5200},
                {5, 5900},
                {40, 3100},
                {27, 9200},
                {32, 6900}
        };
        int[] emoticons2 = {1300, 1500, 1600, 4900};
        int[] result2 = solution.solution(users2, emoticons2);
        System.out.println(Arrays.toString(result2)); // 예상 결과: [4, 13860]
    }
}