import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer> kakao = new HashMap<>();
        Character[] alpa = new Character[] {'R','T','C','F','J','M','A','N'};
        for (char c : alpa) {
            kakao.put(c,0);
        }
        for (int i=0; i<survey.length; i++) {
            if (choices[i] == 4) {
                continue;
            } else if (choices[i] < 4) {
                char c = survey[i].charAt(0);
                kakao.put(c,(kakao.get(c) + (4-choices[i])));
            } else {
                char c = survey[i].charAt(1);
                kakao.put(c,(kakao.get(c) + (choices[i]-4)));
            }
        }
        for (int i=0; i<alpa.length; i+=2) {
            if (kakao.get(alpa[i]) < kakao.get(alpa[i+1])) {
                answer += alpa[i+1];
            } else {
                answer += alpa[i];
            }
        }
        return answer;
    }
}
