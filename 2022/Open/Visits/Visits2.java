import java.util.Scanner;

public class Visits2 {
  static int[] next;
  static int[] nextValue;
  static int[] groupNums;
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    next = new int[N+1];
    nextValue = new int[N+1];
    for (int i = 1; i <= N; i++) {
      next[i] = sc.nextInt();
      nextValue[i] = sc.nextInt();
    }

    groupNums = new int[N+1];

    long total = 0;
    int curGroupNum = 0;
    for (int i = 1; i <= N; i++) {
      if (groupNums[i] != 0) continue;
      curGroupNum++;

      int curCow = i;
      while (groupNums[curCow] == 0) {
        total += nextValue[curCow];
        groupNums[curCow] = curGroupNum;
        curCow = next[curCow];
      }

      if (groupNums[curCow] == curGroupNum) {
        total -= findMinEdge(curCow);
      }
    }

    System.out.println(total);

    sc.close();
  }

  private static int findMinEdge(int start) {
    int minEdge = nextValue[start];
    int curCow = next[start];
    while (curCow != start) {
      minEdge = Math.min(minEdge, nextValue[curCow]);
      curCow = next[curCow];
    }

    return minEdge;
  }
}