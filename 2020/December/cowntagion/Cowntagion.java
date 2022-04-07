import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Cowntagion {
  static int N;
  static TreeNode[] farms;
  static Scanner in;

  private static class TreeNode {
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent = null;
    private int id;
    private int sickCows = 0;

    TreeNode (int id) {
      this.id = id;
    }

    public void addChild(TreeNode child) {
      children.add(child);
    }

    public void assignParent(TreeNode p) {
      parent = p;
    }

    public void doubleSickCows() {
      sickCows *= 2;
    }

    public List<TreeNode> getChildren() {
      return children;
    }

    public int getChildrenLength() {
      return children.size();
    }

    public int getSickCows() {
      return sickCows;
    }

    public void addOneToSickCows() {
      sickCows++;
    }

    @Override
    public String toString() {
      return Integer.toString(id);
    }
  }

  private static int getInt() {
    return Integer.parseInt(in.nextLine());
  }

  private static int getNumberOfDays() {
    int count = 0;
    Queue<Integer> q = new LinkedList<>();
    farms[1].addOneToSickCows();
    q.add(1);

    while (!q.isEmpty()) {
      int curId = q.poll();
      TreeNode curFarm = farms[curId];

      while (curFarm.getSickCows() < curFarm.getChildrenLength() + 1) {
        curFarm.doubleSickCows();
        count++;
      }

      for (TreeNode c : curFarm.getChildren()) {
        c.addOneToSickCows();
        q.add(c.id);
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args){
    in = new Scanner(System.in);
    N = getInt();
    farms = new TreeNode[N + 1];
    int[][] pairs = new int[N-1][2];

    for (int i = 0; i < N - 1; i++) {
      String[] arr = in.nextLine().split("\\s+");
      int a = Integer.parseInt(arr[0]);
      int b = Integer.parseInt(arr[1]);
      pairs[i][0] = a;
      pairs[i][1] = b;
    }

    boolean[] currentLevel = new boolean[N + 1];
    currentLevel[1] = true;
    farms[1] = new TreeNode(1);

    while (true) {
      boolean ended = true;
      for (int i = 0; i < N - 1; i++) {
        int curA = pairs[i][0];
        int curB = pairs[i][1];
        if (currentLevel[curA] && !currentLevel[curB]) {
          // could create class
          currentLevel[curB] = true;
          farms[curB] = new TreeNode(curB);
          farms[curB].assignParent(farms[curA]);
          farms[curA].addChild(farms[curB]);
          ended = false;
        } else if (currentLevel[curB] && !currentLevel[curA]) {
          // could create class
          currentLevel[curA] = true;
          farms[curA] = new TreeNode(curA);
          farms[curA].assignParent(farms[curB]);
          farms[curB].addChild(farms[curA]);
          ended = false;
        }
      }
      if (ended) {
        break;
      }
    }

    in.close();

    System.out.println(getNumberOfDays());
  }
}