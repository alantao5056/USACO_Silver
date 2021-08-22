import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Moocast2 {
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
    HashMap<Cow, List<Cow>> connectionHash= new HashMap<>();
    // initialize connectionHash
    for (Cow c: cows) {
      for (Cow neighbor : cows) {
        if (neighbor.getId() != c.getId()) {
          if (ifAbleToBoardCast(c, neighbor)) {
            // connected
            if (!connectionHash.containsKey(c)) {
              connectionHash.put(c, new ArrayList<>());
            }

            if (!connectionHash.get(c).contains(neighbor)) {
              connectionHash.get(c).add(neighbor);
            }
          }
        }
      }
      if (!connectionHash.containsKey(c)) {
        connectionHash.put(c, new ArrayList<>());
      }
    }

    int maxCount = 0;
    // start brute force
    for (Cow startingCow : cows) {
      Queue<Integer> q = new LinkedList<>();
      boolean[] visited = new boolean[N];
      q.add(startingCow.getId());

      while (!q.isEmpty()) {
        int curId = q.poll();
        Cow curCow = cows[curId];
        visited[curId] = true;

        List<Cow> neighbors = connectionHash.get(curCow);
        for (Cow n : neighbors) {
          if (!visited[n.getId()]) {
            q.add(n.getId());
          }
        }
      }
      int count = 0;
      for (int i = 0; i < N; i++) {
        if (visited[i]) count++;
      }
      maxCount = Math.max(maxCount, count);
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
