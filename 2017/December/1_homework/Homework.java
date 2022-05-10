import java.io.PrintWriter;
import java.util.Scanner;

public class Homework {
  static int N;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("homework.in"));
    PrintWriter pw = new PrintWriter("homework.out");

    N = sc.nextInt();

    int[] prefixLow = new int[N];
    int[] prefixSum = new int[N];

    int[] homework = new int[N];
    for (int i = 0; i < N; i++) {
      homework[i] = sc.nextInt();
    }

    int sum = 0;
    int low = Integer.MAX_VALUE;
    for (int i = N-1; i >= 0; i--) {
      low = Math.min(low, homework[i]);
      sum+=homework[i];

      prefixLow[i] = low;
      prefixSum[i] = sum;
    }

    double[] kResults = new double[N-1];
    double maxResult = 0;
    for (int k = 1; k <= N-2; k++) {
      kResults[k] =(double) (prefixSum[k] - prefixLow[k]) / (N-k-1);
      maxResult = Math.max(maxResult, kResults[k]);
    }

    for (int k = 1; k <= N-2; k++) {
      if (Double.compare(kResults[k], maxResult) == 0) {
        pw.println(k);
      }
    }

    sc.close();
    pw.close();
  }
}