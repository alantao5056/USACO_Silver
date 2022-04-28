def getLetter(s: str, p: int) -> str:
  cur = len(s)
  while cur <= p:
    cur += cur
  
  while p > len(s):
    cur = cur // 2
    if cur == p - 1:
      p -= 1
    elif cur < p:
      p = p - cur - 1
  return s[p - 1]


def main(inputFile, outputFile):
  cowcodeInput = open(inputFile, 'r')
  cowcodeOutput = open(outputFile, 'w')
  
  s, p = cowcodeInput.readline().strip().split()
  p = int(p)
  
  cowcodeOutput.write(str(getLetter(s, p)) + '\n')
  
  cowcodeInput.close()
  cowcodeOutput.close()

main('cowcode.in', 'cowcode.out')
