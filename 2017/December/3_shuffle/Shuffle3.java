import java.util.*;
import java.io.PrintWriter;

/**
 * using the in-degree method
 * finding the 0 in-degrees and deleting them
 * and be left with cycle
 */

public class Shuffle3 {
  static int N;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("shuffle.in"));
    PrintWriter pw = new PrintWriter("shuffle.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();

    int[] next = new int[N];
    int[] inDegree = new int[N];

    for (int i = 0; i < N; i++) {
      next[i] = sc.nextInt()-1;
      inDegree[next[i]]++;
    }

    ArrayDeque<Integer> q = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {
      if (inDegree[i] == 0) {
        q.add(i);
      }
    }

    while (!q.isEmpty()) {
      int cur = q.poll();

      inDegree[next[cur]]--;
      if (inDegree[next[cur]] == 0) {
        q.add(next[cur]);
      }
    }

    int result = 0;
    for (int i = 0; i < N; i++) {
      if (inDegree[i] != 0) {
        result++;
      }
    }

    pw.println(result);

    sc.close();
    pw.close();
  }
}