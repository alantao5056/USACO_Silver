import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Moop2 {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("moop.in"));
    PrintWriter pw = new PrintWriter("moop.out");
    N = sc.nextInt();
    
    // solve

    Particle[] particles = new Particle[N];
    for (int i = 0; i < N; i++) {
      particles[i] = new Particle(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(particles, new Comparator<Particle>() {
      public int compare(Particle p1, Particle p2) {
        if (p1.a > p2.a) {
          return 1;
        } else if (p1.a < p2.a) {
          return -1;
        } else {
          return p1.b - p2.b;
        }
      }
    });

    int[] minL = new int[N];
    int curMin = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      curMin = Math.min(curMin, particles[i].b);
      minL[i] = curMin;
    }

    int[] maxR = new int[N];
    int curMax = Integer.MIN_VALUE;
    for (int i = N-1; i >= 0; i--) {
      curMax = Math.max(curMax, particles[i].b);
      maxR[i] = curMax;
    }
    
    int count = 1;
    for (int i = 0; i < N-1; i++) {
      if (minL[i] > maxR[i+1]) {
        count++;
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static class Particle {
    int a;
    int b;

    public Particle(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public String toString() {
      return String.format("%d %d", a, b);
    }
  }
}