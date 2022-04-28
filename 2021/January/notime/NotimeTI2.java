import java.util.Scanner;
import java.util.Stack;

public class NotimeTI2 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    // Scanner sc = new Scanner(new java.io.File("./test/1.in"));

    int N = sc.nextInt();
    int Q = sc.nextInt();

    int[] fence = new int[N];
    char[] fenceStr = sc.next().toCharArray();

    for (int i = 0; i < N; i++) {
      fence[i] = fenceStr[i];
    }

    // prefix left to right
    int[] prefix = new int[N + 1];

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < N; i++) {
      prefix[i+1] = prefix[i];
      while (!stack.isEmpty() && stack.peek() > fence[i]) {
        stack.pop();
      }

      if (stack.isEmpty() || stack.peek() < fence[i]) {
        prefix[i+1]++;
        stack.push(fence[i]);
      }
    }

    // prefix right to left
    int[] suffix = new int[N + 1];

    stack = new Stack<>();

    for (int i = N - 1; i >= 0; i--) {
      suffix[i] = suffix[i+1];
      while (!stack.isEmpty() && stack.peek() > fence[i]) {
        stack.pop();
      }

      if (stack.isEmpty() || stack.peek() < fence[i]) {
        suffix[i]++;
        stack.push(fence[i]);
      }
    }

    // read and print
    for (int i = 0; i < Q; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();
      System.out.println(prefix[start - 1] + suffix[end]);
    }

    sc.close();
  }
}