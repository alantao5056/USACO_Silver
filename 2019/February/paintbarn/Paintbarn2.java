import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Paintbarn2 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("paintbarn.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));

    int N = sc.nextInt();
    int K = sc.nextInt();

    int[][] grid = new int[1002][1002];

    for (int i = 0; i < N; i++) {
      int x1, y1, x2, y2;
      x1 = sc.nextInt();
      y1 = sc.nextInt();
      x2 = sc.nextInt();
      y2 = sc.nextInt();

      for (int j = y1+1; j <= y2; j++) {
        grid[j][x1]++;
        grid[j][x2]--;
      }
    }

    int[][] prefix = new int[1002][1002];
    int count = 0;
    for (int i = 0; i < 1002; i++) {
      int total = 0;
      for (int j = 0; j < 1002; j++) {
        total += grid[i][j];
        prefix[i][j] = total;
        if (total == K) count++;
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }
}
