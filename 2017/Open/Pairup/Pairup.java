import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Pairup {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("pairup.in"));
    PrintWriter pw = new PrintWriter("pairup.out");
    N = sc.nextInt();
    
    // solve
    Group[] groups = new Group[N+1];
    groups[0] = new Group(-1, -1);
    for (int i = 1; i <= N; i++) {
      groups[i] = new Group(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(groups, new Comparator<Group>() {
      public int compare(Group a, Group b) {
        return a.value - b.value;
      }
    });

    // two pointers
    // pair two ends everytime
    int start = 1;
    int end = N;
    int maxTime = 0;

    while (start < end) {
      maxTime = Math.max(maxTime, groups[start].value + groups[end].value);
      if (groups[start].quantity < groups[end].quantity) {
        groups[end].quantity -= groups[start].quantity;
        start++;
      } else if (groups[end].quantity < groups[start].quantity) {
        groups[start].quantity -= groups[end].quantity;
        end--;
      } else {
        // same
        start++;
        end--;
      }
    }

    if (start == end) {
      maxTime = Math.max(maxTime, groups[start].value*2);
    }

    pw.println(maxTime);

    sc.close();
    pw.close();
  }

  private static class Group {
    int value;
    int quantity;
    public Group(int quantity, int value) {
      this.value = value;
      this.quantity = quantity;
    }
  }
}