import java.io.PrintWriter;
import java.util.Scanner;

public class Shuffle {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("shuffle.in"));
    PrintWriter pw = new PrintWriter("shuffle.out");

    int N = sc.nextInt();

    int[] to = new int[N];
    for (int i = 0; i < N; i++) {
      to[i] = sc.nextInt()-1;
    }

    int[] visited = new int[N];
    int totalCount = 0;

    for (int i = 0; i < N; i++) {
      if (visited[i] == 1 || visited[i] == 2) continue;

      // find loop
      int cur = i;
      
      boolean[] path = new boolean[N];

      while (visited[cur] == 0) {
        visited[cur] = 1;
        path[cur] = true;
        cur = to[cur];
      }

      if (visited[cur] == 1 && path[cur]) {
        // just found
        int count = 1;
        int target = cur;
        cur = to[cur];
        visited[cur] = 2;
        while (cur != target) {
          count++;
          cur = to[cur];
          visited[cur] = 2;
        }
        totalCount+=count;
      }
    }

    pw.println(totalCount);

    sc.close();
    pw.close();
  }
}