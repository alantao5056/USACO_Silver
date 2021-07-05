import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
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

  static int N;
  static int Q;

  public static int findAboveK(int K, Node start) {
    Queue<Node> q = new LinkedList<>();
    int count = 0; // return this
    boolean[] visited = new boolean[N + 1];
    start.value = Integer.MAX_VALUE;
    q.add(start);

    while (!q.isEmpty()) {
      Node cur = q.poll();
      visited[cur.id] = true;
      for (Edge e : cur.next) {
        Node neighbor = e.next;
        if (!visited[neighbor.id]) {
          neighbor.value = Math.min(e.value, cur.value);

          if (neighbor.value >= K) {
            count++;
            q.add(neighbor);
          }
        }
      }
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("mootube.out"));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());

    Node[] nodeArr = new Node[N + 1];
    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());

      if (nodeArr[a] == null) {
        nodeArr[a] = new Node(a);
      }
      if (nodeArr[b] == null) {
        nodeArr[b] = new Node(b);
      }

      nodeArr[a].next.add(new Edge(value, nodeArr[b]));
      nodeArr[b].next.add(new Edge(value, nodeArr[a]));
    }

    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      int K = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());
      pr.println(findAboveK(K, nodeArr[start]));

    }

    br.close();
    pr.close();
  }
}