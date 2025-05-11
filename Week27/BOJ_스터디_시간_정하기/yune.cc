#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int n, t;
long long check_point[100005];
long long psum[100005];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n >> t;

	for (int i = 0; i < n; i++) {
		int k;
		cin >> k;

		for (int j = 0; j < k; j++) {
			int s, e;
			cin >> s >> e;

			check_point[s] += 1;
			check_point[e] -= 1;
		}
	}

	//check_point의 누적합을 구한다
	psum[0] = check_point[0];
	for (int i = 1; i < 100005; i++) {
		psum[i] = psum[i - 1] + check_point[i];
	}

	//누적합에서 슬라이딩 윈도우를 통해 스터디 시간의 구간에서 최대 합이 되는 범위를 구한다
	long long max_sum = 0;
	long long this_sum = 0;

	int left = 0;
	int right = t - 1;
	int result_left = 0;
	int result_right = (t - 1) + 1;
	for (int i = 0; i < t; i++) {
		this_sum += psum[i];
	}
	max_sum = max(max_sum, this_sum);

	while (right < 100005) {
		this_sum -= psum[left];
		left++;
		right++;
		this_sum += psum[right];

		//더 큰 합을 찾았을때
		if (max_sum < this_sum) {
			max_sum = this_sum;
			result_left = left;
			result_right = right + 1;
		}
	}

	cout << result_left << " " << result_right << "\n";

	return 0;
}