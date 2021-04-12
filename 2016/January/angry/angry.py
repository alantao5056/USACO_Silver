def checkIfOK(cows, N, K, P):
  count = 1
  actualRange = P * 2
  curRange = cows[0] + actualRange
  for i in range(1, N):
    if cows[i] > curRange:
      curRange = cows[i] + actualRange
      count += 1

  return count <= K

def getMinR(cows, N, K, start, end):
  if start >= end:
    if not checkIfOK(cows, N, K, start):
      return start + 1
    return start
  mid = (end - start) // 2 + start

  ifValidMid = checkIfOK(cows, N, K, mid)

  if ifValidMid:
    return getMinR(cows, N, K, start, mid - 1)
  else:
    return getMinR(cows, N, K, mid + 1, end)


def main(inputFile, outputFile):
  angryInput = open(inputFile, 'r')
  angryOutput = open(outputFile, 'w')

  N, K = angryInput.readline().strip().split()
  N, K = int(N), int(K)

  cows = []
  maxCow = -1
  minCow = float("inf")
  for _ in range(N):
    cow = int(angryInput.readline().strip())
    cows.append(cow)
    if cow > maxCow:
      maxCow = cow
    if cow < minCow:
      minCow = cow

  cows = sorted(cows)
  # print(str(checkIfOK(cows, N, K, 4)) + '\n')
  # print(str(checkIfOK(cows, N, K, 5)) + '\n')
  # print(str(checkIfOK(cows, N, K, 6)) + '\n')
  angryOutput.write(str(getMinR(cows, N, K, minCow, maxCow)) + '\n')

  angryInput.close()
  angryOutput.close()

main("angry.in", "angry.out")