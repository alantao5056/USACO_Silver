import java.io.*;
import java.lang.Math;

public class Moobuzz2 {
  public static long getNumberOfMoos(int x) {
    long a = (long) x/3;
    long b = (long) x/5;
    long c = (long) x/15;
    return x - (a+b-c);
  }

  public static int getNumberSpoken(int N) {
    int lo = 0;
    int hi = 2000000000;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;

      long r = getNumberOfMoos(mid);
      if (r < N) {
        lo = mid + 1;
      } else if (r > N) {
        hi = mid - 1;
      } else {
        // found
        return mid;
      }
    }
    return lo;
  }

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("moobuzz.out"));

    int N = Integer.parseInt(br.readLine().strip());

    br.close();
    pr.println(getNumberSpoken(N));
    pr.close();
  }
}
