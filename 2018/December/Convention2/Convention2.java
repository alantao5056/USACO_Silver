import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Convention2 {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("convention2.in"));
    PrintWriter pw = new PrintWriter("convention2.out");
    N = sc.nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(i, sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(cows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.arriveTime - b.arriveTime;
      }
    });

    PriorityQueue<Cow> pq = new PriorityQueue<Cow>(N, new Comparator<Cow>() {
      public int compare(Cow c1, Cow c2) {
        return c1.priority - c2.priority;
      }
    });

    int i = 0;
    int longestWaiting = 0;
    int time = 0;
    while (true) {
      // add cur time cows
      time = Math.max(time, cows[i].arriveTime);
      Cow lastCow = cows[i];
      Cow curCow = cows[i];
      while (curCow.arriveTime == lastCow.arriveTime) {
        pq.add(curCow);
        i++;
        if (i == N) break;
        lastCow = curCow;
        curCow = cows[i];
      }
      if (i == N) break;

      int nextArriveTime = curCow.arriveTime;

      while (!pq.isEmpty() && time < nextArriveTime) {
        Cow curInLine = pq.poll();
        longestWaiting = Math.max(longestWaiting, time - curInLine.arriveTime);
        time += curInLine.t;
      }
    }

    pw.println(longestWaiting);

    sc.close();
    pw.close();
  }

  private static class Cow {
    int priority;
    int arriveTime;
    int t;

    public Cow(int priority, int arriveTime, int t) {
      this.priority = priority;
      this.arriveTime = arriveTime;
      this.t = t;
    }
  }
}