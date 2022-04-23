import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Rut {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("rut.in"));
    // Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();

    var cows = new Cow[N];
    Set<Integer>[] colide = new HashSet[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(sc.next().charAt(0), sc.nextInt(), sc.nextInt(), i);
      colide[i] = new HashSet<>();
    }

    for (int i = 0; i < N; i++) {
      Cow startCow = cows[i];
      if (cows[i].direction == 'E') Arrays.sort(cows, new SortByX());
      else Arrays.sort(cows, new SortByY());

      for (int j = i+1; j < N; j++) {
        if (j == startCow.id) continue;
        if (cows[i].direction != cows[j].direction) {
          // possible colide
          Cow east;
          Cow north;
          if (cows[i].direction == 'E') {
            east = cows[i];
            north = cows[j];
          } else {
            east = cows[j];
            north = cows[i];
          }

          if (north.x > east.x && north.y < east.y) {
            // will colide
            if (north.x - east.x > east.y - north.y) {
              // east colide north
              colide[north.id].add(east.id);
              // if east already colided with this, then it can't colide with anything else after this
              Iterator<Integer> itr = colide[east.id].iterator();

              while (itr.hasNext()) {
                int cur = itr.next();
                if (cows[cur].x > north.x) {
                  itr.remove();
                }
              }
            } else if (north.x - east.x < east.y - north.y) {
              // north colide east
              colide[east.id].add(north.id);
              // if north already colided with this, then it can't colide with anything else after this
              Iterator<Integer> itr = colide[north.id].iterator();

              while (itr.hasNext()) {
                int cur = itr.next();
                if (cows[cur].y > east.y) {
                  itr.remove();
                }
              }
            }
          }
        }
      }
    }

    sc.close();
  }

  public static class Cow {
    public char direction;
    public int x;
    public int y;
    public int id;
    public Cow(char direction, int x, int y, int id) {
      this.direction = direction;
      this.x = x;
      this.y = y;
      this.id = id;
    }

    @Override
    public String toString() {
      return String.format("%c %d %d", direction, x, y);
    }
  }
  public static class SortByX implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2) {
      if (c1.x > c2.x) {
        return 1;
      }
      if (c1.x < c2.x) {
        return -1;
      }
      return 0;
    }
  }
  public static class SortByY implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2) {
      if (c1.y > c2.y) {
        return 1;
      }
      if (c1.y < c2.y) {
        return -1;
      }
      return 0;
    }
  }
}