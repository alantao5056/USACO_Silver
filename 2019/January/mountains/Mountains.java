import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Math;

class Mountains {
  static List<List<Integer>> peaks = new ArrayList<>();

  public static int getCovered() {
    int maxBase = -1;
    int count = 0;
    for (int i = 0; i < peaks.size() - 1; i++) {
      int rightBase = peaks.get(i).get(0) + peaks.get(i).get(1);
      if (peaks.get(i).get(0) - peaks.get(i).get(1) == peaks.get(i + 1).get(0) - peaks.get(i + 1).get(1)) {
        // left point the same
        count++;
      }

      else if (rightBase < maxBase) {
        // right covered by max
        count++;
      }

      maxBase = Math.max(maxBase, peaks.get(i).get(0) + peaks.get(i).get(1));
    }

    if (peaks.get(peaks.size() - 1).get(0) + peaks.get(peaks.size() - 1).get(1) < maxBase) {
      count++;
    }

    return peaks.size() - count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("mountains.in"));
    int N = Integer.parseInt(reader.readLine());

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
        if (c1.get(0) - c1.get(1) > c2.get(0) - c2.get(1)) {
          return 1;
        }
        if (c1.get(0) - c1.get(1) < c2.get(0) - c2.get(1)) {
          return -1;
        }
        if (c1.get(1) > c2.get(1)) {
          return -1;
        }
        return 1;
      }
    });

    // for (int i = 0; i < peaks.size(); i++) {
    //   System.out.println(Integer.toString(peaks.get(i).get(0)) + " " + Integer.toString(peaks.get(i).get(1)));
    // }
    // System.out.println(getCovered());
    BufferedWriter writer = new BufferedWriter(new FileWriter("mountains.out"));
    writer.write(Integer.toString(getCovered()) + "\n");
    writer.close();
  }
}