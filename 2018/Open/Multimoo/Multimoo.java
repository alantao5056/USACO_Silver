import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Multimoo {
  static int N;
  static int[][] grid;

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

    boolean[][] visited = new boolean[N+2][N+2];
    int[][] idGrid = new int[N+2][N+2];
    List<Group> groups = new ArrayList<>();
    groups.add(new Group(0, 0));
    // iterate all groups using flood fill
    int curId = 1;
    int maxSize = 0;

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (visited[i][j]) continue;

        // new group
        Group curGroup = new Group(curId, grid[i][j]);

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

          idGrid[curI][curJ] = curId;

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

        curGroup.size = size;
        maxSize = Math.max(maxSize, size);
        groups.add(curGroup);
        curId++;
      }
    }

    pw.println(maxSize);

    // second step
    // create adj arr
    visited = new boolean[N+2][N+2];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (visited[i][j]) continue;

        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        q1.add(i);
        q2.add(j);
        boolean[] visitedId = new boolean[groups.size()];
        visited[i][j] = true;
        curId = idGrid[i][j];
        Group curGroup = groups.get(curId);

        while (!q1.isEmpty()) {
          int curI = q1.poll();
          int curJ = q2.poll();

          if (idGrid[curI+1][curJ] != curId && idGrid[curI+1][curJ] != 0 && !visitedId[idGrid[curI+1][curJ]]) {
            // neighbor
            curGroup.nbs.add(groups.get(idGrid[curI+1][curJ]));
            visitedId[idGrid[curI+1][curJ]] = true;
          } else if (idGrid[curI+1][curJ] == curId && !visited[curI+1][curJ]) {
            q1.add(curI+1);
            q2.add(curJ);
            visited[curI+1][curJ] = true;
          }
          if (idGrid[curI][curJ+1] != curId && idGrid[curI][curJ+1] != 0 && !visitedId[idGrid[curI][curJ+1]]) {
            // neighbor
            curGroup.nbs.add(groups.get(idGrid[curI][curJ+1]));
            visitedId[idGrid[curI][curJ+1]] = true;
          } else if (idGrid[curI][curJ+1] == curId && !visited[curI][curJ+1]) {
            q1.add(curI);
            q2.add(curJ+1);
            visited[curI][curJ+1] = true;
          }
          if (idGrid[curI-1][curJ] != curId && idGrid[curI-1][curJ] != 0 && !visitedId[idGrid[curI-1][curJ]]) {
            // neighbor
            curGroup.nbs.add(groups.get(idGrid[curI-1][curJ]));
            visitedId[idGrid[curI-1][curJ]] = true;
          } else if (idGrid[curI-1][curJ] == curId && !visited[curI-1][curJ]) {
            q1.add(curI-1);
            q2.add(curJ);
            visited[curI-1][curJ] = true;
          }
          if (idGrid[curI][curJ-1] != curId && idGrid[curI][curJ-1] != 0 && !visitedId[idGrid[curI][curJ-1]]) {
            // neighbor
            curGroup.nbs.add(groups.get(idGrid[curI][curJ-1]));
            visitedId[idGrid[curI][curJ-1]] = true;
          } else if (idGrid[curI][curJ-1] == curId && !visited[curI][curJ-1]) {
            q1.add(curI);
            q2.add(curJ-1);
            visited[curI][curJ-1] = true;
          }
        }
      }
    }

    if (groups.size() == 2) {
      // only one team
      pw.println(N*N);
      pw.close();
      return;
    }

    // brute force
    int maxTotalSize = 0;
    for (int i = 1; i < groups.size(); i++) {

      for (Group n : groups.get(i).nbs) {
        // pretend groups i and n are a group
        boolean[] curVisited = new boolean[groups.size()];

        ArrayDeque<Group> q = new ArrayDeque<>();
        q.add(n);
        curVisited[n.id] = true;
        curVisited[groups.get(i).id] = true;

        int teamNum1 = groups.get(i).teamNum;
        int teamNum2 = n.teamNum;
        int totalSize = groups.get(i).size + n.size;

        while (!q.isEmpty()) {
          Group curGroup = q.poll();

          for (Group nb : curGroup.nbs) {
            if (!curVisited[nb.id] && (nb.teamNum == teamNum1 || nb.teamNum == teamNum2)) {
              q.add(nb);
              totalSize += nb.size;
              curVisited[nb.id] = true;
            }
          }
        }

        maxTotalSize = Math.max(maxTotalSize, totalSize);
      }
    }

    pw.println(maxTotalSize);

    sc.close();
    pw.close();
  }

  private static class Group {
    int id;
    int teamNum;
    int size;
    List<Group> nbs = new ArrayList<>();
    public Group(int id, int teamNum) {
      this.id = id;
      this.teamNum = teamNum;
    }
  }
}