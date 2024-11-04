class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i=0; i<n; i++) {
            answer[i] = bin(arr1[i], arr2[i], n);
        }
        return answer;
    }
    public String bin(int a, int b, int n) {
        String binary = "";
        for(int i=0; i<n; i++) {
            if ((a % 2 == 1) || (b % 2 == 1)) {
                binary = "#" + binary;
            } else {
                binary = " " + binary;
            }
            a /= 2;
            b /= 2;
        }
        return binary;
    }
}
