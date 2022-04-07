import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Barns
 */
public class Barns {
  public static void main(String[] args) throws Exception {
    //* reads test cases and prints
    // Scanner sc = new Scanner(new java.io.File("barns.in"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    for (int i = 0; i < T; i++) {
      System.out.println(getMinCost(sc));
    }

    sc.close();
  }

  private static long getMinCost(Scanner sc) {
    //* read graph and get minCost
    int N = sc.nextInt();
    int M = sc.nextInt();

    // initialize graph
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N+1; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < M; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    boolean[] visited = new boolean[N+1];

    // get startGroup & endGroup
    var startGroup = findGroup(visited, graph, 1);
    Collections.sort(startGroup);
    if (visited[N]) {
      // same group
      return 0;
    }
    var endGroup = findGroup(visited, graph, N);
    Collections.sort(endGroup);

    // get groups
    List<List<Integer>> groups = new ArrayList<>();
    for (int i = 1; i < N; i++) {
      if (!visited[i]) {
        groups.add(findGroup(visited, graph, i));
      }
    }

    // cycle groups and keep track of min
    long[] startCostArr = getCostArr(startGroup, N);
    long[] endCostArr = getCostArr(endGroup, N);

    long minCost = Long.MAX_VALUE;

    // get minCost of startGroup to endGroup
    for (var s : startGroup) {
      minCost = Math.min(minCost, endCostArr[s]);
    }

    for (var group : groups) {
      long minStartCost = Long.MAX_VALUE;
      long minEndCost = Long.MAX_VALUE;

      for (var g : group) {
        minStartCost = Math.min(minStartCost, startCostArr[g]);
        minEndCost = Math.min(minEndCost, endCostArr[g]);
      }

      minCost = Math.min(minCost, minStartCost + minEndCost);
    }

    return minCost;
  }

  private static List<Integer> findGroup(boolean[] visited, List<List<Integer>> graph, int start) {
    //* finds groups
    Queue<Integer> q = new LinkedList<>();
    List<Integer> result = new ArrayList<>();

    q.add(start);
    result.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {
      int cur = q.poll();
      for (var g : graph.get(cur)) {
        if (!visited[g]) {
          visited[g] = true;
          q.add(g);
          result.add(g);
        }
      }
    }
    return result;
  }
  private static long[] getCostArr(List<Integer> startGroup, int N) {
    long[] costArr = new long[N+1];

    // do first
    long curAdd = 1;
    for (int i = startGroup.get(0)-1; i > 0; i--) {
      costArr[i] = curAdd * curAdd;
      curAdd++;
    }

    // do middle
    for (int i = 1; i < startGroup.size(); i++) {
      int s = startGroup.get(i-1)+1;
      int e = startGroup.get(i)-1;
      curAdd = 1;

      while (s < e) {
        costArr[s] = curAdd * curAdd;
        costArr[e] = curAdd * curAdd;

        s++;
        e--;
        curAdd++;
      }
      if (s==e) {
        costArr[s] = curAdd * curAdd;
      }
    }
    // do end
    curAdd = 1;
    for (int i = startGroup.get(startGroup.size()-1)+1; i < N+1; i++) {
      costArr[i] = curAdd * curAdd;
      curAdd++;
    }
    
    return costArr;
  }
}