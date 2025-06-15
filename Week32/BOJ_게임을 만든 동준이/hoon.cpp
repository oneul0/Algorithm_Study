#include <iostream>
#include <vector>
using namespace std;

int main(){
    int N;
    cin >> N;

    vector<int> v(N);
    for(int i = 0; i<N; i++){
        cin >> v[i];
    }

    int ans = 0;
    for(int i = N-1; i>=1; i--){
        int tmp = v[i] - 1;
        if(v[i] > v[i-1]) continue;
        ans += (v[i-1] - tmp);
        v[i-1] = tmp;
    }
    cout << ans;


    return 0;
}