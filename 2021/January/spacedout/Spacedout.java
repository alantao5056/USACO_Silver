import java.util.Scanner;

public class Spacedout {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("spacedout.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    int[][] grid = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] = sc.nextInt();
      }
    }

    // by row
    int rowCount = 0;
    for (int i = 0; i < N; i++) {
      int tot1 = 0;
      int tot2 = 0;
      for (int j = 0; j < N; j+=2) {
        tot1 += grid[i][j];
      }
      for (int j = 1; j < N; j+=2) {
        tot2 += grid[i][j];
      }

      rowCount += tot1 > tot2 ? tot1 : tot2;
    }

    // by column
    int columnCount = 0;
    for (int i = 0; i < N; i++) {
      int tot1 = 0;
      int tot2 = 0;
      for (int j = 0; j < N; j+=2) {
        tot1 += grid[j][i];
      }
      for (int j = 1; j < N; j+=2) {
        tot2 += grid[j][i];
      }

      columnCount += tot1 > tot2 ? tot1 : tot2;
    }

    System.out.println(rowCount > columnCount ? rowCount : columnCount);

    sc.close();
  }
}