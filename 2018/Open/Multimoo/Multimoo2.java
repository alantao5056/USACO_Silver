import java.io.PrintWriter;
import java.io.File;
import java.util.*;

/**
 * bfs
 */

public class Multimoo2 {
  static int N;
  static int[][] grid;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("multimoo.in"));
    PrintWriter pw = new PrintWriter("multimoo.out");
    N = sc.nextInt();
    
    grid = new int[N+2][N+2];
    
    // solve
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        grid[i][j] = sc.nextInt()+1;
      }
    }

    visited = new boolean[N+2][N+2];
    // iterate all groups using flood fill
    int maxSize = 0;

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (visited[i][j]) continue;

        // new group
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        q1.add(i);
        q2.add(j);

        visited[i][j] = true;

        int size = 0;
        int curGroupNum = grid[i][j];

        while (!q1.isEmpty()) {
          int curI = q1.poll();
          int curJ = q2.poll();

          size++;

          if (grid[curI+1][curJ] == curGroupNum && !visited[curI+1][curJ]) {
            q1.add(curI+1);
            q2.add(curJ);
            visited[curI+1][curJ] = true;
          }
          if (grid[curI][curJ+1] == curGroupNum && !visited[curI][curJ+1]) {
            q1.add(curI);
            q2.add(curJ+1);
            visited[curI][curJ+1] = true;
          }
          if (grid[curI-1][curJ] == curGroupNum && !visited[curI-1][curJ]) {
            q1.add(curI-1);
            q2.add(curJ);
            visited[curI-1][curJ] = true;
          }
          if (grid[curI][curJ-1] == curGroupNum && !visited[curI][curJ-1]) {
            q1.add(curI);
            q2.add(curJ-1);
            visited[curI][curJ-1] = true;
          }
        }

        maxSize = Math.max(maxSize, size);
      }
    }

    pw.println(maxSize);
    if (maxSize == N*N) {
      pw.println(maxSize);
      pw.close();
      return;
    }

    // 2nd step
    int maxTotalSize = 0;
    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        // pair up with right and down
        int rightTeamNum = grid[i][j+1];
        int downTeamNum = grid[i+1][j];

        if (rightTeamNum != grid[i][j] && rightTeamNum != 0) {
          maxTotalSize = Math.max(maxTotalSize, getMaxSize(i, j, i, j+1));
        }
        if (rightTeamNum != downTeamNum && downTeamNum != grid[i][j] && downTeamNum != 0) {
          maxTotalSize = Math.max(maxTotalSize, getMaxSize(i, j, i+1, j));
        }

        if (maxTotalSize == N*N) {
          pw.println(maxTotalSize);
          pw.close();
          return;
        }
      }
    }

    pw.println(maxTotalSize);

    sc.close();
    pw.close();
  }

  private static int getMaxSize(int i1, int j1, int i2, int j2) {
    resetVisited();

    int teamNum1 = grid[i1][j1];
    int teamNum2 = grid[i2][j2];

    ArrayDeque<Integer> q1 = new ArrayDeque<>();
    ArrayDeque<Integer> q2 = new ArrayDeque<>();

    q1.add(i1);
    q1.add(i2);
    q2.add(j1);
    q2.add(j2);
    visited[i1][j1] = true;
    visited[i2][j2] = true;
    int size = 0;

    while (!q1.isEmpty()) {
      int curI = q1.poll();
      int curJ = q2.poll();
      size++;

      if ((grid[curI+1][curJ] == teamNum1 || grid[curI+1][curJ] == teamNum2) && !visited[curI+1][curJ]) {
        q1.add(curI+1);
        q2.add(curJ);
        visited[curI+1][curJ] = true;
      }
      if ((grid[curI][curJ+1] == teamNum1 || grid[curI][curJ+1] == teamNum2) && !visited[curI][curJ+1]) {
        q1.add(curI);
        q2.add(curJ+1);
        visited[curI][curJ+1] = true;
      }
      if ((grid[curI-1][curJ] == teamNum1 || grid[curI-1][curJ] == teamNum2) && !visited[curI-1][curJ]) {
        q1.add(curI-1);
        q2.add(curJ);
        visited[curI-1][curJ] = true;
      }
      if ((grid[curI][curJ-1] == teamNum1 || grid[curI][curJ-1] == teamNum2)&& !visited[curI][curJ-1]) {
        q1.add(curI);
        q2.add(curJ-1);
        visited[curI][curJ-1] = true;
      }
    }

    return size;
  }

  private static void resetVisited() {
    for (int i = 1; i < N+1; i++) {
      Arrays.fill(visited[i], false);
    }
  }
}