#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

typedef long long ll;

vector<ll> x;
vector<ll> y;
int N, M;

int dfs(vector<vector<int>> adj, vector<bool> visited) {
  ll minPerimeter = 9223372036854775807;

  for (int i = 1; i < N + 1; i++) {
    if (!visited[i]) {
      // run dfs
      queue<int> q;
      q.push(i);

      ll minX = 100000001;
      ll minY = 100000001;
      ll maxX = -100000001;
      ll maxY = -100000001;

      while (!q.empty()) {
        int cur = q.front();
        q.pop();
        visited[cur] = true;

        minX = min(minX, x[cur]);
        maxX = max(maxX, x[cur]);
        minY = min(minY, y[cur]);
        maxY = max(maxY, y[cur]);

        for (int a : adj[cur]) {
          if (!visited[a]) {
            q.push(a);
          }
        }
      }

      minPerimeter = min(minPerimeter, (maxX - minX) * 2 + (maxY - minY) * 2);
    }
  }

  return minPerimeter;
}

int main() {
  freopen("fenceplan.in", "r", stdin);
  freopen("fenceplan.out", "w", stdout);

  vector<vector<int>> adj;
  vector<bool> visited;

  cin >> N >> M;
  for (int i = 0; i < N + 1; i++) {
    x.push_back(0);
    y.push_back(0);
    visited.push_back(false);
    vector<int> tmp;
    tmp.shrink_to_fit();
    adj.emplace_back(move(tmp));
  }

  ll a, b;
  for (int i = 1; i < N + 1; i++) {
    cin >> a >> b;
    x[i] = a;
    y[i] = b;
  }

  int c, d;
  for (int i = 0; i < M; i++) {
    cin >> c >> d;

    adj[c].push_back(d);
    adj[d].push_back(c);
  }

  cout << dfs(adj, visited) << endl;

  return 0;
}