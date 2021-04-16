def getMaxWaitingTime(N: int, M: int, C: int, times: list) -> int:
  i = 0
  maxWaitingTime = 0
  while i + C < N:
    maxWaitingTime = max(maxWaitingTime, times[i + C - 1] - times[i])
    # print(times[i + C - 1] - times[i])
    i += C
  
  maxWaitingTime = max(maxWaitingTime, times[N - 1] - times[i])
  
  return maxWaitingTime

def main(inputFile: str, outputFile: str):
  conventionInput = open(inputFile, 'r')
  conventionOutput = open(outputFile, 'w')

  N, M, C = conventionInput.readline().strip().split()
  N, M, C = int(N), int(M), int(C)

  times = conventionInput.readline().strip().split()
  times = list(map(int, times))

  conventionOutput.write(str(getMaxWaitingTime(N, M, C, sorted(times))) + '\n')
  
  conventionInput.close()
  conventionOutput.close()


main('convention.in', 'convention.out')
