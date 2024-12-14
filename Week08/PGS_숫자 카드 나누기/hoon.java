import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 각 집합의 최대 공약수 중 서로의 집합의 요소를 나눌 수 없는 것
        int answer = 0;
        int a = getGcd(arrayA); //arrayA의 최대공약수
        int b = getGcd(arrayB); //arrayB의 최대공약수

        boolean fA = a != 1 && Arrays.stream(arrayB).allMatch(i -> i%a != 0); //a로 arrayB 나눌 수 있는지
        boolean fB = b != 1 && Arrays.stream(arrayA).allMatch(i -> i%b != 0); //b로 arrayA 나눌 수 있는지


        if(fA && fB) return Math.max(a,b);
        if(fA) return a;
        if(fB) return b;

        return answer;
    }

    int getGcd(int[] arr){
        int result = arr[0];
        for(int i = 1; i<arr.length; i++){
            result = gcd(result, arr[i]);
            if(result == 1) break;
        }
        return result;
    }

    int gcd(int a, int b){
        while(b!=0){
            int c = b;
            b = a%b;
            a = c;
        }
        return a;
    }
}