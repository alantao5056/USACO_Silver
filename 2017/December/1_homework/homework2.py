from itertools import groupby


def all_equal(iterable):
  g = groupby(iterable)
  return next(g, True) and not next(g, False)


def getBitten(N, nums) -> list:
  final = []
  if all_equal(nums):
    # a trick if all equal
    for i in range(1, N-1):
      final.append(i)
    return final
  
  cur = 0
  start = 0
  maxAverage = -1
  maxAverageIndex = -1
  smallest = float("inf")
  smallestIndex = -1
  for i in range(0, N):
    cur += nums[i]
    if nums[i] < smallest:
      smallest = nums[i]
      smallestIndex = i


  while start < N - 3:
    # eating up homework
    if smallestIndex >= N - 2:
      smallestIndex = N - 3
    for j in range(start, smallestIndex):
      cur -= nums[j]
      average = (cur - smallest) / (N - j - 2)
      if average > maxAverage:
        # found new best estimate
        maxAverage = average
        maxAverageIndex = j + 1
    # resetting
    start = smallestIndex
    
    # find new smallest
    smallest = float("inf")
    for i in range(smallestIndex + 1, N):
      if nums[i] < smallest:
        smallest = nums[i]
        smallestIndex = i
      
  final.append(maxAverageIndex)
  return final

def main(inputFile, outputFile):
  homeworkInput = open(inputFile, 'r')
  homeworkOutput = open(outputFile, 'w')
  
  N = int(homeworkInput.readline().strip())
  
  nums = list(map(int, homeworkInput.readline().strip().split()))
  for i in getBitten(N, nums):
    homeworkOutput.write(str(i) + '\n')
  
  homeworkInput.close()
  homeworkOutput.close()

main('homework.in', 'homework.out')