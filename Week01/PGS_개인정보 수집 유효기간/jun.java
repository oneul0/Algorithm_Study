import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList<>();
        int todayData = change(today);
        Map<String,Integer> term = new HashMap<>();
        for(String t : terms) {
            term.put(t.substring(0,1), Integer.valueOf(t.substring(2)));
        }
        for(int i=0; i<privacies.length; i++) {
            int now = change(privacies[i]);
            String alpa = privacies[i].substring(11);
            int target = term.get(alpa);
            if(todayData >= now + (target * 28)) {
                list.add(i+1);
            }
        }
        int[] answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public int change(String str) {
        int year = Integer.valueOf(str.substring(0,4));
        int month = Integer.valueOf(str.substring(5,7));
        int day = Integer.valueOf(str.substring(8,10));
        int date = ((year - 2000) * 12 * 28) + (month-1) * 28 + day;
        return date;
    }
}
