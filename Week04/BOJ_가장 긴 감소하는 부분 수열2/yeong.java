import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for(int i=0;i<N;i++) a[i] = Integer.parseInt(st.nextToken());

        List<Integer> res = new ArrayList<>();
        for(int i=0;i<N;i++){
            int val = a[i];
            if(res.isEmpty()) res.add(val);
            else{
                if(res.get(res.size()-1) < val) res.add(val);
                else{
                    int min = 0;
                    int max = res.size()-1;
                    while(min<max){
                        int mid = (min+max)/2;
                        if(res.get(mid)>=val) max=mid;
                        else min=mid+1;
                    }
                    res.set(max,val);
                }
            }
        }

        System.out.println(res.size());
    }
}