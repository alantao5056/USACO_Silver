import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Cereal2 {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("test/5.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    int M = sc.nextInt();
    
    // read and init cereals
    Cereal[] cereals = new Cereal[M+1];
    for (int i = 1; i < N+1; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      if (cereals[a] == null) {
        cereals[a] = new Cereal(a);
      }
      if (cereals[b] == null) {
        cereals[b] = new Cereal(b);
      }
      cereals[a].neighbors.add(cereals[b]);
      cereals[b].neighbors.add(cereals[a]);
      Cow cow = new Cow(i, cereals[a]);
      cereals[a].cows.add(cow);
      cereals[b].cows.add(cow);
    }
    
    // find groups
    List<Group> groups = new ArrayList<>();
    for (int i = 1; i < M+1; i++) {
      if (cereals[i] != null && !cereals[i].visited) {
        Group curGroup = new Group(cereals[i]);
        
        Stack<Cereal> stack = new Stack<>();
        stack.add(cereals[i]);
        cereals[i].visited = true;
        
        while (!stack.isEmpty()) {
          Cereal curCereal = stack.peek();
          curGroup.allCows.addAll(curCereal.cows);
          int nextCowIdx = curCereal.getNextCowIdx();
          if (nextCowIdx == -1) {
            // done
            stack.pop();
          } else {
            curGroup.visitedCows.add(curCereal.cows.get(nextCowIdx));
            curCereal.neighbors.get(nextCowIdx).visited = true;
            stack.push(curCereal.neighbors.get(nextCowIdx));
          }
        }
        
        groups.add(curGroup);
      }
    }
    
    List<Integer> order = new ArrayList<>();
    int totalHungry = 0;
    for (var g : groups) {
      int hungry = processGroup(g, order);
      totalHungry += (hungry == -1 ? 0 : hungry);
    }
    
    System.out.println(totalHungry);
    
    for (var o : order) {
      System.out.println(o);
    }
    
    sc.close();
  }
  
  private static int processGroup(Group group, List<Integer> order) {

    int remainCowsNum = group.allCows.size() - group.visitedCows.size();
    
    Cereal startCereal = group.startCereal;
    Set<Cow> hungryCows = new HashSet<>();
    Cow startCow = null;
    
    if (remainCowsNum > 0) {
      for (Cow c : group.allCows) {
        if (!group.visitedCows.contains(c)) {
          hungryCows.add(c);
        }
      }
      
      startCow = hungryCows.iterator().next();

      order.add(startCow.id);
      startCereal = startCow.firstChoice;
    }
    
    Stack<Cereal> stack = new Stack<>();
    stack.add(startCereal);
    startCereal.visited2 = true;
    
    while (!stack.empty()) {
      Cereal curCereal = stack.peek();
      int nextCowIdx = curCereal.getNextCowIdx2(hungryCows);
      
      if (nextCowIdx == -1) {
        stack.pop();
      } else {
        order.add(curCereal.cows.get(nextCowIdx).id);
        curCereal.neighbors.get(nextCowIdx).visited2 = true;
        stack.push(curCereal.neighbors.get(nextCowIdx));
      }
    }
    
    for (var h : hungryCows) {
      if (h != startCow) order.add(h.id);
    }
    
    return remainCowsNum-1;
  }
  
  private static class Cow {
    Cereal firstChoice;
    int id;
    
    public Cow(int id, Cereal firstChoice) {
      this.id = id;
      this.firstChoice = firstChoice;
    }
    @Override
    public String toString() {
      return Integer.toString(id);
    }
  }
  
  private static class Group {
    Cereal startCereal;
    Set<Cow> allCows = new HashSet<>();
    Set<Cow> visitedCows = new HashSet<>();
    
    public Group (Cereal startCereal) {
      this.startCereal = startCereal;
    }
    
    @Override
    public String toString() {
//      return allCows.toString();
      return visitedCows.toString();
    }
  }
  
  private static class Cereal {
    int id;
    List<Cereal> neighbors = new ArrayList<>();
    List<Cow> cows = new ArrayList<>();
    boolean visited = false;
    boolean visited2 = false;
    int cowsIdx = 0;
    int cowsIdx2 = 0;
    
    public Cereal(int id) {
      this.id = id;
    }
    @Override
    public String toString() {
      return Integer.toString(id);
    }
    
    public int getNextCowIdx() {
      while (cowsIdx < cows.size() && neighbors.get(cowsIdx).visited) {
        cowsIdx++;
      }
      if (cowsIdx < cows.size()) {
        return cowsIdx;
      }
      return -1;
    }

    public int getNextCowIdx2(Set<Cow> hungryCows) {
      while (cowsIdx2 < cows.size() && (neighbors.get(cowsIdx2).visited2 || hungryCows.contains(cows.get(cowsIdx2)))) {
        cowsIdx2++;
      }
      if (cowsIdx2 < cows.size()) {
        return cowsIdx2;
      }
      return -1;
    }
  }
}
