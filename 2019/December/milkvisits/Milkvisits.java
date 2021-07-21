import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Milkvisits {
  public static class Barn {
    boolean happy;
    boolean milk; // true = H, false = G
    int id;
    List<Barn> next = new ArrayList<>();

    Barn (boolean milk, int id) {
      this.milk = milk;
      this.id = id;
    }

    public void addToNext(Barn b) {
      this.next.add(b);
    }
  }

  public static boolean getHappy(Barn[] barns, int start, int end, boolean milk) {
    barns[start].happy = milk == barns[start].milk; // init start happy
    if (barns[start].happy) {
      return true;
    }
    
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[barns.length];
    q.add(start);
    visited[start] = true;
    
    while (!q.isEmpty()) {
      // happy can't turn into unhappy. but unhappy can turn into happy
      Barn cur = barns[q.poll()];

      if (cur.id == end) {
        return cur.happy;
      }
      
      for (Barn neighbor : cur.next) {
        if (!visited[neighbor.id]) {
          q.add(neighbor.id);
          if (!cur.happy) {
            if (neighbor.milk == milk) {
              // turn into happy
              neighbor.happy = true;
            } else {
              neighbor.happy = false;
            }
          } else {
            neighbor.happy = true;
          }
          visited[neighbor.id] = true;
        }
      }
    }
    return barns[end].happy;
  }

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("milkvisits.out"));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    String farmers = st.nextToken();

    Barn[] barns = new Barn[N + 1];

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (barns[a] == null) {
        barns[a] = new Barn(farmers.charAt(a-1) == 'H', a);
      }
      if (barns[b] == null) {
        barns[b] = new Barn(farmers.charAt(b-1) == 'H', b);
      }

      barns[a].addToNext(barns[b]);
      barns[b].addToNext(barns[a]);
    }

    
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      boolean milk = st.nextToken().charAt(0) == 'H';
      
      pr.print(getHappy(barns, start, end, milk) ? 1 : 0);
    }
    
    br.close();
    pr.println();

    pr.close();
  }
}