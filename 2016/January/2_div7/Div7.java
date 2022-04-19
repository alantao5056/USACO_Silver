import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Div7 {
  static int N;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("div7.in"));
    PrintWriter pw = new PrintWriter("div7.out");

    int N = sc.nextInt();

    int[] minPos = new int[7];
    Arrays.fill(minPos, Integer.MAX_VALUE);
    int[] maxPos = new int[7];
    int curPrefix = 0;
    for (int i = 0; i < N; i++) {
      int cur = sc.nextInt()%7;
      curPrefix += cur;
      curPrefix %= 7;
      minPos[curPrefix] = Math.min(minPos[curPrefix], i);
      maxPos[curPrefix] = Math.max(maxPos[curPrefix], i);
    }

    int max = 0;
    for (int i = 0; i < 7; i++) {
      max = Math.max(max, maxPos[i] - minPos[i]);
    }

    pw.println(max);

    sc.close();
    pw.close();
  }
}