import java.io.*;
import java.util.*;

public class Closing2 {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("closing.in"));
    String[] NM = reader.readLine().split("\\s+");
    int N = Integer.parseInt(NM[0]);
    int M = Integer.parseInt(NM[1]);
    int[] closingOrder = new int[N];
    List<List<Integer>> connection = new ArrayList<>();
    boolean[] closed = new boolean[N + 1];

    // initializing connection
    for (int i = 0; i < N + 1; i++) {
      connection.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      // parings
      String[] line = reader.readLine().split("\\s+");
      int first = Integer.parseInt(line[0]);
      int second = Integer.parseInt(line[1]);
      connection.get(first).add(second);
      connection.get(second).add(first);
    }

    for (int i = 0; i < N; i++) {
      // adding to closing order
      closingOrder[i] = Integer.parseInt(reader.readLine());
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter("closing.out"));

    writer.write(booleanToStr(isConnected(connection, closed, 1, N, N)) + "\n");

    for (int curClosing = 0; curClosing < N - 1; curClosing++) {
      closed[closingOrder[curClosing]] = true;
      writer.write(booleanToStr(
        isConnected(connection, closed, closingOrder[curClosing + 1], N, N - curClosing - 1)) + "\n");
    }

    reader.close();
    writer.close();
  }

  private static String booleanToStr(boolean b) {
    return b ? "YES" : "NO";
  }

  private static boolean isConnected(List<List<Integer>> connection, boolean[] closed, int start, int N, int expectCount) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];
    queue.add(start);
    int counter = 0;
    visited[start] = true;
    while(!queue.isEmpty()) {
      int cur = queue.poll();
      counter++;
      for (int n : connection.get(cur)) {
        if (!visited[n] && !closed[n]) {
          queue.add(n);
          visited[n] = true;
        }
      }
    }
    return counter == expectCount;
  }
}
