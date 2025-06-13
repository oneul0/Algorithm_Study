#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
	vector<int_fast16_t> arr1;
	vector<int_fast16_t> arr2;
	int n;
	cin >> n;
	for(int i = 0; i<n; i++){
		int_fast16_t tmp;
		cin >> tmp;
		arr1.push_back(tmp);
	}
	for(int i = 0; i<n; i++){
		int_fast16_t tmp;
		cin >> tmp;
		arr2.push_back(tmp);
	}
	sort(arr1.begin(), arr1.end(), less<int_fast16_t>());
	sort(arr2.begin(), arr2.end(), greater<int_fast16_t>());
	int ans = 0;
	for(int i = 0; i<n; i++){
		ans += (arr1[i] * arr2[i]);
	}
	cout << ans;
	return 0;
}