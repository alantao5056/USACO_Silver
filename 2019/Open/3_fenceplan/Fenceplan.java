import java.io.PrintWriter;
import java.util.*;

public class Fenceplan {
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("fenceplan.in"));
    PrintWriter pw = new PrintWriter("fenceplan.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(sc.nextInt(), sc.nextInt());
    }

    for (int i = 0; i < M; i++) {
      int a = sc.nextInt()-1;
      int b = sc.nextInt()-1;
      cows[a].neighbors.add(cows[b]);
      cows[b].neighbors.add(cows[a]);
    }

    int minPerimeter = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {
      if (cows[i].visited) continue;

      // bfs
      Queue<Cow> q = new LinkedList<>();
      q.add(cows[i]);
      cows[i].visited = true;

      int maxX = 0;
      int maxY = 0;
      int minX = Integer.MAX_VALUE;
      int minY = Integer.MAX_VALUE;


      while (!q.isEmpty()) {
        Cow curCow = q.poll();
        maxX = Math.max(maxX, curCow.x);
        maxY = Math.max(maxY, curCow.y);
        minX = Math.min(minX, curCow.x);
        minY = Math.min(minY, curCow.y);

        for (Cow c : curCow.neighbors) {
          if (!c.visited) {
            q.add(c);
            c.visited = true;
          }
        }

      }
      minPerimeter = Math.min(minPerimeter, 2*(maxX-minX)+2*(maxY-minY));
    }

    pw.println(minPerimeter);

    pw.close();
    sc.close();
  }

  public static class Cow {
    int x;
    int y;
    boolean visited = false;
    List<Cow> neighbors = new ArrayList<>();

    public Cow (int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
