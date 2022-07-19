import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Sort {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("sort.in"));
    PrintWriter pw = new PrintWriter("sort.out");
    N = sc.nextInt();
    
    // solve
    int[] arr = new int[N];
    TreeMap<Integer, List<Integer>> idxMap = new TreeMap<>();

    for (int i = 0; i < N; i++) {
      int cur = sc.nextInt()-1;

      if (idxMap.containsKey(cur)) {
        idxMap.get(cur).add(i);
        idxMap.put(cur, idxMap.get(cur));
      } else {
        List<Integer> curList = new ArrayList<>();
        curList.add(i);
        idxMap.put(cur, curList);
      }
      arr[i] = cur;
    }

    int maxDistance = 0;

    int curIdx = 0;
    for (List<Integer> value : idxMap.values()) {
      for (int v : value) {
        maxDistance = Math.max(maxDistance, v-curIdx);
        curIdx++;
      }
    }

    pw.println(maxDistance+1);

    sc.close();
    pw.close();
  }
}