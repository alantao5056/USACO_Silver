import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Gifts2 {
  private static int N;
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("test/11.in"));
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();

    // init gifts
    Gift[] gifts = new Gift[N+1];

    for (int i = 1; i < N+1; i++) {
      gifts[i] = new Gift(i);
    }

    for (int i = 1; i < N+1; i++) {
      boolean stop = false;

      for (int j = 1; j < N+1; j++) {
        int curGiftId = sc.nextInt();
        if (curGiftId == i) stop = true;
        if (!stop) {
          gifts[i].next.add(gifts[curGiftId]);
        }
      }
    }

    int[] result = new int[N+1];
    for (int i = 1; i < N+1; i++) {
      for (var n : gifts[i].next) {
        boolean loop = getLoop(n, gifts[i]);

        if (loop == true) {
          // done n is optimal
          result[i] = n.id;
          break;
        }
      }
    }

    for (int i = 1; i < N+1; i++) {
      System.out.println(result[i] == 0 ? i : result[i]);
    }

    sc.close();
  }

  private static boolean getLoop(Gift gift, Gift target) {
    boolean[] visited = new boolean[N+1];
    Queue<Gift> q = new LinkedList<>();
    q.add(gift);
    visited[gift.id] = true;

    while (!q.isEmpty()) {
      Gift curGift = q.poll();

      if (curGift== target) {
        // found
        return true;
      }
      for (var n : curGift.next) {
        if (!visited[n.id]) {
          q.add(n);
          visited[n.id] = true;
        }
      }
    }
    return false;
  }

  public static class Gift {
    List<Gift> next = new ArrayList<>();
    int id;

    public Gift (int id) {
      this.id = id;
    }
    @Override
    public String toString() {
      return Integer.toString(id);
    }
  }
}
