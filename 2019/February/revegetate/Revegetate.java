import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Revegetate {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("revegetate.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("revegetate.out"));

    int N = sc.nextInt();
    int M = sc.nextInt();

    ArrayList<Integer>[] cows = new ArrayList[N+1];

    for (int i = 1; i < N+1; i++) {
      cows[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      sc.next();
      int a = sc.nextInt();
      int b = sc.nextInt();

      cows[a].add(b);
      cows[b].add(a);
    }

    // get the groups

    boolean[] visited = new boolean[N + 1];
    int groups = 0;

    for (int i = 1; i < N+1; i++) {
      if (visited[i]) continue;
      groups++;
      Queue<Integer> q = new LinkedList<>();
      q.add(i);

      while (!q.isEmpty()) {
        int cur = q.poll();
        visited[cur] = true;
        for (int n : cows[cur]) {
          if (!visited[n]) q.add(n);
        }
      }
    }

    pw.print("1");
    for (int i = 0; i < groups; i++) {
      pw.print("0");
    }

    pw.println();

    pw.close();
    sc.close();
  }
}