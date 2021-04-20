def getMinFire(cows, N):
  maxEnd = 0
  totalTime = 0
  minTime = float("inf")
  for i in range(0, N - 1):
    left = 0
    right = 0
    if maxEnd < cows[i][1]:
      # maxEnd not covered this cow
      if maxEnd > cows[i][0]:
        # maxEnd is in the middle of this cow
        left = maxEnd - cows[i][0]
      if cows[i + 1][0] < cows[i][1]:
        # next cow start is in the middle
        right = cows[i][1] - cows[i + 1][0]
    
      time = (cows[i][1] - cows[i][0]) - left - right
      minTime = min(minTime, time)
      # adding to total time
      totalTime += time + right
    else:
      # fire this cow
      minTime = 0
    
    maxEnd = max(maxEnd, cows[i][1]) # update max end
  
  # dealing with the last cow
  if maxEnd < cows[N - 1][1]:
    if maxEnd > cows[N - 1][0]:
      time = cows[N - 1][1] - maxEnd  
    else:
      time = cows[N - 1][1] - cows[N - 1][0]
    minTime = min(minTime, time)
    totalTime += time
  
  return totalTime - minTime


def main(inputFile, outputFile):
  lifeguardsInput = open(inputFile, 'r')
  N = int(lifeguardsInput.readline().strip())
  cows = []
  
  for _ in range(N):
    line = lifeguardsInput.readline().strip().split()
    cows.append((int(line[0]), int(line[1]),))
  
  lifeguardsInput.close()
  
  cows = sorted(cows)
  lifeguardsOutput = open(outputFile, 'w')
  
  lifeguardsOutput.write(str(getMinFire(cows, N)) + '\n')

  lifeguardsOutput.close()


main('lifeguards.in', 'lifeguards.out')  