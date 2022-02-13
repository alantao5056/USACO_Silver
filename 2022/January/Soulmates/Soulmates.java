import java.util.Scanner;

public class Soulmates {

  public static void main(String[] args) throws Exception {
  Scanner sc = new Scanner(new java.io.File("1.in"));
//    Scanner sc = new Scanner(System.in);
  
    int N = sc.nextInt();
  
    for (int i = 0; i < N; i++) {
      long a = sc.nextLong();
      long b = sc.nextLong();
    
      System.out.println(getMinChange(a, b));
    }
  
    sc.close();
  }

  private static long getMinChange(long a, long b) {
    if (a == b) return 0;
    
    int curSteps = 0;
    if (a > b) {
      if (a % 2 == 1) {
        a++;
        curSteps++;
      }
      return getMinChange(a/2, b) + 1+curSteps;
      
    } else if (a <= b/2) {
      if (b % 2 == 1) {
        b--; 
        curSteps++;
      }
      return getMinChange(a, b/2) + 1+curSteps;
      
    } else { // b/2 < a < b
      long difference = b - a;
      if (a % 2 == 1) {
        a++;
        curSteps++;
      }
      if (b % 2 == 1) {
        b--;
        curSteps++;
      }
      
      return Math.min(difference, getMinChange(a/2, b/2) + 2+curSteps);
    }
    
  }
}
