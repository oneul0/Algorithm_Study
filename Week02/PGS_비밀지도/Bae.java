import java.util.* ;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
//      2진법 ? 9 = 01001 9%2 1 4%2 0 2%2 0 1%2 1
//      n=5 0 -> 2^4 보다 작은 수가 나오면 맨 앞자리 처리가 어떻게 되는걸까
//      9/2 4 4/2 2 2/2 1 1/2 0
//      하나만 벽이여도 진짜 벽, 모두 공백이여야 공백 
        
//      arr값/2를 계속하니 배열이 뒤집혀진다. 이유는 모르겠다.
        
        String[] answer = new String[n];
        
        for(int i = 0; i< n; i++ ) {
            StringBuilder sb = new StringBuilder();
            int a = arr1[i];
            int b = arr2[i];
            for(int j=0; j<n; j++)   {
                if(a%2 == 1 || b%2 == 1) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
                a/=2;
                b/=2;
            }
            String temp = sb.reverse().toString();
            answer[i] = temp;
        }
        

        return answer;
    }
}
