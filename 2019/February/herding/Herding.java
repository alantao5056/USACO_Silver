import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Herding {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("herding.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));

    int N = sc.nextInt();

    int[] cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = sc.nextInt();
    }

    Arrays.sort(cows);

    int maxRange = 0;
    for (int start = 0; start < N; start++) {
      int end = start;
      while (end < N-1 && cows[end+1] - cows[start] < N) {
        end++;
      }
      int range = end - start+1;
      if (range == N-1 && cows[end] == cows[start]+N-2) {
        maxRange = Math.max(maxRange, range-1);
      } else {maxRange = Math.max(maxRange, range);}
    }

    pw.println(N - maxRange);

    int numOfWhiteSpace = cows[N-1] - cows[0] - (N-1);
    int minGap = Math.min(cows[1] - cows[0], cows[N-1] - cows[N-2]) - 1;

    pw.println(numOfWhiteSpace - minGap);

    sc.close();
    pw.close();
  }
}