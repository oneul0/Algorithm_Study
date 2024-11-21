import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";

        int[] score = {0,3,2,1,0,1,2,3}; //choices의 번호를 index를 통해 점수를 계산
        String[] user = {"R","T","C","F","J","M","A","N"}; // 성격 유형
        int[] calc = {0,0,0,0,0,0,0,0}; // user 마다 점수 넣어두는 배열

        int index = 0;

        for(int i=0; i < survey.length; i++) {
            int target = choices[i]; // 몇 점을 추가해야하는지의 index
            String[] temp = {survey[i].substring(0,1), survey[i].substring(1,2)};
            if( choices[i] < 4) { // 1-3점 일 때
                index = Arrays.asList(user).indexOf(temp[0]);
                calc[index] += score[target];
            } else {
                // 5-7점 일 때
                index = Arrays.asList(user).indexOf(temp[1]);
                calc[index] += score[target];
            }
        }
        for(int i=0; i < user.length; i += 2) {
            if(calc[i] < calc[i+1]) { // 지표의 오른쪽 유형이 더 크다면
                answer += user[i+1];
            } else { // 지표의 왼쪽유형이 더 크거나 같을 떄
                answer += user[i];
            }
        }
        return answer;
    }
}