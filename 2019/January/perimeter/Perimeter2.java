import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Perimeter2 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("perimeter.in"));
    PrintWriter pw = new PrintWriter("perimeter.out");

    int N = sc.nextInt();

    boolean[][] grid = new boolean[N][N];
    boolean[][] visited = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      char[] line = sc.next().toCharArray();
      for (int j = 0; j < N; j++) {
        grid[i][j] = line[j] == '#' ? true : false;
      }
    }

    int maxArea = 0;
    int maxPerimeter = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!grid[i][j] || visited[i][j]) continue;
        // new group
        int curArea = 0;
        int curPerimeter = 0;

        Queue<Integer> qi = new LinkedList<>();
        Queue<Integer> qj = new LinkedList<>();
        qi.add(i);
        qj.add(j);
        visited[i][j] = true;
        while (!qi.isEmpty()) {
          int curI = qi.poll();
          int curJ = qj.poll();
          curArea++;

          if (curI < N-1 && grid[curI+1][curJ] && !visited[curI+1][curJ]) {
            qi.add(curI+1);
            qj.add(curJ);
            visited[curI+1][curJ] = true;
          } else if (curI == N-1 || !grid[curI+1][curJ]) {
            curPerimeter++;
          }
          if (curJ < N-1 && grid[curI][curJ+1] && !visited[curI][curJ+1]) {
            qi.add(curI);
            qj.add(curJ+1);
            visited[curI][curJ+1] = true;
          } else if (curJ == N-1 || !grid[curI][curJ+1]) {
            curPerimeter++;
          }
          if (curI > 0 && grid[curI-1][curJ] && !visited[curI-1][curJ]) {
            qi.add(curI-1);
            qj.add(curJ);
            visited[curI-1][curJ] = true;
          } else if (curI == 0 || !grid[curI-1][curJ]) {
            curPerimeter++;
          }
          if (curJ > 0 && grid[curI][curJ-1] && !visited[curI][curJ-1]) {
            qi.add(curI);
            qj.add(curJ-1);
            visited[curI][curJ-1] = true;
          } else if (curJ == 0 || !grid[curI][curJ-1]) {
            curPerimeter++;
          }
        }

        if (curArea > maxArea) {
          maxArea = curArea;
          maxPerimeter = curPerimeter;
        } else if (curArea == maxArea) {
          maxPerimeter = Math.min(maxPerimeter, curPerimeter);
        }
      }
    }

    pw.println(String.format("%d %d", maxArea, maxPerimeter));

    sc.close();
    pw.close();
  }
}