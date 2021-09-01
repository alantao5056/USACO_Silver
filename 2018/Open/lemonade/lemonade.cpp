#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;
int N;
vector<int> cows;

int getMinInLine() {
  int curInLine = 0;
  for (int c : cows) {
    if (c >= curInLine) {
      curInLine++;
    }
  }
  return curInLine;
}

int main() {
  ifstream in;
  in.open("lemonade.in");
  in >> N;

  int c;
  for (int i = 0; i < N; i++) {
    in >> c;
    cows.push_back(c);
  }

  sort(cows.begin(), cows.end(), greater<>());

  in.close();

  ofstream out;
  out.open("lemonade.out");
  out << getMinInLine() << endl;
  out.close();
}