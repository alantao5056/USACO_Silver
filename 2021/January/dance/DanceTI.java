import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DanceTI {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = getInt(sc);
    int K = getInt(sc);
    int[] pos = new int[N + 1];
    int[] swapA = new int[K];
    int[] swapB = new int[K];

    for (int i = 1; i < N + 1; i++) {
      pos[i] = i;
    }

    int a, b;
    for (int i = 0; i < K; i++) {
      a = getInt(sc);
      b = getInt(sc);
      swapA[i] = a;
      swapB[i] = b;
      // a and b swap
      int temp = pos[a];
      pos[a] = pos[b];
      pos[b] = temp;
    }

    List<Set<Integer>> cycles = new ArrayList<>();
    int[] whichCycle = new int[N + 1];
    int curCycleIndex = 0;
    boolean[] visited = new boolean[N + 1];

    for (int i = 1; i < N + 1; i++) {
      if (!visited[i]) {
        // start
        int curCow = i;
        cycles.add(new HashSet<>());
        Set<Integer> curCycle = cycles.get(curCycleIndex);

        while (!visited[curCow]) {
          curCycle.add(curCow);
          whichCycle[curCow] = curCycleIndex;
          visited[curCow] = true;
          curCow = pos[curCow];
        }

        curCycleIndex++;
      }
    }
    for (int i = 1; i < N + 1; i++) {
      pos[i] = i;
    }

    for (int i = 0; i < K; i++) {
      a = swapA[i];
      b = swapB[i];

      cycles.get(whichCycle[pos[a]]).add(b);
      cycles.get(whichCycle[pos[b]]).add(a);

      // swap
      int temp = pos[a];
      pos[a] = pos[b];
      pos[b] = temp;
    }

    // output
    for (int i = 1; i < N + 1; i++) {
      System.out.println(cycles.get(whichCycle[i]).size());
    }

    sc.close();
  }

  private static int getInt(Scanner sc) {
    return sc.nextInt();
  }
}