import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Teleport {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("teleport.in"));
    PrintWriter pw = new PrintWriter("teleport.out");
    N = sc.nextInt();
    final int bound = 100000000;
    int[] savesR = new int[2 * bound + 1];
    int[] savesL = new int[2 * bound + 1];
    // solve
    int total = 0;
    for (int i = 1; i <= N; i++) {
      int from = sc.nextInt() + bound;
      int to = sc.nextInt() + bound;
      total += Math.abs(from - to);

      if (from < to) {
        // right
        if (to <= bound) {
          // cant help
          continue;
        }
        if (from < bound) {
          // from < bound and to > bound
          int count = 0;
          for (int j = bound+1; j <= to; j++) {
            count++;
            savesR[j] += count;
          }
          for (int j = to + 1; j <= to + (to-bound) && j <= 2 * bound; j++) {
            count--;
            savesR[j] += count;
          }
        } else {
          // from >= bound and to > bound
          if (from + (from - bound) >= to) {
            continue;
          }
          int count = 0;
          for (int j = from + (from - bound); j <= to; j++) {
            count++;
            savesR[j] += count;
          }
          count--;
          for (int j = to +1; count > 0 && j <= 2 * bound; j++) {
            savesR[j] += count;
            count--;
          }
        }
      } else {
        // left
        if (to >= bound) {
          // cant help
          continue;
        }

        if (from > bound) {
          // from > bound and to < bound
          int count = 0;
          for (int j = bound - 1; j >= from; j--) {
            count++;
            savesL[j] = count;
          }
          for (int j = to - 1; count > 0 && j >= 0; j--)  {
            count--;
            savesL[j] = count;
          }
        } else {
          // from <= bound and to < bound
          if (from - (bound - from) <= to) {
            continue;
          }

          int count = 0;
          for (int j = from - (bound - from); j >= to; j--) {
            count++;
            savesL[j] += count;
          }

          for (int j = to - 1; count >= 0 && j >= 0; j--) {
            count--;
            savesL[j] += count;
          }
        }
      }
    }

    // get max
    int max = 0;
    for (int i = 0; i <= 2 * bound; i++) {
      max = Math.max(max, Math.max(savesL[i], savesR[i]));
    }

    pw.println(total-max);

    sc.close();
    pw.close();
  }
}