import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Cownomics {
  static int N;
  static int M;
  static int[][] spotty;
  static int[][] plain;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("cownomics.in"));
    PrintWriter pw = new PrintWriter("cownomics.out");
    N = sc.nextInt();
    M = sc.nextInt();
    
    // solve
    spotty = new int[M][N];
    for (int i = 0; i < N; i++) {
      char[] line = sc.next().toCharArray();

      for (int j = 0; j < M; j++) {
        spotty[j][i] = line[j]=='A' ? 1 : line[j] == 'C' ? 2 : line[j]=='G' ? 3 : 4;
      }
    }

    plain = new int[M][N];
    for (int i = 0; i < N; i++) {
      char[] line = sc.next().toCharArray();

      for (int j = 0; j < M; j++) {
        plain[j][i] = line[j]=='A' ? 1 : line[j] == 'C' ? 2 : line[j]=='G' ? 3 : 4;
      }
    }

    // brute force
    int count = 0;
    for (int i = 0; i < M-2; i++) {
      for (int j = i+1; j < M-1; j++) {
        for (int k = j+1; k < M; k++) {
          count += checkIfUnique(i, j, k) ? 1 : 0;
        }
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static boolean checkIfUnique(int i, int j, int k) {
    Set<Integer> inSpotty = new HashSet<>();
    for (int l = 0; l < N; l++) {
      inSpotty.add(spotty[i][l] + spotty[j][l] * 10 + spotty[k][l] * 100);
    }

    for (int l = 0; l < N; l++) {
      if (inSpotty.contains(plain[i][l] + plain[j][l] * 10 + plain[k][l] * 100)) {
        return false;
      }
    }
    return true;
  }
}