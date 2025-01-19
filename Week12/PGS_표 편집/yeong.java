import java.util.*;
import java.io.*;

class Solution {
    static class Node {
        Node prev = null;
        Node next = null;
        boolean isDelete;
    }

    public static String solution(int n, int k, String[] cmd) {
        Node[] nodeArr = new Node[1000001];

        nodeArr[0]=new Node();
        for (int i = 1; i < n; i++) {
            nodeArr[i]=new Node();
            nodeArr[i].prev=nodeArr[i-1];
            nodeArr[i-1].next=nodeArr[i];
        }

        Stack<Node> delete= new Stack<>();
        Node now =nodeArr[k];

        for(String query : cmd){
            char command = query.charAt(0);

            // 위로 이동
            if(command == 'U'){
                int cnt = Integer.parseInt(query.substring(2));
                for(int i=0;i<cnt;i++){
                    now=now.prev;
                }
            }
            // 아래로 이동
            else if(command == 'D') {
                int cnt = Integer.parseInt(query.substring(2));
                for(int i=0;i<cnt;i++){
                    now=now.next;
                }
            }
            // 현재 위치 삭제
            else if(command =='C'){
                now.isDelete=true;
                delete.push(now);

                Node prev=now.prev;
                Node next=now.next;

                if(prev!=null){
                    prev.next=next;
                }
                if(next!=null){
                    next.prev=prev;
                    now=next;
                }
                else{
                    now=prev;
                }
            }
            // 최근 삭제된 행 되돌리기
            else{
                Node node = delete.pop();
                Node prev=node.prev;
                Node next=node.next;

                node.isDelete=false;
                if(prev!=null){
                    prev.next=node;
                }
                if(next!=null){
                    next.prev=node;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(nodeArr[i].isDelete) sb.append('X');
            else sb.append('O');
        }

        return sb.toString();
    }
}