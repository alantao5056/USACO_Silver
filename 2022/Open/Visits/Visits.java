import java.util.Scanner;

public class Visits {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    Cow[] cows = new Cow[N+1];
    for (int i = 1; i < N+1; i++) {
      cows[i] = new Cow(i);
    }

    for (int i = 1; i < N+1; i++) {
      cows[i].next = cows[sc.nextInt()];
      cows[i].nextValue = sc.nextInt();
    }

    // cycle cows and find groups and loops
    long total = 0;
    int curGroupNum = 0;
    for (int i = 1; i < N+1; i++) {
      if (cows[i].groupNum != 0) continue;
      curGroupNum++;

      // start
      Cow curCow = cows[i];
      // long totalSum = 0;
      while (curCow.groupNum == 0) {
        total += curCow.nextValue;
        curCow.groupNum = curGroupNum;
        curCow = curCow.next;
      }

      if (curCow.groupNum != curGroupNum) {
        // some other group already visited
        continue;
      }

      long minEdge = Long.MAX_VALUE;
      Cow lastCow = curCow;
      do {
        minEdge = Math.min(minEdge, curCow.nextValue);
        curCow = curCow.next;
      } while (curCow != lastCow);

      total -= minEdge;
    }

    System.out.println(total);

    sc.close();
  }

  public static class Cow {
    Cow next;
    int nextValue;
    int id;
    int groupNum = 0;
    public Cow(int id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return Integer.toString(id);
    }
  }
}