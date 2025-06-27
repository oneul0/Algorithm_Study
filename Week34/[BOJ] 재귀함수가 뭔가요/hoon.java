import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String start = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
    static String repeat1 = "\"재귀함수가 뭔가요?\"\n";
    static String repeat2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    static String repeat3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    static String repeat4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    static String last = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static String end = "라고 답변하였지.\n";
    static String dash = "____";
    static int N;
    static StringBuilder str = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        str.append(start);
        dfs(0);
        bw.write(str.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static void dfs(int depth){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<depth; i++){
            sb.append(dash);
        }
        String s = sb.toString();
        if(depth == N){
            str.append(s).append(repeat1)
                .append(s).append(last)
                .append(s).append(end);
            return;
        }
        str.append(s).append(repeat1)
            .append(s).append(repeat2)
            .append(s).append(repeat3)
            .append(s).append(repeat4);
        dfs(depth+1);
        str.append(s).append(end);
    }

}