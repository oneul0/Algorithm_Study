
import java.util.*;

/*
 * 개인 정보별 파기 날짜를 먼저 계산한다.
 * 이후, 현재 날짜와 비교한다.
 *
 * 필요한 것
 * 1. 날짜 비교 함수( 현재 날짜를 지났으면 배열에 추가 )
 * 2. 날짜 계산 함수
 *
 * 중요한 것 문자열 분리
 * */

class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answerList = new ArrayList<>();

        // 오늘 날짜 포맷(정수형으로 비교하기 쉽도록!)
        String[] today_part = today.split("\\.");
        int[] today_format = new int[today_part.length];
        for (int i = 0; i < today_part.length; i++) {
            today_format[i] = Integer.parseInt(today_part[i]);
        }

        List<String[]> user_info = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String[] format = {String.valueOf(i+1), parts[0], parts[1]};
            user_info.add(format);
        }

        Map<String, Integer> term_format = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] parts = terms[i].split(" ");
            term_format.put(parts[0], Integer.parseInt(parts[1]));
        }


        // 만기 기한 날짜 계산 리스트
        List<int[]> end_date = new ArrayList<>();
        end_date = cal_end_date(term_format, user_info);


        for (int i = 0; i < privacies.length; i++) {
            if(isEnd(today_format, end_date.get(i))){

                answerList.add(i+1);
            }
        }
        return answerList.stream().mapToInt(i -> i).toArray();
    }

    private boolean isEnd(int[] today , int[] user_info) {
        // 연도 비교

        if(today[0] > user_info[0]) return true; // 연도가 더 많을 경우
        if(today[0] == user_info[0]) {
            if(today[1] > user_info[1]) return true;
            if(today[1] == user_info[1]) {
                return today[2] >= user_info[2];
            }
        }

        return false;
    }

    private List<int[]> cal_end_date(Map<String, Integer> termFormat, List<String[]> userInfo) {

        List<int[]> end_date = new ArrayList<>();

        for (int i = 0; i < userInfo.size(); i++) {
            String category = userInfo.get(i)[2];
            int term = termFormat.get(category);

            String[] day_tmp = userInfo.get(i)[1].split("\\.");
            int[] end = cal_date(day_tmp, term);
            end_date.add(end);
        }
        return end_date;
    }


    private int[] cal_date(String[] date, int term) {
        int month = Integer.parseInt(date[1])+ term;
        int year = Integer.parseInt(date[0]);

        if(month >12){
            year += (month - 1) / 12;
            month = (month - 1) % 12 +1;
        }

        int[] end_day = {year, month, Integer.parseInt(date[2])};
        return end_day;
    }
}