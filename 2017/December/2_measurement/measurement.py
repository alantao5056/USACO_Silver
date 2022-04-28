def findMax(some_dict):
  positions = set() # output variable
  max_value = -1
  for k, v in some_dict.items():
    if v == max_value:
      positions.add(k)
    if v > max_value:
      max_value = v
      positions = set() # output variable
      positions.add(k)

  return positions, max_value

def getChangeTimes(cowLog: list, milkLog: dict) -> int:
  board, maxMilk = findMax(milkLog)
  count = 0 # return this
  
  for c in cowLog:
    milkLog[c[1]] += c[2]
    if c[1] in board:
      if c[2] < 0:
        if len(board) == 1:
          # the one cow on board decreased
          newBoard, maxMilk = findMax(milkLog)
          if newBoard != board:
            count += 1
            board = newBoard
        else:
          # one cow on board just got bad
          board.remove(c[1])
          count += 1
      else:
        # one cow on board just got new record
        if len(board) != 1:
          board = set([c[1]])
          count += 1
        maxMilk = milkLog[c[1]]
    else:
      if milkLog[c[1]] > maxMilk:
        # one cow got new record
        maxMilk = milkLog[c[1]]
        board = set([c[1]])
        count += 1
      elif milkLog[c[1]] == maxMilk:
        # tied with old cow
        board.add(c[1])
        count += 1
  if count == 0:
    return 1
  return count
        
        
        
def main(inputFile, outputFile):
  measurementInput = open(inputFile, 'r')
  measurementOutput = open(outputFile, 'w')
  
  cowLog = []
  milkLog = {}
  
  N, G = measurementInput.readline().strip().split()
  N, G = int(N), int(G)
  
  for _ in range(N):
    line = measurementInput.readline().strip().split()
    cow = int(line[1])
    
    cowLog.append((int(line[0]), cow, int(line[2]),))
    if cow not in milkLog:
      milkLog[cow] = G
  
  measurementOutput.write(str(getChangeTimes(sorted(cowLog), milkLog)) + '\n')
  
  measurementInput.close()
  measurementOutput.close()


main('measurement.in', 'measurement.out')
