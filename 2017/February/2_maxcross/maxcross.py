def getMinFix(K, N, signals):
  if K == 1:
    if set(signals) == {False}:
      return 1
    return 0

  startInd = 0
  endInd = K - 1
  
  lastStart = signals[0]
  curCount = signals[0:K].count(False)
  minCount = curCount
  
  for _ in range(N-K):
    startInd += 1
    endInd += 1
    if not lastStart:
      curCount -= 1
    if not signals[endInd]:
      curCount += 1
    lastStart = signals[startInd]
    minCount = min(minCount, curCount)

  return minCount
  

def main(inputFile, outputFile):
  maxcrossInput = open(inputFile, 'r')
  maxcrossOutput = open(outputFile, 'w')
  
  N, K, B = maxcrossInput.readline().strip().split()
  N, K, B = int(N), int(K), int(B)
  
  signals = [True] * N
  
  for _ in range(B):
    signals[int(maxcrossInput.readline().strip()) - 1] = False
  
  # print(signals)
  maxcrossOutput.write(str(getMinFix(K, N, signals)) + '\n')
  
  maxcrossInput.close()
  maxcrossOutput.close()


main('maxcross.in', 'maxcross.out') 