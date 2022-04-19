def getMaxWin(fj: list, N, startOrder: list):
  right = startOrder
  left = [0, 0, 0]
  bigCount = 0
  for i in range(0, N):
    if fj[i] == 'H':
      right[0] -= 1
      left[0] += 1
    elif fj[i] == 'P':
      right[1] -= 1
      left[1] += 1
    else:
      right[2] -= 1
      left[2] += 1
    bigCount = max(bigCount, max(right) + max(left))
  return bigCount


def main(inputFile, outputFile):
  hpsInput = open(inputFile, 'r')
  hpsOutput = open(outputFile, 'w')
  
  N = int(hpsInput.readline().strip())
  fj = []
  startOrder = [0, 0, 0]
  for _ in range(0, N):
    line = hpsInput.readline().strip()
    if line == 'H':
      startOrder[0] += 1
    elif line == 'P':
      startOrder[1] += 1
    else:
      startOrder[2] += 1
    fj.append(line)
  hpsOutput.write(str(getMaxWin(fj, N, startOrder)) + '\n')
  
  hpsInput.close()
  hpsOutput.close()


main('hps.in', 'hps.out')