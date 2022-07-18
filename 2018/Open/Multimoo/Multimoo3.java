import java.io.PrintWriter;
import java.io.File;
import java.util.*;

/**
 * dfs
 */

public class Multimoo3 {
  static int N;
  static int[][] grid;
  static boolean[][] visited;
  static int count;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("test/9.in"));
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
        int curGroupNum = grid[i][j];

        count = 0;
        dfs1(i, j, curGroupNum);

        maxSize = Math.max(maxSize, count);
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

    count = 0;
    dfs2(i1, j1, teamNum1, teamNum2);

    return count;
  }

  private static void dfs1(int curI, int curJ, int curGroupNum) {
    if (visited[curI][curJ]) return;
    visited[curI][curJ] = true;

    count++;

    if (grid[curI+1][curJ] == curGroupNum && !visited[curI+1][curJ]) {
      dfs1(curI+1, curJ, curGroupNum);
    }
    if (grid[curI][curJ+1] == curGroupNum && !visited[curI][curJ+1]) {
      dfs1(curI, curJ+1, curGroupNum);
    }
    if (grid[curI-1][curJ] == curGroupNum && !visited[curI-1][curJ]) {
      dfs1(curI-1, curJ, curGroupNum);
    }
    if (grid[curI][curJ-1] == curGroupNum && !visited[curI][curJ-1]) {
      dfs1(curI, curJ-1, curGroupNum);
    }

  }

  private static void dfs2(int curI, int curJ, int teamNum1, int teamNum2) {
    if (visited[curI][curJ]) return;
    visited[curI][curJ] = true;

    count++;
    if ((grid[curI+1][curJ] == teamNum1 || grid[curI+1][curJ] == teamNum2) && !visited[curI+1][curJ]) {
      dfs2(curI+1, curJ, teamNum1, teamNum2);
    }
    if ((grid[curI][curJ+1] == teamNum1 || grid[curI][curJ+1] == teamNum2) && !visited[curI][curJ+1]) {
      dfs2(curI, curJ+1, teamNum1, teamNum2);
    }
    if ((grid[curI-1][curJ] == teamNum1 || grid[curI-1][curJ] == teamNum2) && !visited[curI-1][curJ]) {
      dfs2(curI-1, curJ, teamNum1, teamNum2);
    }
    if ((grid[curI][curJ-1] == teamNum1 || grid[curI][curJ-1] == teamNum2)&& !visited[curI][curJ-1]) {
      dfs2(curI, curJ-1, teamNum1, teamNum2);
    }
  }

  private static void resetVisited() {
    for (int i = 1; i < N+1; i++) {
      Arrays.fill(visited[i], false);
    }
  }
}