#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(pair<int, int> a, pair<int, int> b) {
    if (a.first == b.first) {
        return a.second < b.second;
    }
    return a.first < b.first;
}

int main(){
    cin.tie(nullptr) -> ios_base::sync_with_stdio(false);
    int n;
    cin >> n;

    vector<pair<int, int>> v(n);
    for (int i = 0; i < n; i++) {
        int a, b;
        cin >> a >> b;
        v[i] = make_pair(a, b);
    }

    sort(v.begin(), v.end(), compare);

    long long ans = 0;
    int start = -1000000001;
    int end = -1000000001;

    for (auto p : v) {
        if(end < p.first){
            ans += end - start;
            start = p.first;
        }
        if(end < p.second){
            end = p.second;
        }
    }
    ans += end-start;

    cout << ans;

    return 0;
}
