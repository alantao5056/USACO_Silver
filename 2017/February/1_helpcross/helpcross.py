def getAvaliableCows(cows, c):
  minEnd = float("inf")
  maxIndex = -1
  
  start = False
  
  for i in range(0, len(cows)):
    if cows[i][0] <= c and cows[i][1] >= c:
      if not start:
        start = True
      # avaliable cow
      if cows[i][1] < minEnd:
        maxIndex = i
        minEnd = cows[i][1]
    else:
      if start:
        break
  return maxIndex

def getNumOfCross(chickens: list, cows: list) -> int:
  cows = sorted(cows)
  chickens = sorted(chickens)
  count = 0
  
  for c in chickens:
    pair = getAvaliableCows(cows, c)
    if pair != -1:
      del cows[pair]
      count += 1
  return count

def main(inputFile, outputFile):
  helpcrossInput = open(inputFile, 'r')
  helpcrossOutput = open(outputFile, 'w')

  C, N = helpcrossInput.readline().strip().split()
  C, N = int(C), int(N)
  
  chickens = []
  cows = []
  
  for _ in range(C):
    chickens.append(int(helpcrossInput.readline().strip()))
  
  for _ in range(N):
    line = helpcrossInput.readline().strip().split()
    cows.append([int(line[0]), int(line[1])])
  
  helpcrossOutput.write(str(getNumOfCross(chickens, cows)) + '\n')
  
  helpcrossInput.close()
  helpcrossOutput.close()
  
  
main('helpcross.in', 'helpcross.out')
  