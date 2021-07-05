def getMaxProfit(cows: list, offers: list, rents: list) -> int:
  offerIdx = len(offers) - 1
  rentIdx = len(rents) - 1
  cowOfferIdx = len(cows) - 1
  cowRentIdx = 0
  profit = 0
  isNewOffer = True

  while (offerIdx >= 0 or rentIdx >= 0) and cowRentIdx <= cowOfferIdx:
    offerUnitPrice = offers[offerIdx][0]
    rentUnitPrice = rents[rentIdx] / cows[cowRentIdx]
    if ((rentUnitPrice >= offerUnitPrice and rentIdx >= 0) or offerIdx < 0) and isNewOffer:
      # rent
      profit += rents[rentIdx]
      rentIdx -= 1
      cowRentIdx += 1
    else:
      # offer
      if offers[offerIdx][1] > cows[cowOfferIdx]:
        profit += cows[cowOfferIdx] * offerUnitPrice
        offers[offerIdx][1] -= cows[cowOfferIdx]
        cowOfferIdx -= 1
        isNewOffer = True
      elif offers[offerIdx][1] == cows[cowOfferIdx]:
        profit += cows[cowOfferIdx] * offerUnitPrice
        offers[offerIdx][1] -= cows[cowOfferIdx]
        cowOfferIdx -= 1
        offerIdx -= 1
        isNewOffer = True
      else:
        profit += offers[offerIdx][1] * offerUnitPrice
        cows[cowOfferIdx] -= offers[offerIdx][1]
        offerIdx -= 1
        isNewOffer = False
        
  return profit
  
  # if cowOfferIdx > cowRentIdx:
  #   return profit
  
  # if offerIdx >= 0:
  #   while offerIdx >= 0 and cowOfferIdx < len(cows):
  #     if offers[offerIdx][1] > cows[cowOfferIdx]:
  #       profit += cows[cowOfferIdx] * offers[offerIdx][0]
  #       cowOfferIdx += 1
  #       offers[offerIdx][1] -= cows[cowOfferIdx]
  #     elif offers[offerIdx][1] == cows[cowOfferIdx]):
  #       profit += cows[cowOfferIdx] * offers[offerIdx][0]
  #       cowOfferIdx += 1
  #       offerIdx -= 1
  #     else:
  #       profit += offers[offerIdx][1] * offerUnitPrice
      

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