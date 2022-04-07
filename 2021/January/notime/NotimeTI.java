import java.util.Scanner;

public class NotimeTI {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    // Scanner sc = new Scanner(new java.io.File("./test/2.in"));

    int N = sc.nextInt();
    int Q = sc.nextInt();

    int[] fence = new int[N];
    int[] reverseFence = new int[N];
    char[] fenceStr = sc.next().toCharArray();

    for (int i = 0; i < N; i++) {
      int numChar = fenceStr[i] - 'A';
      fence[i] = numChar;
      reverseFence[N-i-1] = numChar;
    }

    // prefix left to right
    int[] prefixLToR = prefix(fence, N);
    int[] prefixRToL = prefix(reverseFence, N);

    // read and print
    for (int i = 0; i < Q; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();

      int startSum, endSum;
      if (start == 1) {
        startSum = 0;
      } else {
        startSum = prefixLToR[start - 2];
      }

      if (end == N) {
        endSum = 0;
      } else {
        endSum = prefixRToL[N-end-1];
      }
      System.out.println(startSum + endSum);
    }

    sc.close();
  }

  private static int[] prefix(int[] fence, int N) {
    int[] prefixLToR = new int[N];
    int[] alpha = new int[26];
    int curCount = 0;

    for (int i = 0; i < 26; i++) alpha[i] = -1;
    for (int i = 0; i < N; i++) {
      int curInt = fence[i];
      if (alpha[curInt] == -1) {
        prefixLToR[i] = ++curCount;
        alpha[curInt] = i;
        continue;
      }

      int lastSeenIndex = alpha[curInt];

      for (int j = 0; j < curInt; j++) {
        if (alpha[j] > lastSeenIndex && alpha[j] < i) {
          // cannot connect
          curCount++;
          break;
        }
      }
      prefixLToR[i] = curCount;
      alpha[curInt] = i;
    }
    return prefixLToR;
  }
}