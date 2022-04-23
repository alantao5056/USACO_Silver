import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Year {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("./test/2.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int K = sc.nextInt();

    Set<Integer> segments = new HashSet<>();
    segments.add(0);
    for (int i = 0; i < N; i++) {
      int y = sc.nextInt();
      segments.add((y-1)/12+1);
    }

    List<Integer> segmentsArr = new ArrayList<>(segments);
    Collections.sort(segmentsArr);

    List<Integer> gaps = new ArrayList<>();

    int prev = -1;
    for (int i : segmentsArr) {
      if (prev != -1) {
        gaps.add(i - prev - 1);
      }
      prev = i;
    }

    Collections.sort(gaps);

    int toremove = gaps.size() + 1 - K;
    int total = 0;
    for (int i = 0; i < toremove; i++) {
      total += gaps.get(i);
    }

    System.out.println((segmentsArr.size()+total-1)*12);
    sc.close();
  }
}