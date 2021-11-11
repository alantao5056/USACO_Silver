import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Swap {
  static StringTokenizer st;
  static PrintWriter pr;
  static int N, M, K;
  static int[] cows, starts, ends;

  private static int getInt() {
    return Integer.parseInt(st.nextToken());
  }

  private static void swap(int a, int b, int[] cur) {
    int temp = cur[a];
    cur[a] = cur[b];
    cur[b] = temp;
  }

  private static void flip(int s, int e, int[] cur) {
    while (s < e) {
      swap(s, e, cur);
      s++;
      e--;
    }
  }

  private static int[] solve(int[] cows) {
    // do swapping once
    for (int i = 0; i < M; i++) {
      flip(starts[i], ends[i], cows);
    }

    // calculate whereToGo arr
    int[] whereToGo = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      whereToGo[cows[i]] = i;
    }
    
    // get cycles
    boolean[] visited = new boolean[N + 1];
    List<List<Integer>> cycles = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      if (!visited[i]) {
        // dfs
        List<Integer> cycle = new ArrayList<>();

        int cur = i;
        do {
          cycle.add(cur);
          visited[cur] = true;
          cur = whereToGo[cur];
        } while (cur != i);

        cycles.add(cycle);
      }
    }

    // solve for result
    int[] result = new int[N + 1];

    for (List<Integer> cycle : cycles) {
      int length = cycle.size();
      int remainder = K % length;
      for (int i = 0; i < length; i++) {
        int offset = remainder + i;
        result[cycle.get(offset >= length ? offset - length : offset)] = cycle.get(i);
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException{
    st = new StringTokenizer(new String(Files.readAllBytes(Paths.get("swap.in"))));
    pr = new PrintWriter("swap.out");

    N = getInt();
    M = getInt();
    K = getInt();

    cows = new int[N + 1];
    starts = new int[M];
    ends = new int[M];

    for (int i = 1; i < N + 1; i++) {
      cows[i] = i;
    }

    for (int i = 0; i < M; i++) {
      starts[i] = getInt();
      ends[i] = getInt();
    }

    int[] result = solve(cows);

    for (int i = 1; i < N + 1; i++) {
      pr.println(result[i]);
    }

    pr.close();
  }
}