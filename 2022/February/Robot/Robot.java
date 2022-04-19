import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Robot {
  private static List<List<Coord>> result = new ArrayList<>();
  
  static void combinationUtil(Coord arr[], Coord data[], int start, int end, int index, int r) {
    if (index == r) {
      result.add(new ArrayList<Coord>(Arrays.asList(data)));
      return;
    }

    for (int i=start; i<=end && end-i+1 >= r-index; i++) {
      data[index] = arr[i];
      combinationUtil(arr, data, i+1, end, index+1, r);
    }
  }
  
  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    Coord target = new Coord(sc.nextLong(), sc.nextLong());
    
    Coord[] coords = new Coord[N];
    for (int i = 0; i < N; i++) {
      coords[i] = new Coord(sc.nextLong(), sc.nextLong());
    }
    
    for (int i = 1; i < N+1; i++) {
      long count = 0;
      Coord[] data = new Coord[i];
      combinationUtil(coords, data, 0, N-1, 0, i);
      
      for (var l : result) {
        long xSum = 0;
        long ySum = 0;
        for (Coord c : l) {
          xSum += c.x;
          ySum += c.y;
        }
        if (xSum == target.x && ySum == target.y) {
          // found
          count++;
        }
      }
      System.out.println(count);
      result = new ArrayList<>();
    }
    
    sc.close();
  }
  
  private static class Coord {
    long x;
    long y;
    public Coord(long x, long y) {
      this.x = x;
      this.y = y;
    }
    
    @Override
    public String toString() {
      // TODO Auto-generated method stub
      return String.format("%d %d", x, y);
    }
  }

}
