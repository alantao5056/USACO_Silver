import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Lightson3 {
  static int N;
  static int M;
  static Set<Room> toBeSwitched = new HashSet<Room>();

  static class Room {
    public Boolean LightIsOn = false;
    public Boolean visited = false;
    public Boolean switched = false;
    public List<Room> switches = new ArrayList<>();
  }

  public static int getCount(Room[][] grid) {
    int count = 0;
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        if (grid[i][j].LightIsOn) count++;
      }
    }
    return count;
  }

  public static void recursiveDFS(Room[][] grid, int x, int y) {
    grid[x][y].visited = true;
    if (!grid[x][y].switched) {
      // haven't switched
      for (Room r : grid[x][y].switches) {
        toBeSwitched.add(r);
      }
      grid[x][y].switched = true;
    }

    if (grid[x - 1][y] != null && !grid[x - 1][y].visited && grid[x - 1][y].LightIsOn) {
      // up is not visited
      recursiveDFS(grid, x - 1, y);
    }
    if (grid[x + 1][y] != null && !grid[x + 1][y].visited && grid[x + 1][y].LightIsOn) {
      // down is not visited
      recursiveDFS(grid, x + 1, y);
    }
    if (grid[x][y - 1] != null && !grid[x][y - 1].visited && grid[x][y - 1].LightIsOn) {
      // left is not visited
      recursiveDFS(grid, x, y - 1);
    }
    if (grid[x][y + 1] != null && !grid[x][y + 1].visited && grid[x][y + 1].LightIsOn) {
      // right is not visited
      recursiveDFS(grid, x, y + 1);
    }
  }

  public static int getMaxLit(Room[][] grid) {
    do {
      toBeSwitched = new HashSet<Room>();
      // cleaning everything to unvisited
      for (int i = 1; i < N + 1; i++) {
        for(int j = 1; j < N + 1; j++) {
          grid[i][j].visited = false;
        }
      }
      recursiveDFS(grid, 1, 1);
      // switching on the lights
      for (Room r : toBeSwitched) {
        r.LightIsOn = true;
      }
    } while (!toBeSwitched.isEmpty());
    return getCount(grid);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("lightson.in"));
    String line = reader.readLine();
    String[] items = line.split("\\s+");
    N = Integer.parseInt(items[0]);
    M = Integer.parseInt(items[1]);
    Room[][] grid = new Room[N + 2][N + 2];

    // filling in rooms
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        grid[i][j] = new Room();
      }
    }

    grid[1][1].LightIsOn = true;

    for (int i = 0; i < M; i++) {
      String[] curLine = reader.readLine().split("\\s+");
      grid[Integer.parseInt(curLine[0])][Integer.parseInt(curLine[1])].switches.add(grid[Integer.parseInt(curLine[2])][Integer.parseInt(curLine[3])]);
    }
    reader.close();

    // System.out.println(getMaxLit(grid));

    // writing file
    BufferedWriter writer = new BufferedWriter(new FileWriter("lightson.out"));
    writer.write(Integer.toString(getMaxLit(grid)));;
    writer.write('\n');
    writer.close();
  }
}
