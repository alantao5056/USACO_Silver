class binaryTreeNode():
  def __init__(self, value):
    self.value = value
    self.left = None
    self.right = None

def getMid(start, end):
  return (end - start) // 2 + start

# def dance(arr: list):
#   smallestIndex = 0
#   smallest = float("inf")
#   for i in range(0, len(arr)):
#     if arr[i] < smallest:
#       smallestIndex = i
#       smallest = arr[i]
#   return smallestIndex, smallest

def addToTree(x: int, root):
  if x <= root.value:
    if root.left == None:
      root.left = binaryTreeNode(x)
      return
    return addToTree(x, root.left)
  else:
    if root.right == None:
      root.right = binaryTreeNode(x)
      return
    return addToTree(x, root.right)

def findSmallAndDel(last, root, overallRoot):
  if root.left == None:
    if last != None:
      last.left = root.right
      return overallRoot, root.value
    return root.right, root.value
  return findSmallAndDel(root, root.left, overallRoot)

def findBiggest(root):
  if root.right == None:
    return root.value
  return findBiggest(root.right)

def startDance(cows, stage, N):
  root = binaryTreeNode(cows[0])
  for i in range(1, stage):
    addToTree(cows[i], root)

  for i in range(stage, N):
    root, small = findSmallAndDel(None, root, root)
    addToTree(cows[i] + small, root)
  
  return findBiggest(root)

def findSmallK(cows: list, N: int, TMax: int, start, end, closest, closestNum) -> int:
  if start > end:
    return closestNum
  mid = getMid(start, end)
  danceTime = startDance(cows, mid, N)
  if danceTime < TMax:
    if TMax - closest > TMax - danceTime:
      closest = danceTime
      closestNum = mid
    return findSmallK(cows, N, TMax, start, mid - 1, closest, closestNum)
  elif danceTime > TMax:
    return findSmallK(cows, N, TMax, mid + 1, end, closest, closestNum)
  else:
    return mid
  

def main(inputFile, outputFile):
  cowdanceInput = open(inputFile, 'r')
  cowdanceOutput = open(outputFile, 'w')
  
  N, TMax = cowdanceInput.readline().strip().split()
  N, TMax = int(N), int(TMax)
  
  cows = []
  for _ in range(N):
    cows.append(int(cowdanceInput.readline().strip()))
  cowdanceOutput.write(str(findSmallK(cows, N, TMax, 1, N, -1111, -1)) + '\n')
  
  cowdanceInput.close()
  cowdanceOutput.close()


main('cowdance.in', 'cowdance.out')
