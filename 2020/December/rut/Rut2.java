import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Rut2 {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("rut.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    List<Cow> eCows = new ArrayList<>();
    List<Cow> nCows = new ArrayList<>();
    Cow[] allCows = new Cow[N];

    for (int i = 0; i < N; i++) {
      char curDir = sc.next().charAt(0);
      Cow curCow = new Cow(curDir, sc.nextInt(), sc.nextInt(), i);
      allCows[i] = curCow;

      if (curDir == 'E') eCows.add(curCow);
      else nCows.add(curCow);
    }

    eCows.sort(new SortByY());
    nCows.sort(new SortByX());

    for (var nCow : nCows) {
      for (var eCow : eCows) {
        if (eCow.stopped || nCow.x < eCow.x || nCow.y > eCow.y) continue;

        int deltaX = nCow.x - eCow.x;
        int deltaY = eCow.y - nCow.y;
        if (deltaX > deltaY) {
          // e stopped by n
          eCow.stopped = true;
          nCow.blocked += eCow.blocked+1;
        } else if (deltaX < deltaY) {
          // n stopped by e
          nCow.stopped = true;
          eCow.blocked += nCow.blocked+1;
          break;
        }
      }
    }

    for (var c : allCows) {
      System.out.println(c.blocked);
    }

    sc.close();
  }

  public static class Cow {
    public char direction;
    public int x;
    public int y;
    public boolean stopped;
    int index;
    public int blocked = 0;

    public Cow (char direction, int x, int y, int index) {
      this.direction = direction;
      this.x = x;
      this.y = y;
      this.index = index;
    }

    @Override
    public String toString() {
      return Integer.toString(index);
    }
  }

  public static class SortByX implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2) {
      return c1.x - c2.x;
    }
  }
  
  public static class SortByY implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2) {
      return c1.y - c2.y;
    }
  }
}