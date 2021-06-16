import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Closing {
  static List<List<Integer>> connection;
  static HashSet<Integer> closed;
  static Integer[] closingOrder;
  static Integer curClosing;
  static Integer N;

  public static String booleanToStr(Boolean b) {
    if (b) {
      return "YES";
    }
    return "NO";
  }

  public static Boolean isConnected(Integer curClosed) {
    HashSet<Integer> visited = new HashSet<>();
    Queue<Integer> todoQueue = new LinkedList<>();
    closed.add(curClosed);
    todoQueue.add(closingOrder[curClosing+1]);

    do {
      Integer cur = todoQueue.poll();
      visited.add(cur);
      for (Integer path : connection.get(cur)) {
        if (!visited.contains(path) && !closed.contains(path)) {
          todoQueue.add(path);
        }
      }
    } while (!todoQueue.isEmpty());

    return (visited.size() == N - curClosing - 1);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("closing.in"));
    String[] NM = reader.readLine().split("\\s+");
    N = Integer.parseInt(NM[0]);
    Integer M = Integer.parseInt(NM[1]);
    closingOrder = new Integer[N];
    connection = new ArrayList<List<Integer>>(N+1);
    closed = new HashSet<>();

    // initializing connection
    for (int i = 0; i < N + 1; i++) {
      List<Integer> l = new ArrayList<>();
      connection.add(l);
    }

    for (int i = 0; i < M; i++) {
      // parings
      String[] line = reader.readLine().split("\\s+");
      Integer first = Integer.parseInt(line[0]);
      Integer second = Integer.parseInt(line[1]);
      connection.get(first).add(second);
      connection.get(second).add(first);
    }

    for (int i = 0; i < N; i++) {
      // adding to closing order
      closingOrder[i] = Integer.parseInt(reader.readLine());
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter("closing.out"));

    curClosing = -1;
    writer.write(booleanToStr(isConnected(-1)) + "\n");

    for (curClosing = 0; curClosing < N - 1; curClosing++) {
      writer.write(booleanToStr(isConnected(closingOrder[curClosing])) + "\n");
    }

    reader.close();
    writer.close();
  }
}