import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Where {
  static int N;
  static int[][] grid;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("where.in"));
    PrintWriter pw = new PrintWriter("where.out");

    N = sc.nextInt();
    grid = new int[N+2][N+2];
    for (int i = 1; i < N+1; i++) {
      char[] line = sc.next().toCharArray();
      for (int j = 1; j < N+1; j++) {
        grid[i][j] = ((int) line[j-1])-64;
      }
    }

    // brute force
    List<Integer> pclI1 = new ArrayList<>();
    List<Integer> pclJ1 = new ArrayList<>();
    List<Integer> pclI2 = new ArrayList<>();
    List<Integer> pclJ2 = new ArrayList<>();
    
    for (int i1 = 1; i1 < N+1; i1++) {
      for (int j1 = 1; j1 < N+1; j1++) {
        for (int i2 = 1; i2 < N+1; i2++) {
          for (int j2 = 1; j2 < N+1; j2++) {
            if (checkPLC(i1, j1, i2, j2)) {
              // System.out.println(String.format("%d %d %d %d", i1, j1, i2, j2));
              pclI1.add(i1);
              pclJ1.add(j1);
              pclI2.add(i2);
              pclJ2.add(j2);
            }
          }
        }
      }
    }
    
    // eliminate pcls
    int count = pclI1.size();
    for (int i = 0; i < pclI1.size(); i++) {
      int i1 = pclI1.get(i);
      int j1 = pclJ1.get(i);
      int i2 = pclI2.get(i);
      int j2 = pclJ2.get(i);
      for (int j = 0; j < pclI1.size(); j++) {
        if (i != j) {
          if (inOtherPLC(i1, j1, i2, j2, pclI1.get(j), pclJ1.get(j), pclI2.get(j), pclJ2.get(j))) {
            count--;
            break;
          }
        }
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static boolean checkPLC(int i1, int j1, int i2, int j2) {
    // use flood fill to check
    int[] seenCount = new int[27];
    boolean[][] visited = new boolean[N+2][N+2];

    for (int i = i1; i < i2+1; i++) {
      for (int j = j1; j < j2+1; j++) {
        if (visited[i][j]) continue;
        // flood fill here
        int curLetter = grid[i][j];
        seenCount[curLetter]++;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q1.add(i);
        q2.add(j);
        visited[i][j] = true;

        while (!q1.isEmpty()) {
          int curI = q1.poll();
          int curJ = q2.poll();

          if (curI+1 <= i2 && grid[curI+1][curJ] == curLetter && !visited[curI+1][curJ]) {
            q1.add(curI+1);
            q2.add(curJ);
            visited[curI+1][curJ] = true;
          }
          if (curJ+1 <= j2 && grid[curI][curJ+1] == curLetter && !visited[curI][curJ+1]) {
            q1.add(curI);
            q2.add(curJ+1);
            visited[curI][curJ+1] = true;
          }
          if (curI-1 >= i1 && grid[curI-1][curJ] == curLetter && !visited[curI-1][curJ]) {
            q1.add(curI-1);
            q2.add(curJ);
            visited[curI-1][curJ] = true;
          }
          if (curJ-1 >= j1 && grid[curI][curJ-1] == curLetter && !visited[curI][curJ-1]) {
            q1.add(curI);
            q2.add(curJ-1);
            visited[curI][curJ-1] = true;
          }
        }
      }
    }

    int totalLetters = 0;
    boolean one = false;
    boolean many = false;
    for (int i = 1; i < 27; i++) {
      if (seenCount[i] != 0) totalLetters++;
      if (seenCount[i] == 1) one = true;
      else if (seenCount[i] > 1) many = true;
    }

    return totalLetters == 2 && one && many;
  }

  private static boolean inOtherPLC(int i1, int j1, int i2, int j2, int i3, int j3, int i4, int j4) {
    if (i1 >= i3 && i1 <= i4 && j1 >= j3 && j1 <= j4 && i2 >= i3 && i2 <= i4 && j2 >= j3 && j2 <= j4) {
      return true;
    }
    return false;
  }
}
