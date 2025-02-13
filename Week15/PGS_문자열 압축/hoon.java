import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int i = 1; i<= s.length(); i++){
            answer = Math.min(answer, compress(s,i));
        }
        return answer;
    }

    int compress(String text, int length){
        //압축 단위 단어 저장 리스트
        List<String> words = new ArrayList<>();
        for(int i = 0; i<text.length(); i+=length){
            //length에 따른 자르는 문자 단위
            words.add(text.substring(i, Math.min(text.length(), i+length)));
        }
        StringBuilder compressedText = new StringBuilder();
        String prevWord = "";
        int cnt = 0;
        for(String word: words){
            if(word.equals(prevWord)){
                cnt++;
            }
            else{
                if(cnt>1){
                    compressedText.append(cnt+"");
                }
                compressedText.append(prevWord);
                prevWord = word;
                cnt = 1;
            }
        }
        if(cnt>1){
            compressedText.append(cnt+"");
        }
        compressedText.append(prevWord);
        return compressedText.toString().length();

    }
}