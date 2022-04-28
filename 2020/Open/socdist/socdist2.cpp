#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <cmath>

using namespace std;

int N, M;
pair<long long, long long> intervals[100001];

bool check(long long d) {
  long long curX = intervals[0].first;
  int curInterval = 0;

  for (int i = 1; i < N; i++) {
    curX += d;
    while (curInterval < M && curX > intervals[curInterval].second) {
      curInterval++;
    }

    if (curInterval == M) {
      // out of range
      return false;
    }
    if (intervals[curInterval].first > curX) {
      curX = intervals[curInterval].first;
    }
  }

  return true;
}

int main() {

  freopen("socdist.in", "r", stdin);
  freopen("socdist.out", "w", stdout);

  cin >> N >> M;

  for (int i = 0; i < M; i++) {
    cin >> intervals[i].first >> intervals[i].second;
  }

  sort(intervals, intervals + M);

  long long lo = 1;
  long long hi = 1000000000000000001;

  while (lo + 1 < hi) {
    long long mid = (lo + hi)/2;

    if (check(mid)) {
      lo = mid;
    } else {
      hi = mid;
    }
  }

  cout << lo << endl;

  return 0;
}