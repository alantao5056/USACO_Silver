import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Moocast {
  static int N;
  static Cow[] cows;
  static StringTokenizer st;

  static class Cow {
    private int x, y, radius, id;
    Cow (int x, int y, int radius, int id) {
      this.x = x;
      this.y = y;
      this.radius = radius;
      this.id = id;
    }
    public int getX() {
      return x;
    }
    public int getY() {
      return y;
    }
    public int getRadius() {
      return radius;
    }

    public int getId() {
      return id;
    }

    @Override
    public String toString() {
      return String.format("(%d, %d) #%d r: %d", x, y, id, radius);
    }
  }

  private static int nextInt() {
    return Integer.parseInt(st.nextToken());
  }

  private static Boolean ifAbleToBoardCast(Cow boardcastCow, Cow recieveingCow) {
    int deltaX = Math.abs(boardcastCow.getX() - recieveingCow.getX());
    int deltaY = Math.abs(boardcastCow.getY() - recieveingCow.getY());

    return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= boardcastCow.getRadius();
  }

  private static int getMaxBroadcastCows() {
    int maxCount = 0;
    for (Cow startingCow : cows) {
      Queue<Integer> q = new LinkedList<>();
      Set<Integer> visited = new HashSet<>();
      q.add(startingCow.getId());
      while (!q.isEmpty()) {
        int curId = q.poll();
        if (!visited.contains(curId)) {
          Cow curBoardcasting = cows[curId];
          visited.add(curId);
          
          for (Cow c : cows) {
            if (!visited.contains(c.getId())) {
              if (ifAbleToBoardCast(curBoardcasting, c)) {
                // able to boardcast
                q.add(c.getId());
              }
            }
          }
        }
      }
      maxCount = Math.max(maxCount, visited.size());
    }
    return maxCount;
  }

  public static void main(String[] args) throws IOException{
    String in = new String(Files.readAllBytes(Paths.get("moocast.in")));
    PrintWriter pr = new PrintWriter(new FileWriter("moocast.out"));
    st = new StringTokenizer(in);

    N = nextInt();
    cows = new Cow[N];
    Cow curCow;
    for (int i = 0; i < N; i++) {
      curCow = new Cow(nextInt(), nextInt(), nextInt(), i);
      cows[i] = curCow;
    }

    pr.println(getMaxBroadcastCows());
    
    pr.close();
  }
}