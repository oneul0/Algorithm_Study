import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[] arr, tree;
    static String[] s;
    public static void main(String[] args) throws Exception{
        s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int K = Integer.parseInt(s[2]);

        arr = new long[N+1];
        tree = new long[N*4]; //2의 제곱수의 형태이므로 4를 곱해도 괜찮음

        for(int i = 1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        int a, b;
        for(int i = 0; i<M+K; i++){
            s = br.readLine().split(" ");
            a = Integer.parseInt(s[0]);
            b = Integer.parseInt(s[1]);
            if(a == 1){
                long c = Long.parseLong(s[2]);
                long diff = c-arr[b]; //변경값을 적용하기 위해 차이를 구함
                //변경
                arr[b] = c;
                update(1, N, 1, b, diff);
            }
            else if(a==2){
                int c = Integer.parseInt(s[2]);
                //구간의 합 구하고 출력
                bw.write(sum(1, N, 1, b, c)+"\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
    /**
     *트리 만들기
     * @param start 시작 인덱스
     * @param end 끝 인덱스
     * @param node 세그먼트 트리의 인덱스(무조건 1부터 시작)
     */
    static long init(int start, int end, int node){
        if(start == end) return tree[node] = arr[start];
        int mid = (start+end)/2;
        return tree[node] = init(start, mid, node*2)+init(mid+1, end, node*2+1);
    }

    /**
     * 트리의 노드 값(구간의 합)을 구하는 메서드
     * @param start 시작 인덱스
     * @param end 끝 인덱스
     * @param left 구간 합울 구하고자 하는 범위 시작
     * @param right 구간 합울 구하고자 하는 범위 끝
     */
    static long sum(int start, int end, int node, int left, int right){
        //범위 밖에 있는 경우
        if(left>end || right<start) return 0;
        //범위 안에 있는 경우
        if(left<=start && end<=right) return tree[node];
        // 그렇지 않다면 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
    }

    /**
     * 트리의 노드 값(구간의 합)을 변경하는 메서드
     * @param start 시작 인덱스
     * @param end 끝 인덱스
     * @param index 구간 합을 수정하고자 하는 노드
     * @param diff 수정할 값
     */
    static void update(int start, int end, int node, int index, long diff){
        //범위 밖에 있는 경우
        if(index < start || index > end) return;
        //범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += diff;
        if(start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node*2, index, diff);
        update(mid+1, end, node*2+1, index, diff);
    }

}

//데이터의 개수가 N개일 때 N보다 큰 가장 가까운 N의 제곱수의 2배
