import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Robot2 {
  private static int N;
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("test/2.in"));
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();

    long targetX = sc.nextInt();
    long targetY = sc.nextInt();

    List<Coord> result1 = getResult(targetX, targetY, N/2, sc);
    List<Coord> result2 = getResult(targetX, targetY, N%2 == 0 ? N/2 : N/2+1, sc);

    Collections.sort(result2, (o1, o2) -> Long.valueOf(o1.x).compareTo(Long.valueOf(o2.x)));
    List<List<Coord>> newResult2 = new ArrayList<>();
    long curX = -1;
    int curIdx = -1;
    for (var r : result2) {
      if (r.x != curX) {
        newResult2.add(new ArrayList<>());
        curX = r.x;
        curIdx++;
      }
      newResult2.get(curIdx).add(r);
    }

    int[] result = new int[N];
    for (var r1 : result1) {
      Coord targetCoord = new Coord(targetX - r1.x, targetY - r1.y, 0);
      List<Coord> targetCoords = binarySearch(targetCoord, newResult2);
      if (targetCoords == null) continue;

      for (var t : targetCoords) {
        if (t.x == targetCoord.x && t.y == targetCoord.y) {
          // found
          int usedTimes = r1.usedTimes + t.usedTimes;
          result[usedTimes-1]++;
        }
      }
    }

    for (int r : result) {
      System.out.println(r);
    }

    sc.close();
  }

  private static List<Coord> binarySearch(Coord target, List<List<Coord>> result2) {
    int l = 0, r = result2.size() - 1;
    while (l <= r) {
      int m = l + (r - l) / 2;

      // Check if x is present at mid
      if (result2.get(m).get(0).x == target.x) {
        return result2.get(m);
      }
      // If x greater, ignore left half
      if (result2.get(m).get(0).x < target.x) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return null;
  }

  private static List<Coord> getResult(long targetX, long targetY, int times, Scanner sc) {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0, 0));

    for (int i = 0; i < times; i++) {
      long x = sc.nextLong();
      long y = sc.nextLong();
      // coords.add(new Coord(x, y, 1));
      int coordsSize = coords.size();
      for (int j = 0; j < coordsSize; j++) {
        coords.add(new Coord(coords.get(j).x + x, coords.get(j).y + y, coords.get(j).usedTimes+1));
      }
    }

    return coords;
  }

  public static class Coord {
    long x;
    long y;
    int usedTimes;
    public Coord(long x, long y, int usedTimes) {
      this.x = x;
      this.y = y;
      this.usedTimes = usedTimes;
    }
  }
}
