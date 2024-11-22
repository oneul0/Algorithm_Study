/*

각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형
두 성격 점수가 같으면 사전 순으로 빠른 성격 유형
sury RT or TR 둘다 가능 -> 순서 신경써야한다
예시를 보면 순서도 신경을 써야한다.
RT 에서 매우 동의 -> R에 매우 동의
TR 에서 매우 동의 -> T에서 매우 동의
평가는 1~7
매우 비동의 -> 매우 동의 
survey 길이 = choices 길이
choices 정해져있다


*/



import java.util.*;


class Solution {
        static String[] survey;
        static int[] choices;
        static int[][] totalScore = new int[4][2];
    public String solution(String[] survey, int[] choices) {
        int n = survey.length;
//      점수 판을 따로 만들어서 점수를 매긴 후 불러오자
//      점수판은 2차원 배열이지만 같은 점수면 사전 순을 따르기에 사전순으로
        
//      R,T / C,F / J,M / A,N
        
//      choices에도 접근해야하기 때문에 일반 for문으로
//      1~3 앞에 점수 + / 4~7 뒤에 점수 + 
        for(int i=0; i<n; i++) {
            if(survey[i].equals("RT")) {
                getScore(0,true,choices[i]);
            } else if(survey[i].equals("TR")) {
                getScore(0,false,choices[i]);
            } else if(survey[i].equals("CF")) {
                getScore(1,true,choices[i]);
            } else if(survey[i].equals("FC")) {
                getScore(1,false,choices[i]);
            } else if(survey[i].equals("JM")) {
                getScore(2,true,choices[i]);
            } else if(survey[i].equals("MJ")) {
                getScore(2,false,choices[i]);
            } else if(survey[i].equals("AN")) {
                getScore(3,true,choices[i]);
            } else {
                getScore(3,false,choices[i]);
            }
        }
        
        
        StringBuilder sb = new StringBuilder();
        result(totalScore, sb);
        String answer = sb.toString();
        return answer;
    }
    
    public void getScore(int num, boolean check, int score) {
        if(check) {
            if(score< 4) {
                totalScore[num][0]+=4-score;
            } else {
                totalScore[num][1]+=score-4;
            }
        } else {
            if(score< 4) {
                totalScore[num][1]+=4-score;
            } else {
                totalScore[num][0]+=score-4;
            }
        }
    }
    
    public void result(int[][] totalScore, StringBuilder sb) {
        
        if(totalScore[0][1]>totalScore[0][0]) {
            sb.append("T");
        } else {
            sb.append("R");
        }
        if(totalScore[1][1]>totalScore[1][0]) {
            sb.append("F");
        } else {
            sb.append("C");
        }
        if(totalScore[2][1]>totalScore[2][0]) {
            sb.append("M");
        } else {
            sb.append("J");
        }
        if(totalScore[3][1]>totalScore[3][0]) {
            sb.append("N");
        } else {
            sb.append("A");
        }
    } 
}
