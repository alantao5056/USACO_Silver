import operator

class point():
  def __init__(self, time, cow, isStart):
    self.time = time
    self.cow = cow
    self.isStart = isStart

def getNumOfCross(timestamp) -> int:
  timestamp = sorted(timestamp, key=operator.attrgetter("time"))
  curCows = set()
  count = 0
  
  for t in timestamp:
    if t.cow != None:
      if t.isStart:
        curCows.add(t.cow)
        continue
      else:
        if t.cow in curCows:
          curCows.remove(t.cow)
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
  return count
    

def main(inputFile, outputFile):
  helpcrossInput = open(inputFile, 'r')
  helpcrossOutput = open(outputFile, 'w')

  C, N = helpcrossInput.readline().strip().split()
  C, N = int(C), int(N)
  
  timestamp = [0] * (C+N*2)
  
  for i in range(C):
    timestamp[N*2 + i] = point(int(helpcrossInput.readline().strip()), None, False)
  
  for i in range(N):
    line = helpcrossInput.readline().strip().split()
    line[0] = int(line[0])
    line[1] = int(line[1])
    timestamp[i*2] = point(line[0], (line[0], line[1],), True)
    timestamp[i*2 + 1] = point(line[1], (line[0], line[1],), False)

  helpcrossOutput.write(str(getNumOfCross(timestamp)) + '\n')
  
  helpcrossInput.close()
  helpcrossOutput.close()
  
  
main('helpcross.in', 'helpcross.out')
