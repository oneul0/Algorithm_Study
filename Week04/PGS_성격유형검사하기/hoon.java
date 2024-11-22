import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> mbti = new HashMap<>();

        char[] type = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        for(int i = 0; i<type.length; i++){
            mbti.put(type[i], 0);
        }

        for(int i = 0; i<survey.length; i++){
            int score = choices[i];
            //전자
            if(score < 4){
                char tmp = survey[i].charAt(0);
                mbti.put(tmp, mbti.get(tmp)+4-score);
            }
            //후자
            else if(score > 4){
                char tmp = survey[i].charAt(1);
                mbti.put(tmp, mbti.get(tmp)+score-4);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<type.length; i+=2){
            if(mbti.get(type[i]) == mbti.get(type[i+1]))
                sb.append(type[i] < type[i+1] ? type[i] : type[i+1]);

            else
                sb.append(mbti.get(type[i]) > mbti.get(type[i+1]) ? type[i] : type[i+1]);
        }

        return sb.toString();
    }
}