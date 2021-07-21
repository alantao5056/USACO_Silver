import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Milkvisits2 {
  private static int N;
  private static int M;
  private static String MILKTYPES;
  private static LinkedList<Integer>[] connection;
  private static int[] groups;

  public static boolean ifMilkInPath(int start, int end, char milkType, String MILKTYPES) {
    if (groups[start] == groups[end]) {
      if (MILKTYPES.charAt(start) == milkType) {
        return true;
      }
      return false;
    }
    return true;
  }

  public static void main(String[] args) throws IOException{
    String fileInput = new String(Files.readAllBytes(Paths.get("milkvisits.in")));
    StringTokenizer st = new StringTokenizer(fileInput);
    PrintWriter pr = new PrintWriter(new FileWriter("milkvisits.out"));

    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());

    final String MILKTYPES = ' ' + st.nextToken();

    // initilizing connection
    connection = new LinkedList[N + 1];
    int x, y;
    for (int i = 0; i < N - 1; i++) {
      x = Integer.parseInt(st.nextToken());
      y = Integer.parseInt(st.nextToken());
      
      if (connection[x] == null) {
        connection[x] = new LinkedList<Integer>();
      }
      if (connection[y] == null) {
        connection[y] = new LinkedList<Integer>();
      }

      connection[x].add(y);
      connection[y].add(x);
    }

    // grouping
    groups = new int[N + 1];
    int curGroup = 1;
    for (int i = 1; i < N + 1; i++) {
      if (groups[i] == 0) {
        Queue<Integer> q = new LinkedList<>();
        groups[i] = curGroup;
        q.add(i);
        // bfs this area
        while (!q.isEmpty()) {
          int cur = q.poll();
          char curMilkType = MILKTYPES.charAt(cur);
          for (int neighbor : connection[cur]) {
            if (MILKTYPES.charAt(neighbor) == curMilkType && groups[neighbor] == 0) {
              // in the same group
              groups[neighbor] = curGroup;
              q.add(neighbor);
            }
          }
        }
        curGroup++;
      }
    }

    int a, b;
    char c;
    for (int i = 0; i < M; i++) {
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      c = st.nextToken().charAt(0);

      pr.print(ifMilkInPath(a, b, c, MILKTYPES) ? '1' : '0');
    }

    pr.println();
    pr.close();
  }
}
