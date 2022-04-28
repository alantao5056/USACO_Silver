def resolveCycle(cur, pos, notes):
  while True:
    notes[cur - 1] = 2
    if notes[pos[cur - 1] - 1] == 2:
      break
    cur = pos[cur - 1]

def getContainCows(N, pos):
  allSet = set([i for i in range(1, N + 1)])
  # finding which cows are not visited
  for p in pos:
    if p in allSet:
      allSet.remove(p)
  
  allSet = list(allSet)
  notes = [0] * N

  for c in allSet:
    # start search
    start = c
    cur = c
    while True:
      if notes[cur - 1] == 1:
        # ran into 1, found cycle
        resolveCycle(cur, pos, notes)
        break
      elif notes[cur - 1] == 0:
        # normal, unvisited
        notes[cur - 1] = 1
        cur = pos[cur - 1]
      else:
        # is -1 or 2
        break
    # cycle and mark -1
    while True:
      if notes[start - 1] == 1:
        notes[start - 1] = -1
      else:
        break
      start = pos[start - 1]
    
  return N - notes.count(-1)
      

def main(inputFile, outputFile):
  shuffleInput = open(inputFile, 'r')
  shuffleOutput = open(outputFile, 'w')
  
  N = int(shuffleInput.readline().strip())
  
  pos = list(map(int, shuffleInput.readline().strip().split()))
  
  shuffleOutput.write(str(getContainCows(N, pos)) + '\n')
  
  shuffleInput.close()
  shuffleOutput.close()
  

main('shuffle.in', 'shuffle.out')
    