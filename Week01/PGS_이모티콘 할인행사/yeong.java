import java.util.*;

class Solution {
    static int[][] users;
    static int[] emoticons;

    static int[] dc, ans;
    static int maxCnt, maxProfit; //최대 회원 수, 최대 수익

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;

        dc = new int[emoticons.length]; //이모티콘이 할인되는 퍼센트 저장
        ans = new int[2];

        //이모티콘이 할인되는 분기 모두 탐색: 중복 순열을 위한 재귀함수
        comb(0);

        ans[0] = maxCnt;
        ans[1] = maxProfit;

        return ans;
    }

    //이모티콘이 할인되는 모든 분기 탐색 메소드
    private static void comb(int depth) {
        if(depth == dc.length) { // 모든 할인율 적용 시, 할인율 정해진 상태로 회원과 수익 계산
            calculate();
            return;
        }

        for(int i=10; i<=40; i+=10) {
            dc[depth] = i;
            comb(depth + 1);
        }
    }

    //회원과 수익 계산 메소드
    private static void calculate() {
        int user = 0;
        int profit = 0;

        for(int[] u : users) {
            int discount = u[0];
            int price = u[1];

            int sum = 0; //개인이 이모티콘을 구매한 금액의 합

            for(int i=0; i <dc.length; i++) {
                if(dc[i] >= discount) { //할인 기준이 맞으면 이모티콘 구입
                    sum += (emoticons[i] / 100) * (100 - dc[i]);
                }
            }
            if(sum >= price) { user++; } //가입
            else { profit += sum; } // 수익 올림
        }

        //해당 할인율에 대해 가입한 회원과 수익, 최대값으로 갱신
        if(user > maxCnt) {
            maxCnt = user;
            maxProfit = profit;
            return;
        } else if(user == maxCnt) {
            if(maxProfit < profit) {
                maxProfit = profit;
            }
        }
    }
}