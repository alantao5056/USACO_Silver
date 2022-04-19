import operator

class point():
  def __init__(self, time, cow, isStart):
    self.time = time
    self.cow = cow
    self.isStart = isStart

def getNumOfCross(chickens, timestamp) -> int:
  chickens = sorted(chickens)
  timestamp = sorted(timestamp, key=operator.attrgetter("time"))
  curCows = set()
  count = 0
  
  cIndex = 0
  tIndex = 0
  
  while cIndex < len(chickens) and tIndex < len(timestamp):
    if timestamp[tIndex].time <= chickens[cIndex]:
      if timestamp[tIndex].isStart:
        curCows.add(timestamp[tIndex].cow)
      else:
        if timestamp[tIndex].time != chickens[cIndex]:
          if timestamp[tIndex].cow in curCows:
            curCows.remove(timestamp[tIndex].cow)
      tIndex += 1
    else:
      # finding smallest
      if len(curCows) != 0:
        smallest = float("inf")
        smallestValue = []
        for c in curCows:
          if c[1] < smallest:
            smallest = c[1]
            smallestValue = c
        curCows.remove(smallestValue)
        count += 1
      cIndex += 1
  
  return count
    

def main(inputFile, outputFile):
  helpcrossInput = open(inputFile, 'r')
  helpcrossOutput = open(outputFile, 'w')

  C, N = helpcrossInput.readline().strip().split()
  C, N = int(C), int(N)
  
  chickens = []
  timestamp = [0] * (N*2)
  
  for i in range(C):
    chickens.append(int(helpcrossInput.readline().strip()))
  
  line = helpcrossInput.readline().strip().split()
  line[0], line[1] = int(line[0]), int(line[1])
  aRandomTime = (line[0], line[1],)
  timestamp[0] = point(line[0], (line[0], line[1],), True)
  timestamp[1] = point(line[1], (line[0], line[1],), False)
  allSame = True
  
  for i in range(1, N):
    line = helpcrossInput.readline().strip().split()
    line[0] = int(line[0])
    line[1] = int(line[1])
    
    if allSame:
      if line[0] != aRandomTime[0] or line[1] != aRandomTime[1]:
        allSame = False
    
    timestamp[i*2] = point(line[0], (line[0], line[1],), True)
    timestamp[i*2 + 1] = point(line[1], (line[0], line[1],), False)

  if len(set(chickens)) == 1 and allSame:
    if aRandomTime[0] <= chickens[0] and aRandomTime[1] >= chickens[0]:
      helpcrossOutput.write(str(C) + '\n')
  else:
    helpcrossOutput.write(str(getNumOfCross(chickens, timestamp)) + '\n')
  
  helpcrossInput.close()
  helpcrossOutput.close()
  
  
main('helpcross.in', 'helpcross.out')
