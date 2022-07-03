import java.io.PrintWriter;
import java.io.File;
import java.util.*;

/**
 * the greedy strategy of using a boot until it can't anymore
 * this does 7/10 tests and is not the optimal solution
 */

public class Snowboots {
  static int N;
  static int B;
  static int[] depths;
  static int[] bootDepths;
  static int[] bootStepLen;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("test/3.in"));
    PrintWriter pw = new PrintWriter("snowboots.out");
    N = sc.nextInt();
    B = sc.nextInt();
    depths = new int[N];
    bootDepths = new int[B];
    bootStepLen = new int[B];
    
    for (int i = 0; i < N; i++) {
      depths[i] = sc.nextInt();
    }
    
    for (int i = 0; i < B; i++) {
      bootDepths[i] = sc.nextInt();
      bootStepLen[i] = sc.nextInt();
    }
    
    // solve
    // brute force
    int curSnowIdx = 0;
    int curShoeIdx = 0;
    int wasted = 0;

    while (curSnowIdx < N-1) {
      // if (depths[curSnowIdx] > bootDepths[curShoeIdx]) {
      //   curShoeIdx++;
      //   wasted++;
      //   continue;
      // }
      int end = curSnowIdx + bootStepLen[curShoeIdx];
      if (end >= N-1) {
        // can step onto ground of the finish
        break;
      }

      // check what tile to go to
      boolean canStep = false;
      for (int i = end; i > curSnowIdx; i--) {
        if (depths[i] <= bootDepths[curShoeIdx]) {
          // can step
          canStep = true;
          curSnowIdx = i;
        }
      }

      if (!canStep) {
        curShoeIdx++;
        wasted++;
        while (bootDepths[curShoeIdx] < depths[curSnowIdx]) {
          curShoeIdx++;
          wasted++;
        }
      }
    }

    pw.println(wasted);

    sc.close();
    pw.close();
  }
}