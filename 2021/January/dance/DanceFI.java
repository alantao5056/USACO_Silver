import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class DanceFI {
  public static void main(String[] args) throws IOException{
    StringTokenizer st = new StringTokenizer(new String(Files.readAllBytes(Paths.get("dance.in"))));
    PrintWriter pw = new PrintWriter("dance.out");

    int N = getInt(st);
    int K = getInt(st);
    int[] pos = new int[N + 1];
    int[] swapA = new int[K];
    int[] swapB = new int[K];

    for (int i = 1; i < N + 1; i++) {
      pos[i] = i;
    }

    int a, b;
    for (int i = 0; i < K; i++) {
      a = getInt(st);
      b = getInt(st);
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
        Set<Integer> curCycle = new HashSet<>();
        
        while (!visited[curCow]) {
          curCycle.add(curCow);
          whichCycle[curCow] = curCycleIndex;
          visited[curCow] = true;
          curCow = pos[curCow];
        }
        
        cycles.add(curCycle);
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
      pw.println(cycles.get(whichCycle[i]).size());
    }

    pw.close();
  }

  private static int getInt(StringTokenizer st) {
    return Integer.parseInt(st.nextToken());
  }
}