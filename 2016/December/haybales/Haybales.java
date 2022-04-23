import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Haybales {
  static int N;
  static long[] hays;
  static StringTokenizer st;

  static int getInt() {
    return Integer.parseInt(st.nextToken());
  }

  static long getLong() {
    return Long.parseLong(st.nextToken());
  }

  static int getNumberOfHaybales(long start, long end) {
    boolean addOne = false;

    int lo = 0, hi = N - 1;
    while (lo <= hi) {
      int mid = lo + (hi-lo)/2;

      // Check if start is present at mid
      if (hays[mid] == start) {
        hi = mid;
        lo = mid;
        // addOne = true;
        break;
      }

      // If start greater, ignore left half
      if (hays[mid] < start) {
        lo = mid + 1;
      }
      // If start is smaller, ignore right half
      else {
        hi = mid - 1;
      }
    }

    int startIndex = 0;
    if (hi < N + 1 && hi > -1) {
      // hi is in range
      if (hays[hi] >= start) {
        // hi is valid
        startIndex = hi;
      }
    }
    if (lo < N + 1 && lo > -1) {
      // lo is in range
      if (hays[lo] >= start) {
        if (startIndex == hi) {
          // found both
          startIndex = hi < lo ? lo : hi;
        } else {
          startIndex = lo;
        }
      }
    }

    lo = 0;
    hi = N - 1;
    while (lo <= hi) {
      int mid = lo + (hi-lo)/2;

      // Check if end is present at mid
      if (hays[mid] == end) {
        hi = mid;
        lo = mid;
        addOne = true;
        break;
      }

      // If end greater, ignore left half
      if (hays[mid] < end) {
        lo = mid + 1;
      }
      // If end is smaller, ignore right half
      else {
        hi = mid - 1;
      }
    }

    int endIndex = 0;
    if (hi < N + 1 && hi > -1) {
      // hi is in range
      if (hays[hi] >= end) {
        // hi is valid
        endIndex = hi;
      }
    }
    if (lo < N + 1 && lo > -1) {
      // lo is in range
      if (hays[lo] >= end) {
        if (endIndex == hi) {
          // found both
          endIndex = hi < lo ? lo : hi;
        } else {
          endIndex = lo;
        }
      }
    }

    return addOne ? endIndex - startIndex + 1 : endIndex - startIndex;
  }

  public static void main(String[] args) throws IOException{
    String in = new String(Files.readAllBytes(Paths.get("haybales.in")));
    PrintWriter pr = new PrintWriter(new FileWriter("haybales.out"));
    st = new StringTokenizer(in);

    N = getInt();
    int Q = getInt();

    hays = new long[N + 1];

    for (int i = 0; i < N; i++) {
      hays[i] = getLong();
    }
    hays[N] = Long.MAX_VALUE;

    Arrays.sort(hays);

    for (int i = 0; i < Q; i++) {
      pr.println(getNumberOfHaybales(getLong(), getLong()));
    }

    pr.close();

  }
}