import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Mooyomooyo {
  static int N;
  static int K;
  static PrintWriter pw;
  static int[] di = {0, -1, 0, 1};
  static int[] dj = {1, 0, -1, 0};
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("mooyomooyo.in"));
    pw = new PrintWriter("mooyomooyo.out");

    N = sc.nextInt();
    K = sc.nextInt();

    int[][] grid = new int[N][10];
    for (int i = 0; i < N; i++) {
      char[] line = sc.next().toCharArray();
      for (int j = 0; j < 10; j++) {
        grid[i][j] = line[j] - '0';
      }
    }

    while (removeDups(grid)) {
      grid = gravity(grid);
    }
    printGrid(grid);


    sc.close();
    pw.close();
  }

  private static boolean removeDups(int[][] grid) {
    // flood fill
    boolean[][] visited = new boolean[N][10];
    boolean removed = false;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 10; j++) {
        if (grid[i][j] == 0 || visited[i][j]) continue;
        // new
        boolean[][] visited2 = new boolean[N][10];
        int curId = grid[i][j];
        Queue<Integer> qi = new LinkedList<>();
        Queue<Integer> qj = new LinkedList<>();
        visited2[i][j] = true;
        qi.add(i);
        qj.add(j);
        int count = 0;

        while (!qi.isEmpty() && count < K) {
          int curI = qi.poll();
          int curJ = qj.poll();
          count++;

          if (curI < N-1 && grid[curI+1][curJ] == curId && !visited2[curI+1][curJ]) {
            qi.add(curI+1);
            qj.add(curJ);
            visited2[curI+1][curJ] = true;
            visited[curI+1][curJ] = true;
          }
          if (curJ < 9 && grid[curI][curJ+1] == curId && !visited2[curI][curJ+1]) {
            qi.add(curI);
            qj.add(curJ+1);
            visited2[curI][curJ+1] = true;
            visited[curI][curJ+1] = true;
          }
          if (curI > 0 && grid[curI-1][curJ] == curId && !visited2[curI-1][curJ]) {
            qi.add(curI-1);
            qj.add(curJ);
            visited2[curI-1][curJ] = true;
            visited[curI-1][curJ] = true;
          }
          if (curJ > 0 && grid[curI][curJ-1] == curId && !visited2[curI][curJ-1]) {
            qi.add(curI);
            qj.add(curJ-1);
            visited2[curI][curJ-1] = true;
            visited[curI][curJ-1] = true;
          }
        }

        if (count == K) {
          removed = true;
          qi = new LinkedList<>();
          qj = new LinkedList<>();
          qi.add(i);
          qj.add(j);
          count = 0;
          grid[i][j] = 0;

          while (!qi.isEmpty()) {
            int curI = qi.poll();
            int curJ = qj.poll();
            count++;

            if (curI < N-1 && grid[curI+1][curJ] == curId) {
              qi.add(curI+1);
              qj.add(curJ);
              grid[curI+1][curJ] = 0;
            }
            if (curJ < 9 && grid[curI][curJ+1] == curId) {
              qi.add(curI);
              qj.add(curJ+1);
              grid[curI][curJ+1] = 0;
            }
            if (curI > 0 && grid[curI-1][curJ] == curId) {
              qi.add(curI-1);
              qj.add(curJ);
              grid[curI-1][curJ] = 0;
            }
            if (curJ > 0 && grid[curI][curJ-1] == curId) {
              qi.add(curI);
              qj.add(curJ-1);
              grid[curI][curJ-1] = 0;
            }
          }
        }
      }
    }
    return removed;
  }

  private static int[][] gravity(int[][] grid) {
    int[][] newArr = new int[N][10];
    for (int j = 0; j < 10; j++) {
      int curI = N-1;
      for (int i = N-1; i >= 0; i--) {
        if (grid[i][j] == 0) continue;
        newArr[curI][j] = grid[i][j];
        curI--;
      }
    }
    return Arrays.copyOf(newArr, N);
  }

  private static void printGrid(int[][] grid) {
    for (int i = 0; i < N; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < 10; j++) {
        sb.append(Integer.toString(grid[i][j]));
      }
      pw.println(sb.toString());
    }
  }
}