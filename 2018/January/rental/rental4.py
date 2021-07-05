def getMaxProfit(cows: list, offers: list, rents: list) -> int:
  mxProfit = [0] * (len(cows) + 1)
  curOfferIndex = 0
  curProfit = 0
  offersLen = len(offers)
  rentLen = len(rents)
  
  # from left to right
  for i in range(1, len(cows) + 1):
    while curOfferIndex < offersLen and cows[i - 1] >= offers[curOfferIndex][1]:
      curProfit += offers[curOfferIndex][0] * offers[curOfferIndex][1]
      cows[i - 1] -= offers[curOfferIndex][1]
      curOfferIndex += 1
    if curOfferIndex >= offersLen:
      # offers ran out
      for j in range(i, len(cows)):
        mxProfit[j] = curProfit
      break
    else:
      curProfit += offers[curOfferIndex][0] * cows[i - 1]
      offers[curOfferIndex][1] -= cows[i - 1]
      mxProfit[i] = curProfit

  # from right to left

  curRent = 0
  curRentIndex = 0
  for i in range(len(cows) - 1, -1, -1):
    if curRentIndex >= rentLen:
      # out of range
      for j in range(i, -1, -1):
        mxProfit[i] += curRent
      break
    else:
      curRent += rents[curRentIndex]
      mxProfit[i] += curRent
      curRentIndex += 1

  return max(mxProfit)


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
  
  cows, milkOffers, rents = sorted(cows, reverse=True), sorted(milkOffers, reverse=True), sorted(rents, reverse=True)
  
  # print(cows)
  # print(milkOffers)
  # print(rents)
  
  # writing output
  rentalOutput = open(outputFile, 'w')
  rentalOutput.write(str(getMaxProfit(cows, milkOffers, rents)) + '\n')
  rentalOutput.close()

main('rental.in', 'rental.out')