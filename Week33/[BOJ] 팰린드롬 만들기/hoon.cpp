#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int alpha[26] = {0};

int main(){
    string str;
    cin >> str;

    for(auto c : str){
        alpha[c - 'A']++;
    }

    int odd = 0;
    char mid = 0;

    // 홀수개인 문자 count
    for(int i = 0; i<26; i++){
        if(alpha[i] % 2 == 1){
            odd++;
            mid = i + 'A';
        }
    }

    if(odd > 1){
        cout << "I'm Sorry Hansoo";
        return 0;
    }

    string left = "", right = "";

    //문자열의 개수 중 절반만 사용하여 left 문자열 생성(홀수개를 찾는 위의 연산에서 남은 문자가 짝수개가 됨을 보장)
    for(int i = 0; i<26; i++){
        int cnt = alpha[i] / 2;
        left += string(cnt, i + 'A');
    }
    right = left;
    reverse(right.begin(), right.end());

    if (odd == 1)
        cout << left << mid << right;
    else
        cout << left << right;

    return 0;
}