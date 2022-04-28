from collections import Counter


def getMaxWin(fj: list, N):
  right = dict(Counter(fj))
  left = {'H': 0, 'P': 0, 'S': 0}
  bigCount = 0
  for i in range(0, N):
    right[fj[i]] -= 1
    left[fj[i]] += 1
    bigCount = max(bigCount, max(right.values()) + max(left.values()))
  return bigCount


def main(inputFile, outputFile):
  hpsInput = open(inputFile, 'r')
  hpsOutput = open(outputFile, 'w')
  
  N = int(hpsInput.readline().strip())
  fj = []
  for _ in range(0, N):
    fj.append(hpsInput.readline().strip())
  hpsOutput.write(str(getMaxWin(fj, N)) + '\n')
  
  hpsInput.close()
  hpsOutput.close()


main('hps.in', 'hps.out')