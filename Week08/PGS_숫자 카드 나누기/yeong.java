import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int n = arrayA.length;
        int m = arrayB.length;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        // 각 배열의 최대공약수 구하기
        int x = arrayA[0];

        for (int i = 1; i < n; i++) {
            x = gcd(arrayA[i], x);
        }

        int y = arrayB[0];

        for (int i = 1; i < m; i++) {
            y = gcd(arrayB[i], y);
        }

        // 다른 배열의 원소 중 하나라도 나눌 수 없는지 확인
        if (!divided(arrayB, x)) {
            answer = Math.max(answer, x); // 나눌 수 없을 때 최댓값 비교해서 갱신
        }

        if (!divided(arrayA, y)) {
            answer = Math.max(answer, y);
        }

        return answer;
    }

    // 유클리드 호제법(단, x > y) 약수구하기
    public int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }


    public boolean divided(int[] arr, int a) { // 약수로 해당 배열 내 원소들이 나눠지는지 구하기
        boolean divided = false;

        if (a <= 1) {
            return true;
        }

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (arr[i] % a == 0) {
                divided = true;
            }
        }

        return divided;
    }
}