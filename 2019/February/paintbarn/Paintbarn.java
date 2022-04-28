import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Paintbarn {
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
        for (int k = x1+1; k <= x2; k++) {
          grid[j][k]++;
        }
      }
    }

    int count = 0;
    for (int i = 1; i <= 1001; i++) {
      for (int j = 1; j <= 1001; j++) {
        if (grid[i][j] == K) count++;
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }
}