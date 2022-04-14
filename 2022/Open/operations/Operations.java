import java.util.Scanner;

public class Operations {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);

    char[] s = sc.next().toCharArray();
    int[] prefixC = new int[s.length+1];
    int[] prefixO = new int[s.length+1];
    int[] prefixW = new int[s.length+1];

    int curPrefixC = 0;
    int curPrefixO = 0;
    int curPrefixW = 0;
    for (int i = 1; i < s.length+1; i++) {
      if (s[i-1] == 'C') {
        curPrefixC++;
      } else if (s[i-1] == 'O') {
        curPrefixO++;
      } else {
        curPrefixW++;
      }

      prefixC[i] = curPrefixC;
      prefixO[i] = curPrefixO;
      prefixW[i] = curPrefixW;
    }

    int N = sc.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();

      if (((prefixC[end] - prefixC[start-1]) % 2 == 1 && (prefixO[end] - prefixO[start-1]) % 2 == 0 && (prefixW[end] - prefixW[start-1]) % 2 == 0)
      || ((prefixC[end] - prefixC[start-1]) % 2 == 0 && (prefixO[end] - prefixO[start-1]) % 2 == 1 && (prefixW[end] - prefixW[start-1]) % 2 == 1)) {
        sb.append('Y');
      } else {
        sb.append('N');
      }
    }
    System.out.println(sb.toString());

    sc.close();
  }
}