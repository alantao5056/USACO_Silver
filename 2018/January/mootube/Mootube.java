import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

class Mootube {
  static class Node {
    List<Edge> next = new ArrayList<>();
    int value = -1;
    int id;

    Node(int id) {
      this.id = id;
    }
  }

  static class Edge {
    int value;
    Node next;

    Edge(int value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  static int[][] relationships;

  public static int findAboveK(int K, Node start) {
    Queue<Node> q = new LinkedList<>();
    int count = 0; // return this
    Set<Integer> visited = new HashSet<>();
    start.value = 2147483647;
    q.add(start);

    while (!q.isEmpty()) {
      Node cur = q.poll();
      visited.add(cur.id);
      for (Edge e : cur.next) {
        Node neighbor = e.next;
        if (!visited.contains(neighbor.id)) {
          neighbor.value = Math.min(e.value, cur.value);
          relationships[start.id][neighbor.id] = neighbor.value;
          relationships[neighbor.id][start.id] = neighbor.value;

          if (neighbor.value >= K) {
            count++;
          }
          q.add(neighbor);
        }
      }
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("mootube.out"));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());

    Node[] nodeArr = new Node[N + 1];
    relationships = new int[N + 1][N + 1];

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());

      relationships[a][b] = value;
      relationships[b][a] = value;

      if (nodeArr[a] == null) {
        nodeArr[a] = new Node(a);
      }
      if (nodeArr[b] == null) {
        nodeArr[b] = new Node(b);
      }

      nodeArr[a].next.add(new Edge(value, nodeArr[b]));
      nodeArr[b].next.add(new Edge(value, nodeArr[a]));
    }

    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      int K = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());

      if (visited.contains(start)) {
        // for cycle relationships
        int count = 0;
        for (int j : relationships[start]) {
          if (j >= K) {
            count++;
          }
        }
        pr.println(count);
      } else {
        visited.add(start);
        pr.println(findAboveK(K, nodeArr[start]));
      }

    }

    br.close();
    pr.close();
  }
}