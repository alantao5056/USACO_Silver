import java.util.Scanner;
import java.util.Stack;

public class Frisbee {

  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    
    Stack<Integer> value = new Stack<>();
    Stack<Integer> index = new Stack<>();
    long result = (N-1) * 2;
    
    for (int i = 0; i < N; i++) {
      int curValue = sc.nextInt();
      while (!value.isEmpty() && value.peek() < curValue) {
        if (index.size() > 1) {
          result += i - index.get(index.size() - 2) + 1;
        }
        index.pop();
        value.pop();
      }
      value.push(curValue);
      index.push(i);
    }
    
    System.out.println(result);
    
    sc.close();
  }

}
