#include <iostream>
#include <vector>

using namespace std;

static int MOD = 10007;

vector<int> dp;
int n;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);

    cin >> n;
    dp.resize(n+1, 0);
    for(int i = 0; i<3; i++){
        dp[i] = (i%MOD);
    }

    for(int i = 3; i<=n; i++){
        dp[i] = (dp[i-2] + dp[i-1])%MOD;
    }
    
    cout << dp[n];
    return 0;
}