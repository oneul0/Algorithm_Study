#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    long long answer = 0;
    // 최대한 works안의 큰 수를 없에는것이 관건이다.
    // 제곱했을 때 큰 수는 더 커지기 때문,,
    // (야근은 피로도가 제곱으로 쌓이는구나..ㅜㅠ!)
    // works안의 녀석들을 maxHeap에 넣고, 맨 앞의 녀석을 -1하고 enqueue
    // 이를 n만큼 반복한 후, maxHeap안의 모든 원소를 꺼내며
    // 각 원소마다 제곱한 후 더하면 최소 야근 지수를 구할 수 있다.
    
    priority_queue<int> maxHeap;
    
    for(int i = 0; i<works.size(); i++) {
        maxHeap.push(works[i]);
    }
    
    while (n > 0 && maxHeap.size() > 0) {
        int work = maxHeap.top();
        if (work == 0 ) { break; }
        maxHeap.pop();
        work--;
        maxHeap.push(work);
        n--;
    }
    
    while (maxHeap.size() > 0) {
        int work = maxHeap.top();
        maxHeap.pop();
        answer += work*work;
    }
    
    return answer;
}