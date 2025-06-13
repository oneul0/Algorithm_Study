#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main(){
    int ans = 0;

    string str;
    cin >> str;
    char c = str[0];
    for(int i = 1; i<str.length(); i++){
        if(c != str[i]){
            ans++;
            c = str[i];
        }
    }

    if(ans > 1){
        if(ans % 2 == 0) ans/=2;
        else {
            ans /= 2;
            ans += 1;
        }
    }
    cout << ans;
    return 0;
}