import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Triangles {
  private static int N;
  private static StringTokenizer st;
  private static PrintWriter pw;
  private static Point[] points;

  public static class Point {
    public int y;
    public int x;
    public long sumx;
    public long sumy;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return String.format("(%s, %s)", x, y);
    }
  }

  
  private static int getInt() {
    return Integer.parseInt(st.nextToken());
  }

  static class sortBasedOnX implements Comparator<Point> {
    public int compare(Point p1, Point p2) {
      if (p1.x > p2.x) {
        return 1;
      }
      if (p1.x == p2.x) {
        return p1.y > p2.y ? 1 : -1;
      }
      return -1;
    }
  }

  static class sortBasedOnY implements Comparator<Point> {
    public int compare(Point p1, Point p2) {
      if (p1.y > p2.y) {
        return 1;
      }
      if (p1.y == p2.y) {
        return p1.x > p2.x ? 1 : -1;
      }
      return -1;
    }
  }

  public static void main(String[] args) throws IOException{
    st = new StringTokenizer(new String(Files.readAllBytes(Paths.get("triangles.in"))));
    pw = new PrintWriter("triangles.out");
    N = getInt();

    points = new Point[N];

    for (int i = 0; i < N; i++) {
      points[i] = new Point(getInt() + 10000, getInt() + 10000);
    }

    // do y
    Arrays.sort(points, new sortBasedOnX());
    int i = 0;
    while (i < N) {
      int total = 0;
      int j = i + 1;
      while (j < N && points[i].x == points[j].x) {
        points[i].sumy += points[j].y - points[i].y;
        j++;
        total++;
      }
      j = i + 1;
      int adds = 1 - total;
      long prev = points[i].sumy;
      while (j < N && points[i].x == points[j].x) {
        points[j].sumy = prev + (points[j].y - points[j-1].y) * adds;
        prev = points[j].sumy;
        adds += 2;
        j++;
      }
      i += total + 1;
    }

    // do x
    Arrays.sort(points, new sortBasedOnY());
    i = 0;
    while (i < N) {
      int total = 0;
      int j = i + 1;
      while (j < N && points[i].y == points[j].y) {
        points[i].sumx += points[j].x - points[i].x;
        j++;
        total++;
      }
      j = i + 1;
      int adds = 1 - total;
      long prev = points[i].sumx;
      while (j < N && points[i].y == points[j].y) {
        points[j].sumx = prev + (points[j].x - points[j-1].x) * adds;
        prev = points[j].sumx;
        adds += 2;
        j++;
      }
      i += total + 1;
    }

    // get result
    long result = 0;
    for (int k = 0; k < N; k++) {
      result += (points[k].sumx * points[k].sumy) % 1000000007;
      result %= 1000000007;
    }

    pw.println(result);

    pw.close();
  }
}