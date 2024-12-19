class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int minA = findMin(arrayA);
        int minB = findMin(arrayB);
        int num = minA > minB ? minA : minB;
        while(num > 0) {
            if (minA % num != 0 && minB % num != 0) {
                num--;
                continue;
            } else if (minA % num == 0 && minB % num == 0) {
                if(check(arrayA, arrayB, num)) {
                    break;
                } else if (check(arrayB, arrayA, num)) {
                    break;
                } else {
                    num--;
                    continue;
                }
            } else if (minA % num == 0) {
                if(check(arrayA, arrayB, num)) {
                    break;
                } else {
                    num--;
                    continue;
                }
            } else {
                if(check(arrayB, arrayA, num)) {
                    break;
                } else {
                    num--;
                    continue;
                }
            }
        }
        return num;
    }
    
    public int findMin(int[] array) {
        int minValue = Integer.MAX_VALUE;
        for (int i=0; i<array.length; i++) {
            if (minValue > array[i]) {
                minValue = array[i];
            }
        }
        return minValue;
    }
    public boolean check(int[] passArray, int[] failArray, int n) {
        boolean flag = false;
        for (int i=0; i<passArray.length; i++) {
            if((passArray[i]%n) != 0) {
                return false;
            }
            if((failArray[i])%n == 0) {
                flag = true;
            }
        }
        if (flag) {
            return false;
        } else { 
            return true;
        }
    }
}
