
import java.util.Scanner;

public class Main {
    private static final String INDENT = "____";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        recursiveFunction(n, 0);
        scanner.close();
    }

    private static void recursiveFunction(int n, int depth) {
        // 들여쓰기 출력
        String indent = INDENT.repeat(depth);

        // 재귀함수가 뭔가요? 질문 출력
        System.out.println(indent + "\"재귀함수가 뭔가요?\"");

        if (depth == n) {
            // 재귀함수에 대한 답변 출력
            System.out.println(indent + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
        } else {
            // 이야기의 내용 출력
            System.out.println(indent + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
            System.out.println(indent + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
            System.out.println(indent + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
            recursiveFunction(n, depth + 1);
        }

        // 재귀 호출이 끝난 후 답변 출력
        System.out.println(indent + "라고 답변하였지.");
    }
}