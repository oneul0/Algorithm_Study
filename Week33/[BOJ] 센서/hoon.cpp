#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

    int N, K;
vector<int> v;
int main(){
    cin >> N >> K;
    if(K >= N){
        cout << 0;
        return 0;
    }
    v.resize(N, 0);
    for(int i = 0; i<N; i++){
        cin >> v[i];
    }

    sort(v.begin(), v.end());

    vector<int> dist(N-1, 0);
    for(int i = 0; i<N-1; i++){
        dist[i] = v[i+1] - v[i];
    }
    sort(dist.begin(), dist.end(), [](int a, int b){return a>b;});
    int ans = 0;
    for(int i = K-1; i < dist.size(); i++){
        ans += dist[i];
    }
    cout << ans;

    return 0;
}