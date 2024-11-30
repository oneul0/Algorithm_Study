import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] lcs = new int[1001][1001];

        //LCS(최장 공통 부분수열) 알고리즘
        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                //만약 문자가 같으면
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    //대각선 위 방향(이전까지의 가장 긴 부분 수열의 길이)에 추가하여 길이를 더함
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                }
                else{
                    // s1과 s2 문자열 중 더 긴 부분 수열의 길이를 가져옴
                    // 예를 들어, 상(i-1) 방향의 길이를 가져오는 것은 s1의 이전 문자와 s2의 전체 문자를 고려
                    // 좌(j-1) 방향의 길이를 가져오는 것은 s1의 전체 문자와 s2의 이전 문자를 고려
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        bw.write(lcs[s1.length()][s2.length()]+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
