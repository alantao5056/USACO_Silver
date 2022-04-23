import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gift3 {
  private static int N;
  private static boolean[][] reachable;
  private static List<List<Integer>> gifts;
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    reachable = new boolean[N+1][N+1];
    gifts = new ArrayList<>();
    gifts.add(new ArrayList<>());

    for (int i = 1; i <= N; i++) {
      gifts.add(new ArrayList<>());
      boolean add = true;
      for (int j = 1; j <= N; j++) {
        int cur = sc.nextInt();
        if (add) {
          gifts.get(i).add(cur);
        }
        if (cur == i) {
          add = false;
        }
      }
    }

    calc_reachable_dfs();

    for (int i = 1; i <= N; i++) {
      for (int g : gifts.get(i)) {
        if (reachable[g][i]) {
          System.out.println(g);
          break;
        }
      }
    }

    sc.close();
  }

  private static void dfs(int src, int cur) {
    if (reachable[src][cur]) return;
    reachable[src][cur] = true;
    for (int g : gifts.get(cur)) {
      dfs(src, g);
    }
  }

  private static void calc_reachable_dfs() {
    for (int i = 1; i <= N; i++) {
      dfs(i, i);
    }
  }
}