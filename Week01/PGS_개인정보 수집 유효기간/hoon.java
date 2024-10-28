import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Solution {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> tmp = new ArrayList<>();
        Map<String, Integer> terms_map = new HashMap<>();

        //today
        LocalDate today_date = LocalDate.parse(today, dtf);

        //약정 기간 저장
        for(String term : terms){
            String[] trm = splitSpace(term);
            terms_map.put(trm[0], Integer.parseInt(trm[1]));
        }

        //privacy에 terms만큼 더하기
        for(int i = 0; i<privacies.length; i++){
            String[] date_term = splitSpace(privacies[i]);
            LocalDate expired_date = LocalDate.parse(date_term[0], dtf).plusMonths(terms_map.get(date_term[1]));
            //날짜가 도래했거나 지났으면 인덱스 추가
            if(today_date.equals(expired_date) || today_date.isAfter(expired_date)){
                tmp.add(i);
            }
        }
        int[] answer = new int[tmp.size()];
        for(int i = 0; i<tmp.size(); i++) answer[i] = tmp.get(i)+1;
        return answer;
    }

    // 공백을 기준으로 나누는 함수
    public String[] splitSpace(String origin_str){
        String[] splited = origin_str.split(" ");
        return splited;
    }
}