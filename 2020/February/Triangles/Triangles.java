import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.AbstractMap;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Triangles {
  private static int N;
  private static StringTokenizer st;
  private static PrintWriter pw;
  private static List<Map.Entry<Integer, Integer>> xy;
  private static long[][] sumx;
  private static long[][] sumy;

  
  private static int getInt() {
    return Integer.parseInt(st.nextToken());
  }

  static class MyComparator implements Comparator<Map.Entry<Integer, Integer>> {
    public int compare(Map.Entry<Integer, Integer> c1, Map.Entry<Integer, Integer> c2) {
      if (c1.getKey() > c2.getKey()) {
        return 0;
      }
      if (c1.getKey() == c2.getKey()) {
        return c1.getValue() > c2.getValue() ? 0 : 1;
      }
      return 1;
    }
  }

  public static void main(String[] args) throws IOException{
    st = new StringTokenizer(new String(Files.readAllBytes(Paths.get("triangles.in"))));
    pw = new PrintWriter("triangles.out");
    N = getInt();

    // initializing xy array
    xy = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      xy.add(new AbstractMap.SimpleEntry<Integer, Integer>(getInt() + 100000, getInt() + 100000));
    }

    // sort
    Collections.sort(xy, new MyComparator());

    // turning array into sums
    sumx = new long[20001][20001];
    sumy = new long[20001][20001];

    for (int i = 0; i < N + 1; i++) {
      int j = i + 1;
      int x = xy.get(i).getKey();
      int y = xy.get(i).getValue();

      while (x != xy.get(j).getKey()) {
        sumx[x][y] += xy.get(j).getValue() - xy.get(i).getValue();
      }
    }

    pw.close();
  }
}