def getCord(d, curCord):
  # updates cord
  if d == "N":
    return (curCord[0], curCord[1] + 1,)
  elif d == "E":
    return (curCord[0] + 1, curCord[1],)
  elif d == "S":
    return (curCord[0], curCord[1] - 1,)
  else:
    # curCord is "W"
    return (curCord[0] - 1, curCord[1],)


def getNumOfGates(N, directions):
  # main function
  count = 0
  visitedDirec = set([(0, 0,)])
  fences = {}
  lastCord = (0, 0)

  curCord = (0, 0)

  for i in range(0, N):
    curCord = getCord(directions[i], curCord) # updates curCord

    # putting cords in hash
    if curCord in fences:
      fences[curCord].add(lastCord)
    else:
      fences[curCord] = set([lastCord])

    # check if touching original point
    if curCord in visitedDirec:
      # check if already drawn line
      if curCord not in fences[lastCord]:
        count += 1

    # putting cords in hash
    if lastCord in fences:
      fences[lastCord].add(curCord)
    else:
      fences[lastCord] = set([curCord])

    visitedDirec.add(curCord)
    lastCord = curCord

  return count


def main(inputFile, outputFile):
  # reads input
  gatesInput = open(inputFile, 'r')
  N = int(gatesInput.readline().strip())
  directions = gatesInput.readline().strip()
  gatesInput.close()

  # writes output
  gatesOutput = open(outputFile, 'w')
  gatesOutput.write(str(getNumOfGates(N, directions)) + '\n')
  gatesOutput.close()


main('gates.in', 'gates.out')
