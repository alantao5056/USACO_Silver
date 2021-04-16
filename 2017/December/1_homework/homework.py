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
  sortedNums = sorted(nums)
  # usedNums = set()
  curSortIndex = 0
  if nums[0] == sortedNums[0]:
    curSortIndex = 1
  
  cur = sum(nums)
  bigEat = 0
  bigSize = 1
  for i in range(0, N-2):
    cur -= nums[i]
    if nums[i] == sortedNums[curSortIndex]:
      # so short lol
      curSortIndex += 1
    average = (cur - sortedNums[curSortIndex]) / (N - i - 2)

    if average > bigEat:
      bigEat = average
      bigSize = i + 1
  final.append(bigSize)
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