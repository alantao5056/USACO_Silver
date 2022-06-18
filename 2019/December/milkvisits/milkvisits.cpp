#include <iostream>
#include <fstream>
#include <string>
#include <vector>

int N, M;

int main() {
  std::ifstream inputFile("milkvisits.in");

  std::string milkTypes;
  inputFile >> N >> M >> milkTypes;

  // initializing connection
  std::vector<std::vector<int>> connection;
  for (int i = 0; i < N + 1; i++) {
    connection.push_back(std::vector<int>());
  }

  // making connections
  int x, y;
  for (int i = 0; i < N - 1; i++) {
    inputFile >> x >> y;
    connection.at(x).push_back(y);
    connection.at(y).push_back(x);
  }

  std::vector<int> groups(N, 0);

  return 0;
}