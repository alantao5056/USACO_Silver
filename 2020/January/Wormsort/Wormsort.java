import java.util.*;
import java.io.PrintWriter;

public class Wormsort {
  static int N;
  static int M;
  static List<List<Wormhole>> wormholeMap;
  static List<WormholePair> allWormholes;
  static Cow[] cows;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("wormsort.in"));
    PrintWriter pw = new PrintWriter("wormsort.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    
    cows = new Cow[N];
    wormholeMap = new ArrayList<>();
    allWormholes = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      wormholeMap.add(new ArrayList<>());
    }

    boolean sorted = true;
    for (int i = 0; i < N; i++) {
      int id = sc.nextInt()-1;
      cows[i] = new Cow(id, i);
      if (id != i) sorted = false;
    }

    if (sorted) {
      pw.println(-1);
      pw.close();
      return;
    }

    for (int i = 0; i < M; i++) {
      int a = sc.nextInt()-1;
      int b = sc.nextInt()-1;
      int weight = sc.nextInt();
      Wormhole wormhole1 = new Wormhole(b, weight);
      wormholeMap.get(a).add(wormhole1);
      Wormhole wormhole2 = new Wormhole(a, weight);
      wormholeMap.get(b).add(wormhole2);
      allWormholes.add(new WormholePair(wormhole1, wormhole2));
    }


    Collections.sort(allWormholes, (a, b) -> {return b.from.weight - a.from.weight;});
    for (int i = 0; i < allWormholes.size(); i++) {
      allWormholes.get(i).id = i;
      allWormholes.get(i).from.id = i;
      allWormholes.get(i).to.id = i;
    }

    // binary search
    int high = M-1;
    int low = 0;
    int mid = 0;

    while (low <= high) {
      mid = low  + ((high - low) / 2);
      if (checkIfConnected(mid)) {
        high = mid-1;
      } else {
        low = mid+1;
      }
    }

    pw.println(allWormholes.get(low).from.weight);

    pw.close();
    sc.close();
  }

  private static boolean checkIfConnected(int mid) {
    boolean[] visited = new boolean[N];
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;

      // bfs
      ArrayDeque<Cow> q = new ArrayDeque<>();
      q.add(cows[i]);
      visited[cows[i].id] = true;

      while (!q.isEmpty()) {
        Cow curCow = q.poll();
        int curPos = curCow.pos;

        for (var w : wormholeMap.get(curPos)) {
          if (w.isValid(mid) && !visited[cows[w.to].id]) {
            q.add(cows[w.to]);
            visited[cows[w.to].id] = true;
          }
        }
      }
      break;
    }

    for (int i = 0; i < N; i++) {
      if (!visited[i] && cows[i].id != cows[i].pos) {
        return false;
      }
    }
    return true;
  }

  public static class WormholePair {
    Wormhole from;
    Wormhole to;
    int id;

    public WormholePair (Wormhole from, Wormhole to) {
      this.from = from;
      this.to = to;
    }
  }

  public static class Cow {
    int id;
    int pos;
    public Cow(int id, int pos) {
      this.id = id;
      this.pos = pos;
    }

    @Override
    public String toString() {
      return Integer.toString(id);
    }
  }

  public static class Wormhole {
    int id;
    int to;
    int weight;

    public Wormhole(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return Integer.toString(to);
    }

    public boolean isValid(int mid) {
      return id <= mid;
    }
  }
}
