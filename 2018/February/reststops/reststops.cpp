#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
using namespace std;

int L, N, rf, rb;
vector<pair<int, int>> restStops;
vector<bool> good;

long long solve() {
  long long bPos = 0;
  long long count = 0;

  for (int i = 0; i < N; i++) {
    // simulate going there
    if (good[i]) {
      long long restPos = restStops[i].first;
      long long tastiness = restStops[i].second;

      long long bSeconds = rb * (restPos-bPos);
      long long fSeconds = rf * (restPos-bPos);

      count += tastiness * (fSeconds - bSeconds);

      // update bPos
      bPos = restPos;
    }
  }

  return count;
}

int main() {
  freopen("reststops.in", "r", stdin);
  freopen("reststops.out", "w", stdout);

  cin >> L >> N >> rf >> rb;

  for (int i = 0; i < N; i++) {
    good.push_back(false);
  }

  for (int i = 0; i < N; i++) {
    int pos, t;
    cin >> pos >> t;
    restStops.push_back(make_pair(pos, t));
  }

  int curBiggest = -1;
  for (int i = N - 1; i >= 0; i--) {
    if (restStops[i].second > curBiggest) {
      good[i] = true;
      curBiggest = restStops[i].second;
    }
  }

  cout << solve() << endl;

  return 0;
}