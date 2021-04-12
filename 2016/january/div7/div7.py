def getSubSum(arr: list, a: int, b: int):
  return arr[b + 1] - arr[a]


def getMaxDiv7(N: int, origArr: list, prefixArr: list):
  maxCurSum = 0 # return this
  
  for i in range(0, 7):
    start = -1
    end = -1
    for j in range(0, N):
      if prefixArr[j] == i:
        if start == -1:
          start = j
        end = j
      if start != -1:
        maxCurSum = max(maxCurSum, end - start)

  return maxCurSum      
      


def main(inputFile, outputFile):
  div7Input = open(inputFile, 'r')
  div7Output = open(outputFile, 'w')
  
  N = int(div7Input.readline().strip())
  
  origArr = [0] * N
  prefixArr = [0] * (N + 1)
  
  cur = 0  
  for i in range(N):
    num = int(div7Input.readline().strip())
    origArr[i] = num
    cur += num
    prefixArr[i + 1] = cur % 7
  
  # print(origArr)
  # print(prefixArr)

  div7Output.write(str(getMaxDiv7(N, origArr, prefixArr)) + '\n')
  
  div7Input.close()
  div7Output.close()

main('div7.in', 'div7.out')
