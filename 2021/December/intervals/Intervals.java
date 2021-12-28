import java.util.Scanner;

public class Intervals {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("intervals.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int M = sc.nextInt();

    int[] a = new int[N];
    int[] b = new int[N];
    int[] counterA = new int[M+1];
    int[] counterB = new int[M+1];

    for (int i = 0; i < N; i++) {
      a[i] = sc.nextInt();
      b[i] = sc.nextInt();
      counterA[a[i]]++;
      counterB[b[i]]++;
    }

    long[] prefix = new long[2*M+2];

    for (int i = 0; i < M+1; i++) {
      for (int j = 0; j < M+1; j++) {
        prefix[i+j] += (long)counterA[i]*counterA[j];
        prefix[i+j+1] -= (long)counterB[i]*counterB[j];
      }
    }

    System.out.println(prefix[0]);
    for (int i = 1; i < 2*M+1; i++) {
      prefix[i] += prefix[i-1];
      System.out.println(prefix[i]);
    }

    sc.close();
  }
}