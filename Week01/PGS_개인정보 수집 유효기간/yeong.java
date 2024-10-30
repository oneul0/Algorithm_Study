import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st;

        //약관이름, 약관유효기간 저장
        Map<String, Integer> term = new HashMap<>();
        for(int i=0;i<terms.length;i++){
            st = new StringTokenizer(terms[i]);
            term.put(st.nextToken(), Integer.parseInt(st.nextToken())*28);
        }

        //today
        int days = getDays(today);

        List<Integer> list = new ArrayList<>();
        int idx = 1;
        for(String privacy:privacies){
            st = new StringTokenizer(privacy," ");
            int pdays = getDays(st.nextToken());

            //파기해야하는 개인정보 list에 add
            if(days-pdays >= term.get(st.nextToken())){
                list.add(idx);
            }

            idx++;
        }

        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }

        return answer;
    }

    public int getDays(String date){
        StringTokenizer st = new StringTokenizer(date,".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int days = (year-1)*12*28 + (month-1)*28 + day;
        return days;
    }
}