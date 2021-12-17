import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Paintbarn3 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("paintbarn.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));

    int N = sc.nextInt();
    int K = sc.nextInt();

    int[][] grid = new int[1002][1002];

    for (int i = 0; i < N; i++) {
      int x1, y1, x2, y2;
      x1 = sc.nextInt()+1;
      y1 = sc.nextInt()+1;
      x2 = sc.nextInt()+1;
      y2 = sc.nextInt()+1;

      grid[x1][y1]++;
      grid[x2][y2]++;
      grid[x1][y2]--;
      grid[x2][y1]--;
    }

    int count = 0;
    for (int i = 1; i < 1002; i++) {
      for (int j = 1; j < 1002; j++) {
        grid[i][j] = grid[i][j] + grid[i-1][j] + grid[i][j-1] - grid[i-1][j-1];
        if (grid[i][j] == K) count++;
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }
}
