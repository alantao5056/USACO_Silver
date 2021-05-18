import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class lightson {

  public static int getMaxRooms(HashMap<List<Integer>, List<List<Integer>>> lightHash, List<Integer> cur, Set<List<Integer>> visited, Set<List<Integer>> lit, int count) {
    // add to visited
    visited.add(cur);
    // path didn't end
    List<List<Integer>> options = lightHash.get(cur);
    for (List<Integer> p : options) {
      // adding lit rooms first
      if (!lit.contains(p)) {
        count++;
        lit.add(p);
      }
      // trying to go there
      // getting paths
      List<Integer> path1 = new ArrayList<>() {{add(p.get(0)); add(p.get(1) + 1);}};
      List<Integer> path2 = new ArrayList<>() {{add(p.get(0)); add(p.get(1) - 1);}};
      List<Integer> path3 = new ArrayList<>() {{add(p.get(0) + 1); add(p.get(1));}};
      List<Integer> path4 = new ArrayList<>() {{add(p.get(0) - 1); add(p.get(1));}};
      // checking if available
      if (visited.contains(path1)) {
        count = getMaxRooms(lightHash, p, visited, lit, count);
      } else if (visited.contains(path2)) {
        count = getMaxRooms(lightHash, p, visited, lit, count);
      } else if (visited.contains(path3)) {
        count = getMaxRooms(lightHash, p, visited, lit, count);
      } else if (visited.contains(path4)) {
        count = getMaxRooms(lightHash, p, visited, lit, count);
      }
    }



    return count;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("lightson.in"));
    String line = reader.readLine();
    System.out.println(line);
    String[] items = line.split("\\s+");

    int N = Integer.parseInt(items[0]);
    int M = Integer.parseInt(items[1]);

    HashMap<List<Integer>, List<List<Integer>>> lightHash = new HashMap<>();

    for (int i = 0; i < M; i++) {
      String curLine = reader.readLine();
      String[] curItems = curLine.split("\\s+");
      // initializing key
      List<Integer> key = new ArrayList<>() {
        {
          add(Integer.parseInt(curItems[0]));
          add(Integer.parseInt(curItems[1]));
        }
      };
      // initializing value
      List<Integer> value = new ArrayList<>() {
        {
          add(Integer.parseInt(curItems[2]));
          add(Integer.parseInt(curItems[3]));
        }
      };
      // appending to lightHash
      if (lightHash.get(key) == null) {
        // if there is no existing value
        List<List<Integer>> nestedValue = new ArrayList<>() {
          {
            add(value);
          }
        };
        lightHash.put(key, nestedValue);
      } else {
        // if there is an existing value
        lightHash.get(key).add(value);
      }
    }
    
    reader.close();

    List<Integer> cur = new ArrayList<>() {
      {
        add(1);
        add(1);
      }
    };
    Set<List<Integer>> visited = new HashSet<>();
    Set<List<Integer>> lit = new HashSet<>();
    System.out.println(getMaxRooms(lightHash, cur, visited, lit, 0));
  }
}
