import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Closest {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("./test/2.in"));
    Scanner sc = new Scanner(System.in);

    int K = sc.nextInt();
    int M = sc.nextInt();
    int N = sc.nextInt();

    Grass[] grass = new Grass[K];
    for (int i = 0; i < K; i++) {
      grass[i] = new Grass(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(grass, new Comparator<Grass>() {
      public int compare(Grass a, Grass b) {
        return a.pos - b.pos;
      }
    });

    int[] nohjCows = new int[M];
    for (int i = 0; i < M; i++) {
      nohjCows[i] = sc.nextInt();
    }

    Arrays.sort(nohjCows);

    List<Interval> intervals = new ArrayList<>();
    int lastCow = -1;
    int grassIdx = 0;
    int curCow = 0;
    for (int i = 0; i < M+1; i++) {
      if (i != M) {
        curCow = nohjCows[i];
      } else {
        curCow = Integer.MAX_VALUE;
      }
      List<Grass> curGrass = new ArrayList<>();
      while (grassIdx < K && grass[grassIdx].pos < curCow) {
        curGrass.add(grass[grassIdx]);
        grassIdx++;
      }

      if (i == M) { curCow = -1; }
      if (!curGrass.isEmpty()) {
        Interval curInterval = new Interval(curGrass, lastCow, curCow);
        intervals.add(curInterval);
      }
      lastCow = curCow;
    }

    List<Long> tastiness = new ArrayList<>();

    for (var i : intervals) {
      long maxTaste = getMaxTastiness(i);
      tastiness.add(maxTaste);
      tastiness.add(i.grassSum - maxTaste);
    }

    Collections.sort(tastiness, Collections.reverseOrder());

    long total = 0;
    int tastinessSize = tastiness.size();
    for (int i = 0; i < N && i < tastinessSize; i++) {
      total += tastiness.get(i);
    }

    System.out.println(total);

    sc.close();
  }

  private static long getMaxTastiness(Interval interval) {
    if (interval.left == -1 || interval.right == -1) {
      return interval.grassSum;
    }
    List<Grass> grass = interval.grass;
    int grassSize = grass.size();

    long total = 0;
    long maxTotal = 0;
    int endIdx = 0;
    for (int i = 0; i < grassSize; i++) {
      long start = grass.get(i).pos * 2 - interval.left;
      long end = (interval.right + start) / 2;
      if (i != 0) {
        total -= grass.get(i-1).tastiness;
      }

      while (endIdx < grassSize && grass.get(endIdx).pos < end) {
        total += grass.get(endIdx).tastiness;
        endIdx++;
      }
      maxTotal = Math.max(maxTotal, total);
    }

    return maxTotal;
  }

  private static class Grass {
    public int pos;
    public int tastiness;

    public Grass (int pos, int tastiness) {
      this.pos = pos;
      this.tastiness = tastiness;
    }
  }

  private static class Interval {
    public List<Grass> grass;
    public long grassSum;
    public int left;
    public int right;

    public Interval (List<Grass> grass, int left, int right) {
      this.grass = grass;
      this.left = left;
      this.right = right;
      for (var g : grass) {
        grassSum += g.tastiness;
      }
    }
  }
}