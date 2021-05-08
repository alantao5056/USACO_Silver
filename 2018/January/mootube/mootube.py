def getVideos(k, v, relationHash):
  count = 0
  for r in relationHash[v]:
    if r >= k:
      count += 1
  return count

def main(inputFile, outputFile):
  mootubeInput = open(inputFile, 'r')
  mootubeOutput = open(outputFile, 'w')
  
  N, Q = mootubeInput.readline().strip().split()
  N, Q = int(N), int(Q)
  
  relationHash = {}
  
  for _ in range(N - 1):
    p, q, r = mootubeInput.readline().strip().split()
    p, q, r = int(p), int(q), int(r)
    
    if p in relationHash:
      relationHash[p].append(r)
    else:
      relationHash[p] = [r]
    if q in relationHash:
      relationHash[q].append(r)
    else:
      relationHash[q] = [r]
  
  for _ in range(Q):
    k, v = mootubeInput.readline().strip().split()
    k, v = int(k), int(v)
    mootubeOutput.write(str(getVideos(k, v, relationHash)) + '\n')
  
  mootubeInput.close()
  mootubeOutput.close()


main('mootube.in', 'mootube.out')