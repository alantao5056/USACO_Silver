import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Highcard {
  public static int getMaxPoints(List<Integer> elsieList, Integer N) {
    int count = 0;
    Integer last = -1;
    Integer curCount = 0;
    for (int i = 0; i < N; i++) {
      if (last + 1 != elsieList.get(i)) {
        if (last != -1) {
          if (elsieList.get(i) - last - 1 < curCount) {
            curCount -= elsieList.get(i) - last - 1;
            count += elsieList.get(i) - last - 1;
          } else {
            count += curCount;
            curCount = 0;
          }
        }
      }
      last = elsieList.get(i);
      curCount++;
    }

    // getting the rest
    if (N*2 > elsieList.get(N - 1)) {
      // more cards from bessie
      if (N*2 - elsieList.get(N - 1) >= curCount) {
        return count + curCount;
      }
      return count + (N*2 - elsieList.get(N - 1));
    }

    return count;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("highcard.in"));
    String[] items = reader.readLine().split("\\s+");
    Integer N = Integer.parseInt(items[0]);

    List<Integer> elsieList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      String num = reader.readLine();
      elsieList.add(Integer.parseInt(num));
    }

    reader.close();
    Collections.sort(elsieList);

    BufferedWriter writer = new BufferedWriter(new FileWriter("highcard.out"));
    writer.write(String.valueOf(getMaxPoints(elsieList, N)));
    writer.write('\n');
    writer.close();
  }
}