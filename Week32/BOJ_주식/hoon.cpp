#include <iostream>
#include <vector>

using namespace std;

int main() {
    int T;
    cin >> T;
    while (T--) {
        int N;
        cin >> N;
        vector<int> prices(N);
        for (int i = 0; i < N; ++i) {
            cin >> prices[i];
        }

        long long profit = 0;
        int maxPrice = 0;

        for (int i = N - 1; i >= 0; --i) {
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            }
            profit += maxPrice - prices[i];
        }

        cout << profit << endl;
    }
    return 0;
}
