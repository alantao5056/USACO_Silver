import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Pails {
  static int X;
  static int Y;
  static int K;
  static int M;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("pails.in"));
    PrintWriter pw = new PrintWriter("pails.out");
    X = sc.nextInt();
    Y = sc.nextInt();
    K = sc.nextInt();
    M = sc.nextInt();
    
    // solve
    Set<TwoPails> pails = new HashSet<>();
    Set<Integer> visited = new HashSet<>();
    pails.add(new TwoPails(0, 0));
    pails.add(new TwoPails(X, 0));
    pails.add(new TwoPails(0, Y));

    visited.add(0);
    visited.add(X);
    visited.add(Y);

    for (int i = 0; i < K-1; i++) {
      Set<TwoPails> newPails = new HashSet<>();
      for (TwoPails p : pails) {
        TwoPails aToB = p.pourAToB();
        TwoPails bToA = p.pourBToA();
        TwoPails fill1 = new TwoPails(X, p.b);
        TwoPails fill2 = new TwoPails(p.a, Y);
        TwoPails empty1 = new TwoPails(0, p.b);
        TwoPails empty2 = new TwoPails(p.a, 0);

        newPails.add(aToB); 
        newPails.add(bToA);
        newPails.add(fill1);
        newPails.add(fill2);
        newPails.add(empty1);
        newPails.add(empty2);

        visited.add(aToB.total);
        visited.add(bToA.total);
        visited.add(fill1.total);
        visited.add(fill2.total);
        visited.add(empty1.total);
        visited.add(empty2.total);

      }
      pails = newPails;
    }

    List<Integer> visitedList = new ArrayList<>(visited);
    Collections.sort(visitedList);

    if (M > visitedList.get(visitedList.size()-1)) {
      pw.println(M - visitedList.get(visitedList.size()-1));
      pw.close();
      return;
    }
    if (M < visitedList.get(0)) {
      pw.println(visitedList.get(0)-M);
      pw.close();
      return;
    }

    int binarySearchValue = Collections.binarySearch(visitedList, M);

    if (binarySearchValue>=0) {
      pw.println(0);
    } else {
      int a = visitedList.get(binarySearchValue * -1 - 2);
      int b = visitedList.get(binarySearchValue* - 1 - 1);
      pw.println(Math.min(M-a, b-M));
    }

    sc.close();
    pw.close();
  }

  private static class TwoPails {
    int a;
    int b;
    int total;

    public TwoPails(int a, int b) {
      this.a = a;
      this.b = b;
      total = a + b;
    }

    @Override
		public int hashCode() {
      return a*31+b;
		}

    @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TwoPails other = (TwoPails) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}

    public TwoPails pourAToB() {
      if (b + a > Y) {
        return new TwoPails(a-(Y-b), Y);
      }

      // can pour
      return new TwoPails(0, a+b);
    }

    public TwoPails pourBToA() {
      if (a + b > X) {
        return new TwoPails(X, b-(X-a));
      }

      // can pour
      return new TwoPails(a+b, 0);
    }
  }
}