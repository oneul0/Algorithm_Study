import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        Deque<Integer> removed = new ArrayDeque<>();
        int tableSize = n;
        for(String c: cmd) {
            String[] command = c.split(" ");
            if(command[0].equals("U")) k-=Integer.parseInt(command[1]);
            else if(command[0].equals("D")) k+=Integer.parseInt(command[1]);
            else if(command[0].equals("C")){
                removed.push(k);
                tableSize--;
                if(k==tableSize) k--;
            }
            else if(command[0].equals("Z")) {
                if(removed.pop()<=k) k++;
                tableSize++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<tableSize; i++){
            sb.append("O");
        }
        while(!removed.isEmpty()){
            sb.insert(removed.pop(), "X");
        }

        return sb.toString();
    }
}