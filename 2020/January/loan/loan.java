import java.io.*;
import java.lang.Math;

class loan {
  static long M;
  public static Boolean routine(long left, long days, long X) {
    long y = (long) left / X;
    if (y < M) {
      return days *M >= left;
    }
    if (y*days < left) return false;
    if (left <= 0) return true;

    long end = y*X;
    long givey = (left - end)/y + 1;
    return routine(left - y*givey, days-givey, X);
  }

  // X is between 1 and K or 1 and N/M
  public static long binarySearchX(long N, long K, long M) {
    long lo = 1;
    long hi = Math.min(K, (long) Math.ceil(N/M));

    while (lo < hi) {
      long mid = (long) (lo + hi)/2;
      if (routine(N, K, mid)) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    if (routine(N, K, lo)) {
      return lo;
    } else {
      return lo-1;
    }
  }
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("loan.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("loan.out"));
    String[] line = br.readLine().split("\\s+");
    br.close();

    long N = Long.parseLong(line[0]);
    long K = Long.parseLong(line[1]);
    M = Long.parseLong(line[2]);


    pw.println(binarySearchX(N, K, M));
    pw.close();
  }
}
