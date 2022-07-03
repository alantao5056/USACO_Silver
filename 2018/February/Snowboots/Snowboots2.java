import java.io.PrintWriter;
import java.io.File;
import java.util.*;

/**
 * a brute force method of trying all the possibilities of the boots
 * 10/10
 */

public class Snowboots2 {
  static int N;
  static int B;
  static int[] depths;
  static int[] bootDepths;
  static int[] bootStepLen;
  static Set<Integer> canReach;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("snowboots.in"));
    PrintWriter pw = new PrintWriter("snowboots.out");
    N = sc.nextInt();
    B = sc.nextInt();
    depths = new int[N];
    bootDepths = new int[B];
    bootStepLen = new int[B];
    canReach = new HashSet<>();
    canReach.add(0);

    for (int i = 0; i < N; i++) {
      depths[i] = sc.nextInt();
    }
    
    for (int i = 0; i < B; i++) {
      bootDepths[i] = sc.nextInt();
      bootStepLen[i] = sc.nextInt();
    }
    
    // solve
    int count = 0;
    for (int i = 0; i < B; i++) { // cycle boot
      Set<Integer> temp = new HashSet<>();
      for (int c : canReach) {
        if (bootDepths[i] < depths[c]) continue;
        addAllToSet(i, c, temp);
      }

      // set addition
      canReach.addAll(temp);

      if (canReach.contains(N-1)) {
        break;
      }
      count++;
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static void addAllToSet(int bootIdx, int c, Set<Integer> temp) {
    int end = c + bootStepLen[bootIdx];
    for (int i = c+1; i <= end; i++) {
      if (bootDepths[bootIdx] >= depths[i]) {
        // can step
        temp.add(i);
        end = i + bootStepLen[bootIdx];
        if (end >= N-1) {
          // reached finish
          temp.add(N-1);
          return;
        }
      }
    }
  }
}