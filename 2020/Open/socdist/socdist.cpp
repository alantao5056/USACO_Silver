#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

struct grass {
  int start;
  int end;
};

struct less_than_key {
  inline bool operator() (const grass& struct1, const grass& struct2) {
    return (struct1.start < struct2.start);
  }
};

int N, M;
vector<grass> patchesOfGrass;

int check(int d) {
  int count = 1;
  int cur = patchesOfGrass[0].start;

  while (cur + d < patchesOfGrass.back().end && count < N) {
    int nextGrassIndex = 0;
    bool startOfNextGrass = false;

    for (int i = 0; i < M; i++) {
      if (patchesOfGrass[i].start > cur + d) {
        // not in a grass
        nextGrassIndex = i;
        startOfNextGrass = true;
        break;
      }
      if (patchesOfGrass[i].start <= cur + d && patchesOfGrass[i].end >= cur + d) {
        // in a grass
        nextGrassIndex = i;
        break;
      }
    }

    cur = startOfNextGrass ? patchesOfGrass[nextGrassIndex].start : cur + d;
    count++;
  }

  return count == N;
}

int getMaximumDistance() {
  int lo = 1;
  int hi = patchesOfGrass.back().end;

  while(lo<hi) {
    int mid=(lo+hi)/2;

    if(!check(mid)) {
      hi=mid;
    } else {
      lo=mid+1;
    }
  }
  return check(lo) ? lo : lo - 1;
}

int main() {
  freopen("socdist.in", "r", stdin);
  freopen("socdist.out", "w", stdout);

  cin >> N;
  cin >> M;

  for (int i = 0; i < M; i++) {
    patchesOfGrass.push_back(grass());

    cin >> patchesOfGrass[i].start;
    cin >> patchesOfGrass[i].end;
  };

  sort(patchesOfGrass.begin(), patchesOfGrass.end(), less_than_key());

  cout << getMaximumDistance() << endl;

  // for (int i = 0; i < M; i++) {
  //   cout << patchesOfGrass[i].start << " " << patchesOfGrass[i].end << endl;
  // }

  return 0;
}