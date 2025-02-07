import java.util.*;
import java.io.*;

// idea
/*
 * 첫번째 윈도우(길이 p)에 대한 문자 개수 계산
 * start를 증가시킬 때 빠지는 문자와 새로 추가되는 문자만 반영하여 문자 개수를 업데이트
 * */


public class Main {
    // 문자 개수 업데이트(delta가 1이면 추가 -1이면 제거)
    public static void updateCount(char ch, int[] count, int delta){
        if(ch == 'A'){count[0] += delta;}
        else if(ch == 'C'){count[1] += delta;}
        else if(ch == 'G'){count[2] += delta;}
        else if(ch == 'T'){count[3] += delta;}
    }

    // 현재 윈도우가 조건을 만족하는지 확인
    private static boolean isValid(int[] minCount, int[] currentCount){
        return currentCount[0]>=minCount[0] && currentCount[1] >= minCount[1] && currentCount[2] >= minCount[2] && currentCount[3] >= minCount[3];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int S = Integer.parseInt(input1[0]);
        int P = Integer.parseInt(input1[1]);

        char[] DNA = br.readLine().toCharArray();

        String[] input2 = br.readLine().split(" ");
        int min_num[] = new int[4];
        for (int i = 0; i < 4; i++) {
            min_num[i] = Integer.parseInt(input2[i]); // {‘A’, ‘C’, ‘G’, ‘T’}
        }

        int curr_count[] = new int[4]; // 현재 윈도우 내의 A, C, G, T 개수

        int answer = 0;

        // 초기 윈도우 (첫 p 개 이내의 문자 개수 세기)
        for(int i=0; i<P; i++){
            updateCount(DNA[i], curr_count, 1);
        }

        // 첫 문자열이 조건을 만족하는지
        if(isValid(min_num, curr_count)) answer++;

        // 슬라이드 윈도우 이동
        for(int start=1; start <= S-P; start++){
            int end = start + P -1;
            // 이전 윈도우의 맨 앞의 문자를 제거
            updateCount(DNA[start-1], curr_count, -1);

            // 새로운 문자열 추가
            updateCount(DNA[end], curr_count, 1);

            // 조건의 만족여부 확인
            if(isValid(min_num, curr_count)) answer++;
        }

        System.out.println(answer);

    }
}
