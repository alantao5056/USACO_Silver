def getMilkOffer(cow, milkOffers, curMilkIndex):
  # milkOffers[i][0] is the value
  # milkOffers[i][1] is how many are there
  profit = 0
  for i in range(curMilkIndex, len(milkOffers)):
    if milkOffers[i][1] <= cow:
      curMilkIndex += 1
      profit += milkOffers[i][0] * milkOffers[i][1]
      cow -= milkOffers[i][1]
    else:
      # needs to stop
      profit += cow * milkOffers[i][0]
      return profit, curMilkIndex, cow
  return profit, -1, -1

def getMaxProfit(cows: list, milkOffers: list, rents: list) -> int:
  curRentIndex = 0
  curMilkIndex = 0
  profit = 0 # return this
  for c in cows:
    milkProfit = -1
    if curMilkIndex != -1:
      milkProfit, newMilkIndex, sub = getMilkOffer(c, milkOffers, curMilkIndex)
      
      if newMilkIndex == -1:
        curMilkIndex = -1
    if rents[curRentIndex] > milkProfit:
      # rather just sell the cow
      profit += rents[curRentIndex]
      curRentIndex += 1
    else:
      # rather milk
      curMilkIndex = newMilkIndex
      milkOffers[curMilkIndex][1] -= sub
      profit += milkProfit

  return profit

def main(inputFile, outputFile):
  rentalInput = open(inputFile, 'r')
  N, M, R = rentalInput.readline().strip().split()
  N, M, R = int(N), int(M), int(R)
  
  cows = [0] * N
  for i in range(N):
    cows[i] = int(rentalInput.readline().strip())
  
  milkOffers = [None] * M
  for i in range(M):
    line = rentalInput.readline().strip().split()
    milkOffers[i] = (int(line[1]), int(line[0]),)

  rents = [0] * R
  for i in range(R):
    rents[i] = int(rentalInput.readline().strip())
  
  rentalInput.close()  
  
  cows, milkOffers, rents = sorted(cows), sorted(milkOffers, reverse=True), sorted(rents, reverse=True)
  
  # print(cows)
  # print(milkOffers)
  # print(rents)
  
  # writing output
  rentalOutput = open(outputFile, 'w')
  
  rentalOutput.write(str(getMaxProfit(cows, milkOffers, rents)) + '\n')
  
  rentalOutput.close()


main('rental.in', 'rental.out')
