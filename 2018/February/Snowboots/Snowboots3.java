import java.io.PrintWriter;
import java.io.File;
import java.util.*;

/**
 * a brute force method of trying all the possibilities of the boots
 * But recursive
 * 10/10
 */

public class Snowboots3 {
  static int N;
  static int B;
  static int[] depths;
  static int[] bootDepths;
  static int[] bootStepLen;
  static Set<Integer> canReach;
  static int result = Integer.MAX_VALUE;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new File("snowboots.in"));
    PrintWriter pw = new PrintWriter("snowboots.out");
    N = sc.nextInt();
    B = sc.nextInt();
    depths = new int[N];
    bootDepths = new int[B];
    bootStepLen = new int[B];
    canReach = new HashSet<>();
    canReach.add(0);
    visited = new boolean[B][N];
    visited[0][0] = true;

    for (int i = 0; i < N; i++) {
      depths[i] = sc.nextInt();
    }
    
    for (int i = 0; i < B; i++) {
      bootDepths[i] = sc.nextInt();
      bootStepLen[i] = sc.nextInt();
    }

    // solve
    recursive(0, 0);

    pw.println(result);

    sc.close();
    pw.close();
  }

  private static void recursive(int snowIdx, int bootIdx) {
    if (bootIdx > B-1) {
      return;
    }

    if (bootDepths[bootIdx] < depths[snowIdx]) {
      recursive(snowIdx, bootIdx+1);
      return;
    }

    if (snowIdx == N-1) {
      result = Math.min(result, bootIdx);
      return;
    }

    recursive(snowIdx, bootIdx+1);

    int end = snowIdx + bootStepLen[bootIdx];

    for (int i = snowIdx+1; i <= Math.min(N-1, end); i++) {
      if (bootDepths[bootIdx] >= depths[i] && !visited[bootIdx][i]) {
        // can step
        visited[bootIdx][i] = true;
        recursive(i, bootIdx);
      }
    }
  }
}
