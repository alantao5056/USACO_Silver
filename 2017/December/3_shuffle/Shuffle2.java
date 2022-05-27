import java.util.*;
import java.io.PrintWriter;

/**
 * labeling each individual part as a group
 * and find cycles
 */

public class Shuffle2 {
  static int N;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("shuffle.in"));
    PrintWriter pw = new PrintWriter("shuffle.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();

    int[] next = new int[N];

    for (int i = 0; i < N; i++) {
      next[i] = sc.nextInt()-1;
    }

    // bfs
    int[] groups = new int[N];
    int curGroupNum = 1;
    int result = 0;

    for (int i = 0; i < N; i++) {
      if (groups[i] != 0) continue;

      int cur = i;
      while (groups[cur] == 0) {
        groups[cur] = curGroupNum;
        cur = next[cur];
      }

      if (groups[cur] == curGroupNum) {
        // found cycle
        int starting = cur;
        int length = 1;
        cur = next[cur];
        while (cur != starting) {
          length++;
          cur = next[cur];
        }

        result += length;
      }
      curGroupNum++;
    }
    
    pw.println(result);
    
    pw.close();
    sc.close();
  }
}
