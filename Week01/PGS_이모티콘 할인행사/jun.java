import java.util.*;
class Solution {
    public int[] ratio;
    public int end;
    public int[] solution(int[][] users, int[] emoticons) { // users의 길이 <= 100, 최대 4**7
        ratio = new int[] {10, 20, 30, 40};
        end = emoticons.length;
        int[] temp = new int[end];
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? 
                                Integer.compare(b[0],a[0]) : Integer.compare(b[1], a[1]));
        
        sale(temp,0,users,emoticons,pq);
        int[] answer = pq.remove();
        return answer;
    }
    
    public void sale(int[] temp, int now, int[][] users, int[] emoticons, Queue<int[]> pq) {
        if (now == end) {
            int price = 0;
            int man = 0;
            for(int user=0; user<users.length; user++) {
                int sum = 0;
                for(int emo=0; emo<end; emo++) {
                    int discounted = emoticons[emo] * (100 - temp[emo]) / 100;
                    if (temp[emo] >= users[user][0]) { 
                        sum += discounted; 
                    }
                }
                if(sum >= users[user][1]) {
                    man++;
                } else {
                    price += sum;
                }
            }
            pq.add(new int[] {man, price});
            return;
        }
        
        for (int i=0; i<4; i++) {
            temp[now] = ratio[i];
            sale(temp, now+1, users, emoticons, pq);
        }
    }
}
