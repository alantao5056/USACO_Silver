import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Closing4 {
  static int N;
  static int M;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("closing.in"));
    PrintWriter pw = new PrintWriter("closing.out");

    N = sc.nextInt();
    M = sc.nextInt();

    List<Integer>[] connection = new ArrayList[N+1];

    for (int i = 0; i < M; i++) {
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

    int[] order = new int[N+1];
    for (int i = 1; i < N+1; i++) {
      order[i] = sc.nextInt();
    }

    int[] groups = new int[N+1];
    boolean[] result = new boolean[N+1];

    int count = 0;

    for (int i = N; i >= 1; i--) {
      int cur = order[i];
      groups[cur] = -1;
      count++;

      if(connection[cur] != null) {
        for (int n : connection[cur]) {
          if (groups[n] != 0) {
            // opened
            int curN = n;
            while (groups[curN] > -1) {
              curN = groups[curN];
            }
            if (curN==cur) continue;

            if (groups[cur] < groups[curN]) {
              // curN merge to cur
              groups[cur] += groups[curN];
              groups[curN] = cur;
            } else {
              groups[curN] += groups[cur];
              groups[cur] = curN;
              cur = curN;
            }
            count--;
          }
        }
      }

      result[i] = count == 1;
    }

    for (int i = 1; i < N+1; i++) {
      pw.println(result[i] ? "YES":"NO");
    }

    sc.close();
    pw.close();
  }
}