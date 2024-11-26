import java.io.*;
import java.util.*;
// 0-1 배낭 문제
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] dp;
    static ArrayList<Page> chapters = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]); //일 수
        M = Integer.parseInt(NM[1]); //챕터 수
        dp = new int[N+1];

        for(int i = 0; i<M; i++){
            String[] s = br.readLine().split(" ");
            chapters.add(new Page(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }

        for(int i = 0; i<M; i++){
            //중복 선택 방지를 위해서 뒤에서부터 순회
            for(int j = N; j>=0; j--){
                if(chapters.get(i).days > j) continue;
                dp[j] = Math.max(dp[j], dp[j-chapters.get(i).days]+chapters.get(i).pages);
            }
        }

        bw.write(dp[N] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}


class Page implements Comparable<Page>{
    int days, pages;
    Page(int days, int pages){
        this.days = days;
        this.pages = pages;
    }

    @Override
    public int compareTo(Page o) {
        if(this.days == o.days){
            return  o.pages - this.pages;
        }
        return o.days - this.days;
    }
    /*
    객체의 순서를 정하는 compareTo 메서드
    먼저 온 객체(현재는 o객체)의 순서를 바꿀지 말지 결정함(기준이 됨)
    return 값이 양수이면 두 객체의 순서가 바뀌고
    return 값 <= 0 이면 두 객체의 순서의 변함이 없음

    현재는 내림차순으로 정렬하려고 하고 있으므로 o객체가 this 객체보다 값이 크다면(양수이면)
    두 객체의 순서를 바꿈

    정리
    먼저 온 객체를 기준으로 반환값이 양수이면 두 객체의 순서를 바꿈
    패턴화를 시키자면
    this - o 이면 오름차순
    o - this 이면 내림차순
    * */
}
