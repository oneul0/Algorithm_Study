/*
철수 영희가 숫자가 하나씩 적힌 카드들을 절반씩 나누어 가진다
-> 두 조건중 하나를 만족하는 가장 큰 양의 정수
1. 철수가 가진 카드들을 모두 나눌 수 있고, 영희는 하나도 못나누는 양의 정수
2. 영희가 가진 카드들을 모두 나눌 수 있고, 철수는 하나도 못나누는

기본적으로 카드는 반반 나누어서 주어진다
조건 만족 x -> 0 리턴건 만족 x -> 0 리턴
arrayA.length = arrayB.length <= 500,000
arrayA와 arrayB에 중복된 원소가 존재가능
-> 중복된 원소가 존재한다면 기본적으로 return 0

시간복잡도 상 한번 순회할 때 바로 확인 가능해야한다
1. 먼저 중복 원소가 있는가 ? 확인
2. 배열의 원소들을 나눌 수 있는 공통된 약수가 잇는지 존재하는지 확인
2-1 한 배열의 공통 약수 확인 -> 그 약수가 반대 배열에서는 모두 적용되지 않는가 확인
2-2 반대도 동일하게 확인

*/

/*
1차 제출 70%
특수한 경우에 대해서 처리를 못하는 거 같다.

어떠한 경우인지 확인을 못하겠다 -> 최소값의 약수로 해버린게 원인같지만 왜인지는 모르겠다 -> 최대공약수 구해서
최대 공약수의 약수를 찾아서 해보자
무슨 예외케이스인지 모르겠다. 최솟값의 약수를 계산하나 최대공약수의 약수를 계산하나 로직상 순서의 문제이지 무슨 차이일까 ?

: "최솟값의 약수"를 기반으로 하는 방법이 효율적이지 않다는 점은 이해되었지만, 왜 통과하지 못하는 특정 케이스가 존재하는지에 대한 근본적인 이유를 파악하고자 하시는 거죠.

결론적으로, 최솟값의 약수를 사용하는 방법이 모든 경우를 통과하지 못하는 이유는 "최솟값이 배열의 전체 최대공약수를 정확히 반영하지 못할 가능성" 때문입니다. 아래에서 구체적으로 분석하겠습니다.

"최솟값의 약수"를 사용하는 방식이 통과하지 못하는 반례는, 최솟값이 배열의 공통 구조를 반영하지 못하는 경우입니다. 최대공약수를 사용하는 방식은 공통 약수를 명확히 반영하며, 조건을 정확히 만족하거나 불만족하는지를 보장합니다.

이 차이는 효율성뿐만 아니라 정확성의 문제로 이어질 수 있습니다. 반드시 최대공약수를 사용해야 통과하지 못하는 케이스를 피할 수 있습니다.

*/


import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        
        int n = arrayA.length;
//      1. 중복 배열 확인
//      이중for문 -> 10^10 -> x
//      list의 contains O(n) x , set의 contains O(1) -> 시도
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        
        for(int i=0; i<n; i++) {
            setA.add(arrayA[i]);
            setB.add(arrayB[i]);
        }
        
        for(int a : setA) {
            if(setB.contains(a)) {
                return 0;
            }
        }
        
        System.out.println("1번 조건 완료");
        
//      2-1. 배열의 원소들을 나눌 수 있는 공통된 약수가 잇는지 존재하는지 확인
        
        
        int maxCommonMeausreA = arrayA[0];
        int maxCommonMeausreB = arrayB[0];
        
        for(int i = 1; i < n; i++) {
            maxCommonMeausreA = findCommonMeasure(maxCommonMeausreA, arrayA[i]);
            maxCommonMeausreB = findCommonMeasure(maxCommonMeausreB, arrayB[i]);
        }
        
        
        int[] measuresA = findMeasures(maxCommonMeausreA);
        int[] measuresB = findMeasures(maxCommonMeausreB);
        
        int maxMeasureA = 0;
        int maxMeasureB = 0;
        
//     2-2 다른 배열에서는 나누어지는지 확인
//     둘 중 0이 아닌 경우에 다른 쪽 배열을 확인한다.
        
        for(int measure : measuresA) {
            boolean checkB = false;
            for(int i=0; i<n; i++) {
                if(arrayB[i]%measure == 0) {
                    checkB = true;
                    break;
                }
            }
            if(!checkB) {
                maxMeasureA = Math.max(maxMeasureA,measure);
            }
        }
        
        for(int measure : measuresB) {
            boolean checkA = false;
            for(int i=0; i<n; i++) {
                if(arrayA[i]%measure == 0) {
                    checkA = true;
                    break;
                }
            }
            if(!checkA) {
                maxMeasureB = Math.max(maxMeasureB,measure);
            }
        }
        
        
        if(maxMeasureA == 1 && maxMeasureB == 1) {
            return 0;
        }
        
        return Math.max(maxMeasureA,maxMeasureB);
    }
    
    public int findCommonMeasure(int a, int b) {
        while( b != 0) {
            int temp = a%b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    public int[] findMeasures (int num) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 1; i<=num; i++) {
            if(num%i == 0) {
                list.add(i);        
            }
        }
        
        int[] measures = new int[list.size()];
        
        for(int i=0; i<list.size(); i++) {
            measures[i] = list.get(i);
        }
        
        return measures;
    }
}
