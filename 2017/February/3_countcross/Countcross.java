import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Countcross {
  static int N;
  static int K;
  static int R;
  static Cell[][] grid;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("countcross.in"));
    PrintWriter pw = new PrintWriter("countcross.out");

    N = sc.nextInt();
    K = sc.nextInt();
    R = sc.nextInt();
    grid = new Cell[N+2][N+2];

    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        grid[i][j] = new Cell(i, j);
      }
    }

    for (int i = 0; i < R; i++) {
      int i1 = sc.nextInt();
      int j1 = sc.nextInt();
      int i2 = sc.nextInt();
      int j2 = sc.nextInt();

      if (i1 == i2) {
        // left-right
        if (j1 < j2) {
          grid[i1][j1].right = true;
          grid[i2][j2].left = true;
        } else {
          grid[i1][j1].left = true;
          grid[i2][j2].right = true;
        }
      } else {
        // up down
        if (i1 > i2) {
          grid[i1][j1].up = true;
          grid[i2][j2].down = true;
        } else {
          grid[i1][j1].down = true;
          grid[i2][j2].up = true;
        }
      }
    }

    for (int i = 0; i < K; i++) {
      grid[sc.nextInt()][sc.nextInt()].cow = true;
    }

    // flood fill
    List<Integer> groups = new ArrayList<>();

    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        if (grid[i][j].visited) continue;
        Queue<Cell> q = new LinkedList<>();
        q.add(grid[i][j]);
        grid[i][j].visited = true;

        int count = 0;

        while (!q.isEmpty()) {
          Cell curCell = q.poll();
          if (curCell.cow) {
            count++;
          }
          Cell down = curCell.getDown();
          Cell right = curCell.getRight();
          Cell up = curCell.getUp();
          Cell left = curCell.getLeft();

          if (down != null && !down.visited) {
            q.add(down);
            down.visited = true;
          }
          if (right != null && !right.visited) {
            q.add(right);
            right.visited = true;
          }
          if (up != null && !up.visited) {
            q.add(up);
            up.visited = true;
          }
          if (left != null && !left.visited) {
            q.add(left);
            left.visited = true;
          }
        }
        groups.add(count);
      }
    }

    // calc results
    int len = groups.size();
    long total = 0;
    for (int i = 0; i < len; i++) {
      for (int j = i+1; j < len; j++) {
        total += groups.get(i) * groups.get(j);
      }
    }

    pw.println(total);

    sc.close();
    pw.close();
  }

  public static class Cell {
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    boolean cow = false;
    boolean visited = false;
    int x;
    int y;
    public Cell (int x, int y) {
      this.x = x;
      this.y = y;
    }
    public Cell getLeft() {
      return left ? null : grid[x][y-1];
    }
    public Cell getRight() {
      return right ? null : grid[x][y+1];
    }
    public Cell getUp() {
      return up ? null : grid[x-1][y];
    }
    public Cell getDown() {
      return down ? null : grid[x+1][y];
    }
  }
}