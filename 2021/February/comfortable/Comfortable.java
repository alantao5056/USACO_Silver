import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Comfortable {
  public static boolean[][] grid;
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("comfortable.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    grid = new boolean[2000][2000];
    int total = 0;

    for (int i = 0; i < N; i++) {
      int x = sc.nextInt() + 500;
      int y = sc.nextInt() + 500;

      if (!grid[x][y]) {grid[x][y] = true;}
      else total--;

      // add the cows
      Queue<Integer> xq = new LinkedList<>();
      Queue<Integer> yq = new LinkedList<>();

      xq.add(x);
      yq.add(y);


      while (!xq.isEmpty()) {
        int curX = xq.poll();
        int curY = yq.poll();

        total+=checkAndAdd(xq, yq, curX, curY);
        total+=checkAndAdd(xq, yq, curX, curY+1);
        total+=checkAndAdd(xq, yq, curX, curY-1);
        total+=checkAndAdd(xq, yq, curX+1, curY);
        total+=checkAndAdd(xq, yq, curX-1, curY);
      }
      System.out.println(total);
    }
    sc.close();
  }

  private static int checkAndAdd(Queue<Integer> xq, Queue<Integer> yq, int curX, int curY) {
    int[] next = isComfortable(curX, curY);
    if (next != null) {
      int nextX = next[0];
      int nextY = next[1];
      xq.add(nextX);
      yq.add(nextY);
      grid[nextX][nextY] = true;
      return 1;
    }
    return 0;
  }

  private static int[] isComfortable(int x, int y) {
    if (!grid[x][y]) return null;
    int nextX = -1;
    int nextY = -1;
    int around = 0;

    if (!grid[x][y+1]) {nextX = x; nextY = y+1;}
    else around++;
    if (!grid[x][y-1]) {nextX = x; nextY = y-1;}
    else around++;
    if (!grid[x+1][y]) {nextX = x+1; nextY = y;}
    else around++;
    if (!grid[x-1][y]) {nextX = x-1; nextY = y;}
    else around++;

    if (around == 3) {
      int[] result = new int[2];
      result[0] = nextX;
      result[1] = nextY;
      return result;
    }
    return null;
  }
}