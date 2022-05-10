import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Rental {
  static int N;
  static int M;
  static int R;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("rental.in"));
    // Scanner sc = new Scanner(System.in);

    PrintWriter pw = new PrintWriter("rental.out");
    
    N = sc.nextInt();
    M = sc.nextInt();
    R = sc.nextInt();

    Integer[] cows = new Integer[N];
    for (int i = 0; i < N; i++) {
      cows[i] = sc.nextInt();
    }

    Arrays.sort(cows, Collections.reverseOrder());

    Offer[] offers = new Offer[M];
    for (int i = 0; i < M; i++) {
      offers[i] = new Offer(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(offers, (Offer a, Offer b) -> b.cost - a.cost);

    Integer[] sell = new Integer[R];
    for (int i = 0; i < R; i++) {
      sell[i] = sc.nextInt();
    }

    Arrays.sort(sell, Collections.reverseOrder());

    pw.println(getMaxProfit(cows, offers, sell));
    
    pw.close();
    sc.close();
  }

  private static long getMaxProfit(Integer[] cows, Offer[] offers, Integer[] sell) {
    int cowIdx1 = 0;
    int cowIdx2 = N-1;
    int rentIdx = 0;
    int sellIdx = 0;
    long totalProfit = 0;
    long rentProfit = 0;
    long sellProfit = 0;
    while (cowIdx1 <= cowIdx2) {
      if (rentProfit == 0) {
        int curMilk = cows[cowIdx1];
        while (rentIdx < M && curMilk > 0) {
          if (curMilk < offers[rentIdx].left) {
            // give all
            offers[rentIdx].left -= curMilk;
            rentProfit += curMilk * offers[rentIdx].cost;
            curMilk = 0;
            break;
          } else {
            curMilk -= offers[rentIdx].left;
            rentProfit += offers[rentIdx].left * offers[rentIdx].cost;
            rentIdx++;
          }
        }
      }


      if (sellIdx < R && sellProfit == 0) {
        sellProfit = sell[sellIdx];
        sellIdx++;
      }

      if (rentProfit > sellProfit) {
        totalProfit += rentProfit;
        rentProfit = 0;
        cowIdx1++;
      } else {
        totalProfit += sellProfit;
        sellProfit = 0;
        cowIdx2--;
      }
    }
    return totalProfit;
  }

  public static class Offer {
    int left;
    int cost;

    public Offer(int left, int cost) {
      this.left = left;
      this.cost = cost;
    }
  }
}
