import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Meetings3 {
  static int N;
  static int L;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("meetings.in"));
    PrintWriter pw = new PrintWriter("meetings.out");
    N = sc.nextInt();
    L = sc.nextInt();
    
    // solve
    List<Cow> leftCows = new ArrayList<>();
    List<Cow> rightCows = new ArrayList<>();
    Cow[] allCows = new Cow[N];
    int totalWeight = 0;

    for (int i = 0; i < N; i++) {
      int w = sc.nextInt();
      int p = sc.nextInt();
      int d = sc.nextInt();
      Cow cow = new Cow(w, p, d);
      allCows[i] = cow;
      totalWeight += w;
      if (d == -1) {
        leftCows.add(cow);
      } else {
        rightCows.add(cow);
      }
    }

    Collections.sort(leftCows, (a, b) -> {return a.position - b.position;});
    Collections.sort(rightCows, (a, b) -> {return b.position - a.position;});

    Arrays.sort(allCows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.position - b.position;
      }
    });

    int[] rightPositions = new int[rightCows.size()];
    for (int i = 0; i < rightCows.size(); i++) {
      rightPositions[i] = rightCows.get(i).position;
    }

    for (int i = 0; i < leftCows.size(); i++) {
      leftCows.get(i).time = leftCows.get(i).position;
      leftCows.get(i).weightCarried = allCows[i].weight;
    }

    for (int i = 0; i < rightCows.size(); i++) {
      rightCows.get(i).time = L-rightCows.get(i).position;
      rightCows.get(i).weightCarried = allCows[N-i-1].weight;
    }

    // find T
    Arrays.sort(allCows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.time - b.time;
      }
    });

    int curWeight = 0;
    int curIdx = 0;
    while (curWeight * 2 < totalWeight) {
      curWeight += allCows[curIdx].weightCarried;
      curIdx++;
    }
    
    int T2 = allCows[curIdx-1].time * 2;

    Arrays.sort(allCows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.position - b.position;
      }
    });

    // find number of meetings

    Arrays.sort(rightPositions);

    int meetings = 0;

    for (int i = 0; i < leftCows.size(); i++) {
      int leftIdx = Arrays.binarySearch(rightPositions, leftCows.get(i).position - T2 + 1);
      int rightIdx = Arrays.binarySearch(rightPositions, leftCows.get(i).position);
      rightIdx = rightIdx * -1;
      if (leftIdx < 0) {
        leftIdx = leftIdx * -1;
      } else if (leftIdx == 0) {
        leftIdx++;
      }

      System.out.println(String.format("%d %d", rightIdx, leftIdx));

      meetings += rightIdx - leftIdx;
    }

    pw.println(meetings);

    sc.close();
    pw.close();
  }

  private static class Cow {
    int weight;
    int position;
    int time;
    int weightCarried;
    boolean direction;

    public Cow(int weight, int position, int direction) {
      this.weight = weight;
      this.position = position;
      this.direction = direction == 1 ? true : false;
    }

    @Override
    public String toString() {
      return Integer.toString(position);
    }
  }
}