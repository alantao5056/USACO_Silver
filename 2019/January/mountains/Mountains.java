import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Mountains {
  static List<List<Integer>> peaks = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("mountains.in"));
    int N = Integer.parseInt(reader.readLine());
    int count = N;

    for (int i = 0; i < N; i++) {
      String[] line = reader.readLine().split("\\s+");
      Integer x = Integer.parseInt(line[0]);
      Integer y = Integer.parseInt(line[1]);

      List<Integer> cord = new ArrayList<>();
      cord.add(x);
      cord.add(y);
      peaks.add(cord);
    }

    reader.close();

    // sort custom comparator
    Collections.sort(peaks, new Comparator<List<Integer>>() {
      @Override
      public int compare(List<Integer> c1, List<Integer> c2) {
        if (c1.get(0) + c1.get(1) > c2.get(0) + c2.get(1)) {
          return 1;
        }
        if (c1.get(0) + c1.get(1) < c2.get(0) + c2.get(1)) {
          return -1;
        }
        return 0;
      }
    });

    System.out.println(count);
  }
}