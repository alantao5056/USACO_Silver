import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Moop {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("moop.in"));
    PrintWriter pw = new PrintWriter("moop.out");
    N = sc.nextInt();

    Particle[] particles = new Particle[N];
    
    // solve
    for (int i = 0; i < N; i++) {
      particles[i] = new Particle(sc.nextInt(), sc.nextInt());

      for (int j = 0; j < i; j++) {
        if (particles[i].canInteract(particles[j])) {
          particles[i].nbs.add(particles[j]);
          particles[j].nbs.add(particles[i]);
        }
      }
    }

    // bfs
    int count = 0;
    for (int i = 0; i < N; i++) {
      if (particles[i].visited) continue;
      // new group
      count++;

      ArrayDeque<Particle> q = new ArrayDeque<>();

      q.add(particles[i]);
      particles[i].visited = true;

      while (!q.isEmpty()) {
        Particle curP = q.poll();

        for (Particle p : curP.nbs) {
          if (!p.visited) {
            p.visited = true;
            q.add(p);
          }
        }
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static class Particle {
    int a;
    int b;
    boolean visited;
    List<Particle> nbs = new ArrayList<>();

    public Particle(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public boolean canInteract(Particle p) {
      // smaller
      if (a <= p.a && b <= p.b) {
        return true;
      }
      // larger
      if (a >= p.a && b >= p.b) {
        return true;
      }
      return false;
    }

    @Override
    public String toString() {
      return String.format("%d %d", a, b);
    }
  }
}