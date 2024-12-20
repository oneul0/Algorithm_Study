class Solution {
    // GCD 계산(유클리드 호제법)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 배열의 GCD 구하기
    public static int findGCD(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = gcd(result, arr[i]);
            if (result == 1) { // gcd가 1이면 더 이상 공약수를 구할 수 없다
                break;
            }
        }
        return result;
    }

    public static boolean testGCD(int[] arr, int gcd) {
        for(int tmp: arr){
            if(tmp % gcd == 0) return false;
        }
        return true;
    }


    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // arrayA의 GCD 구하기
        int gcdA = findGCD(arrayA);

        // arrayB의 GCD 구하기
        int gcdB = findGCD(arrayB);

        // 각 gcd 구한 값으로 다른 배열 값을 나눌 수 있는 지 테스트 해보쟈
        boolean testA = testGCD(arrayA, gcdB);
        boolean testB = testGCD(arrayB, gcdA);



        if (gcdA == gcdB || !testA && !testB) {
            answer = 0;
        }else{
            answer = gcdA > gcdB ? gcdA : gcdB;
        }

        return answer;
    }
}