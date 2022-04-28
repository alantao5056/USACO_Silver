import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

class Berries {
  public static int getMostBerries(Integer[] trees, int N, int M) {
    int maxResult = 0;
    for (int q = 1; q < 1000; q++) {
      int fullChunks = 0;
      Integer[] remains = new Integer[N];
      for (int i = 0; i < N; i++) {
        fullChunks += (int) trees[i]/q;
        remains[i] = trees[i] % q;
        if (fullChunks >= M) {
          // found optimal solution
          maxResult = Math.max(maxResult, q*2);
          break;
        }
      }
      if (fullChunks >= M) {
        continue;
      }

      Arrays.sort(remains, Collections.reverseOrder());
      int partRemains = 0;
      if (fullChunks > M/2) {
        partRemains += (fullChunks - M/2) * q;
      }
      for (int i = 1; i < M - fullChunks + 1; i++) {
        if (i >= N) {
          break;
        }
        if (fullChunks + i > M/2) {
          partRemains += remains[i - 1];
        }
      }

      maxResult = Math.max(maxResult, partRemains);
    }

    return maxResult;
  }

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("berries.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("berries.out"));
    String[] line = br.readLine().split("\\s+");
    int N = Integer.parseInt(line[0]);
    int M = Integer.parseInt(line[1]);

    Integer[] trees = new Integer[N];

    String[] treesString = br.readLine().split("\\s+");

    for (int i = 0; i < N; i++) {
      trees[i] = Integer.parseInt(treesString[i]);
    }

    Arrays.sort(trees, Collections.reverseOrder());
    System.out.println(Arrays.toString(trees));
    br.close();

    pr.println(getMostBerries(trees, N, M));

    pr.close();
  }
}