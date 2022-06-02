import java.io.PrintWriter;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Closing3 {
  static int N;
  static int M;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("closing.in"));
    PrintWriter pw = new PrintWriter("closing.out");

    N = sc.nextInt();
    M = sc.nextInt();

    List<List<Integer>> connection = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      connection.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      int a = sc.nextInt()-1;
      int b = sc.nextInt()-1;
      connection.get(a).add(b);
      connection.get(b).add(a);
    }

    int[] order = new int[N];
    for (int i = 0; i < N; i++) {
      order[i] = sc.nextInt()-1;
    }

    LinkedList<Set<Integer>> groups = new LinkedList<>();
    int setCount = 0;
    boolean[] result = new boolean[N];

    boolean[] visited = new boolean[N];
    for (int i = N-1; i >= 0; i--) {
      int cur = order[i];
      visited[cur] = true;

      Set<Integer> newSet = new HashSet<>();
      newSet.add(cur);
      groups.add(newSet);
      setCount++;

      for (int n : connection.get(cur)) {
        if (visited[n]) {
          for (Set<Integer> s : groups) {
            if (s.contains(n)) {
              if (s.size() > newSet.size()) {
                s.addAll(newSet);
                groups.remove(newSet);
                newSet = s;
              } else {
                newSet.addAll(s);
                groups.remove(s);
              }
              setCount--;
              break;
            }
          }
        }
      }

      result[i] = setCount == 1;
    }

    for (int i = 0; i < N; i++) {
      pw.println(result[i] ? "YES":"NO");
    }

    sc.close();
    pw.close();
  }
}
