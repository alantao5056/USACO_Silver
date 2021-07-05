def getMaxProfit(cows: list, milkOffers: list, rents: list) -> int:
  curMilkIndex = len(milkOffers) - 1
  curRentIndex = len(rents) - 1
  profit = 0

  cowSmallIndex = 0
  cowBigIndex = len(cows) - 1

  while curMilkIndex >= 0 and curRentIndex >= 0 and cowBigIndex >= cowSmallIndex - 1:
    # compare milkOffer and rent
    curRentOfferValue = rents[curRentIndex] / cows[cowSmallIndex]
    curMilkOfferValue = milkOffers[curMilkIndex][0]
    
    if curRentOfferValue >= curMilkOfferValue:
      # rather rent the cow
      cowSmallIndex += 1
      profit += rents[curRentIndex]
      curRentIndex -= 1
    else:
      # try selling all the milk
      while cows[cowBigIndex] <= milkOffers[curMilkIndex][1] and cowBigIndex >= cowSmallIndex:
        profit += curMilkOfferValue * cows[cowBigIndex]
        milkOffers[curMilkIndex][1] -= cows[cowBigIndex]
        cowBigIndex -= 1
      if cowBigIndex < cowSmallIndex:
        return profit
      profit += milkOffers[curMilkIndex][1] * curMilkOfferValue
      cows[cowBigIndex] -= milkOffers[curMilkIndex][1]
      curMilkIndex -= 1
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
    milkOffers[i] = [int(line[1]), int(line[0])]

  rents = [0] * R
  for i in range(R):
    rents[i] = int(rentalInput.readline().strip())
  
  rentalInput.close()  
  
  cows, milkOffers, rents = sorted(cows), sorted(milkOffers), sorted(rents)
  
  # print(cows)
  # print(milkOffers)
  # print(rents)
  
  # writing output
  rentalOutput = open(outputFile, 'w')
  
  rentalOutput.write(str(getMaxProfit(cows, milkOffers, rents)) + '\n')
  
  rentalOutput.close()


main('rental.in', 'rental.out')