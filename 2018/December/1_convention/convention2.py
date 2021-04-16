def getIfTrueWait(times: list, maxWaiting: int, N: int, M: int, C: int) -> bool:
  i = 0
  buses = 0
  while i < N:
    curPeople = 1
    firstOnBus = times[i]
    lastOnBus = times[i]
    while curPeople < C:
      if i < N - 1:
        lastOnBus = times[i + 1]
      else:
        break
      curPeople += 1
      if lastOnBus - firstOnBus > maxWaiting:
        break
      i += 1
    i += 1
    buses += 1
  
  return buses <= M

def getMaxWaitingTime(N: int, M: int, C: int, times: list) -> int:
  low = 1
  high = 1000000000
  mid = 0

  while True:
    mid = (high + low) // 2

    # If x is greater, ignore left half
    if not getIfTrueWait(times, mid, N, M, C):
      low = mid

    # If x is smaller, ignore right half
    else:
      high = mid

    if low == high:
      return high
    if low + 1 == high:
      return high


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

# print(getIfTrueWait([1, 1, 3, 4, 10, 14], 10, 6, 3, 50))
