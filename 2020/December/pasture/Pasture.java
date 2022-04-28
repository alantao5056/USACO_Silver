import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Pasture {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("pasture.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    // setup grid
    Cow[] cows = new Cow[N];
    boolean[][] grid = new boolean[N+1][N+1];

    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(sc.nextInt()+1, sc.nextInt()+1);
    }

    Arrays.sort(cows, new SortByX());

    for (int i = 0; i < N; i++) {
      cows[i].posX = i+1;
    }

    Arrays.sort(cows, new SortByY());
    for (int i = 0; i < N; i++) {
      cows[i].posY = i+1;
      grid[cows[i].posX][cows[i].posY] = true;
    }

    // setup prefix
    int[][] prefix = new int[N+1][N+1];

    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + (grid[i][j]?1:0);
      }
    }

    // algorithm
    long total = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        // left
        int maxX = Math.max(cows[i].posX, cows[j].posX);
        int minX = Math.min(cows[i].posX, cows[j].posX);
        int leftTotal = prefix[maxX][cows[i].posY] - prefix[minX-1][cows[i].posY];
        int rightTotal = prefix[maxX][N] - prefix[minX-1][N] - prefix[maxX][cows[j].posY-1] + prefix[minX-1][cows[j].posY-1];
        total += leftTotal * rightTotal;
      }
    }

    System.out.println(total + 1);

    sc.close();
  }

  public static class Cow {
    public int x;
    public int y;
    public int posX;
    public int posY;

    public Cow(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static class SortByX implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2) {
      return c1.x - c2.x;
    }
  }
  
  public static class SortByY implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2) {
      return c1.y - c2.y;
    }
  }
}