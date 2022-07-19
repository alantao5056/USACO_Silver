import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class BubbleSort {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("bubble.in"));
    PrintWriter pw = new PrintWriter("bubble.out");
    N = sc.nextInt();
    
    // solve
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      int cur = sc.nextInt()-1;

      arr[i] = cur;
    }

    System.out.println(bubbleSort(arr));

    sc.close();
    pw.close();
  }

  private static int bubbleSort(int arr[]) { 
    int n = arr.length; 
    int count = 0;
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      count++;
      for (int i = 0; i < n-1; i++) {
        if (arr[i] > arr[i + 1]) {
          // swap arr[i+1] and arr[i]
          int temp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;
          sorted = false;
        }
      }
    }

    return count;
  }
}