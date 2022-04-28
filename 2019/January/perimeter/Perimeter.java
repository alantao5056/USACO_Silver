import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Perimeter {
  static Set<List<Integer>> toBeVisited = new HashSet<>();
  static Set<List<Integer>> visited = new HashSet<>();
  static Boolean[][] grid;
  static int N;

  public static int[] getBiggestArea(List<Integer> cur, int count, int perimeter) {
    toBeVisited.remove(cur);
    visited.add(cur);
  
    List<Integer> downCur = new ArrayList<>();
    downCur.add(cur.get(0) + 1);
    downCur.add(cur.get(1));
    if (grid[cur.get(0) + 1][cur.get(1)] != null) {
      if (!visited.contains(downCur)) {
        count++;
        int[] out = getBiggestArea(downCur, count, perimeter);
        count = out[0];
        perimeter = out[1];
      }
    } else perimeter++;

    List<Integer> upCur = new ArrayList<>();
    upCur.add(cur.get(0) - 1);
    upCur.add(cur.get(1));
    if (grid[cur.get(0) - 1][cur.get(1)] != null) {
      if (!visited.contains(upCur)) {
        count++;
        int[] out = getBiggestArea(upCur, count, perimeter);
        count = out[0];
        perimeter = out[1];
      }
    } else perimeter++;

    List<Integer> rightCur = new ArrayList<>();
    rightCur.add(cur.get(0));
    rightCur.add(cur.get(1) + 1);
    if (grid[cur.get(0)][cur.get(1) + 1] != null) {
      if (!visited.contains(rightCur)) {
        count++;
        int[] out = getBiggestArea(rightCur, count, perimeter);
        count = out[0];
        perimeter = out[1];
      }
    } else perimeter++;

    List<Integer> leftCur = new ArrayList<>();
    leftCur.add(cur.get(0));
    leftCur.add(cur.get(1) - 1);
    if (grid[cur.get(0)][cur.get(1) - 1] != null) {
      if (!visited.contains(leftCur)) {
        count++;
        int[] out = getBiggestArea(leftCur, count, perimeter);
        count = out[0];
        perimeter = out[1];
      }
    } else perimeter++;

    int[] result = {count, perimeter};
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("perimeter.in"));
    N = Integer.parseInt(reader.readLine());
    grid = new Boolean[N + 2][N + 2];
    String line;

    for (int i = 1; i < N + 1; i++) {
      line = reader.readLine();
      for (int j = 1; j < N + 1; j++) {
        char c = line.charAt(j - 1);
        if (c == '#') {
          grid[i][j] = true;
          List<Integer> coord = new ArrayList<>();
          coord.add(i);
          coord.add(j);
          toBeVisited.add(coord);
        }
      }
    }

    reader.close();

    int maxArea = -1;
    int minPerimeter = -1;
    while (!toBeVisited.isEmpty()) {
      for (List<Integer> c : toBeVisited) {
        int[] result = getBiggestArea(c, 0, 0);
        int area = result[0] + 1;
        int perimeter = result[1];

        if ((area == maxArea && perimeter < minPerimeter) || (area > maxArea)) {
          maxArea = area;
          minPerimeter = perimeter;
        }
        break;
      }
      visited.clear();
    }

    // System.out.println(maxArea);
    // System.out.println(minPerimeter);

    BufferedWriter writer = new BufferedWriter(new FileWriter("perimeter.out"));
    writer.write(Integer.toString(maxArea) + " " + Integer.toString(minPerimeter) + "\n");
    writer.close();
  }
}
