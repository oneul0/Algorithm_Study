import java.io.*;
import java.util.*;

public class Main {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

/*
첫 번째 -> 무조건 첫 번째 수
두 번째 -> 작은 수
세 번째 -> 중간 수
네 번째 -> 중간에서 작은 수

정수의 개수 n개
1<= n <= 100,000

우선순위 큐를 사용해야 될 거 같지만 큐는 인덱스 보장이 안된다
큐에 새로운 정수를 넣을 때마다 정렬이 되어 순서에 대해 알지만 이에 대해 매번 가운데 수를 찾아내야한다
1. 매번 꺼내고 다시 넣고를 반복한다 -> 시간복잡도 ??, q를 두 개 사용해야한다.
i<q.size()/2 -> 1 1/2 -> 0 x, 2-> 2/2 -> 1 -> 0 ok, 3 -> 3/2 -> 1 x, 4 -> 4/2 -> 2 1 ok, 5/2 -> 2

짝수, 홀수에 따라 for문 조건을 다르게 해보자
q.size%2 == 0 -> n/2 < 까지하면 중간의 작은수로 적용가능
q.size%2 == 1 -> n/2 <= 같게까지하면 중앙값 보장

q.size() == 1 만 예외케이스

1차
for문 내부에 q.size()로 길이를 설정하여 for문이 돌때마다 q.size()가 변경되어 비정상적으로 작동

2차
bw.write로 바로 중간값을 넣으니 마지막 공백처리가 불가능하다. 따로 담아서 bw에 넣자

-> 시간초과

2.
결국 큐는 계속해서 갱신된다 -> 그때마다의 중간값을 알아내는 방법
우선순위 큐를 사용하여 정렬은 상관 x -> 결국 큐에 넣는 for문 한번 반복할 때마다 계속 갱신이 자동으로 되어야한다
원소를 넣고 빼는 과정은 10^4에서 거의 불가능
차라리 넣고 빼는거보다 복사해서 get하는게 or 정렬을 차라리 list에 넣어서 따로하고 get하는게 시간이 나을까
-> collections.sort도 시간초과

3.
결국 중앙 값을 비교하려면
size가 3이상일때
3개or 2개 만 미리 기억해서 계속 갱신하는 식으로 처리하는게 좋아보인다
1,5,2 -> [1,2,5] -> 예외 케이스가 뭔지 모르겠다

4.
결국 짝수 홀수일 때 경우가 다르다.
우선순위 큐를 쓰는 이유는 중앙에 대해 알기위해서
결국 중앙에 대한 값이 계속 갱신되어야 한다
위치 지정하여 갱신 -> x
인덱스에 구분되지 않고 항상 적용되는 조건이여야 한다
1 -> 중앙 값 1, 5는 1보다 크다 -> 짝수 -> 중앙 값 1 , 2-> 1보다 크다 -> 중앙값보다 크다 -> 중앙값 2 ,
10 -> 짝수 중앙값보다 크다 -> 중앙값 2, -99 -> 홀수 중앙값보다 작다 -> 중앙값 2 , 7 -> 홀수 중앙값보다 크다 ->
중앙값 기준으로 좌 우? 결국 좌측에서 제일 큰 숫자 -> 짝수 일때, 홀 수 일때도 두 개로 나누어서 중앙보다 크냐 작냐 로 반갈하면

*/




//      중앙으로 부터 <- mid -> 이럴려면 left는 역순해야 peek할 때 중앙값
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> left = new PriorityQueue<>((a,b)-> b-a);
        Queue<Integer> right = new PriorityQueue<>();
        List<Integer> list = new ArrayList<>();


        for(int i = 0; i < n; i++){
//           홀수면 좌측이 더 길다 3 [2,1]
            int num = Integer.parseInt(br.readLine());
//          기본적으로 left에 중앙 값 존재 -> left.peek()랑 비교 + 개수도 생각해야한다
//          처음에 사이즈로만 비교하면 분류가 안된다
//          일단 left에 넣고 계속 개수 갱신해주는게 좋을 거 같다 -> 왜인지 모르겠지만 right가 더 커지는 경우가 발생해 이에 대해 대비를 못한다
//          결국 설정을 left는 중앙값이랑 같거나 중앙 값과 같은 값들을 넣으니 넣을때부터 검사해서 넣자.
//          나머지 큰거는 right에 넣자
            if(!left.isEmpty() && left.peek() >= num ) {
                left.add(num);
            } else {
                right.add(num);
            }

//          큐의 사이즈 관련하여 while문을 돌릴 경우, 큐 사이즈가 계속해서 변경되어서 그냥 매번 맞춰주는게 맞다
//          어짜피 하나씩 계속 확인을 하기 때문에 굳이 while문으로 전체를 다 나중에 옮기는 로직을 짜도 결국 계속 검사를 하여 하나씩 옮겨진다
//          같은 경우는 애초에 안옮겨도 되니까 제외
//          홀수면 좌측이 더 길다 
            if(left.size() > right.size()+1){
                right.add(left.remove());
            } else if (right.size() > left.size()){
                left.add(right.remove());
            }


            list.add(left.peek());
        }

        for(int i = 0; i < list.size(); i++){
            bw.write(String.valueOf(list.get(i)));
            if(i != list.size()-1){
                bw.newLine();
            }
        }



        bw.flush();
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
