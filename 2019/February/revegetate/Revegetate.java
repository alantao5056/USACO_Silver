import java.util.*;
import java.io.PrintWriter;

public class Revegetate {
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("revegetate.in"));
    PrintWriter pw = new PrintWriter("revegetate.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    List<Integer>[] connection = new ArrayList[N+1];

    for (int i = 0; i < M; i++) {
      sc.next();
      int a = sc.nextInt();
      int b = sc.nextInt();

      if (connection[a] == null) {
        connection[a] = new ArrayList<>();
      }
      connection[a].add(b);

      if (connection[b] == null) {
        connection[b] = new ArrayList<>();
      }
      connection[b].add(a);
    }

    // find groups
    int[] groups = new int[N+1];
    int curGroupNum = 0;

    for (int i = 1; i < N+1; i++) {
      if (groups[i] != 0) continue;
      curGroupNum++;

      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);
      groups[i] = curGroupNum;

      while (!q.isEmpty()) {
        int cur = q.poll();

        if (connection[cur] == null) continue;
        for (int n : connection[cur]) {
          if (groups[n] == 0) {
            groups[n] = curGroupNum;
            q.add(n);
          }
        }
      }
    }
    pw.print(1);
    for (int i = 0; i < curGroupNum; i++) {
      pw.print(0);
    }
    pw.println();

    pw.close();
    sc.close();
  }
}
