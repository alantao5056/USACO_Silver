import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Green {
  static int N;
  static int[][] prefix;       

  public static void main(String[] args) throws Exception {
    // read input
    // Scanner sc = new Scanner(new File("green.in"));
    Scanner sc = new Scanner(System.in);
    // PrintWriter pw = new PrintWriter("green.out");
    N = sc.nextInt();
    
    // solve
    int[][] grid = new int[N+1][N+1];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        int curGreeness = sc.nextInt();
        grid[i][j] = curGreeness > 100 ? 0 : curGreeness < 100 ? -250000 : 1;
      }
    }

    // make prefix
    prefix = new int[N+1][N+1];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + grid[i][j];
      }
    }

    int count = 0;
    for (int i1 = 1; i1 <= N; i1++) {
      for (int j1 = 1; j1 <= N; j1++) {
        for (int i2 = i1; i2 <= N; i2++) {
          for (int j2 = j1; j2 <= N; j2++) {
            count += getPrefix(i1, j1, i2, j2) > 0 ? 1 : 0;
          }
        }
      }
    }

    System.out.println(count);

    // sc.close();
    // pw.close();
  }

  private static int getPrefix(int i1, int j1, int i2, int j2) {
    return prefix[i2][j2] - prefix[i1-1][j2] - prefix[i2][j1-1] + prefix[i1-1][j1-1];
  }
}