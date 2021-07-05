import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lightson2 {

  private static int N;
  private static int M;

  static class Room {
    public boolean isLightOn = false;
    public boolean visited = false;
    public boolean switched = false;
    public List<Room> switches = new ArrayList<>();
    public Room up;
    public Room down;
    public Room left;
    public Room right;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("lightson.in"));
    String line = reader.readLine();
    String[] items = line.split("\\s+");
    N = Integer.parseInt(items[0]);
    M = Integer.parseInt(items[1]);

    Room[][] grid = new Room[N + 2][N + 2];
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        grid[i][j] = new Room();
      }
    }

    grid[1][1].isLightOn = true;

    for (int i = 0; i < M; i++) {
      String curLine = reader.readLine();
      String[] curItems = curLine.split("\\s+");
      grid[Integer.parseInt(curItems[0])][Integer.parseInt(curItems[1])].switches.add(grid[Integer.parseInt(curItems[2])][Integer.parseInt(curItems[3])]);
    }
    reader.close();

    // start DFS
    System.out.println(getMaxRooms(grid));
  }

  private static int getMaxRooms(Room[][] grid) {
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        grid[i][j].up = grid[i-1][j];
        grid[i][j].down = grid[i+1][j];
        grid[i][j].left = grid[i][j-1];
        grid[i][j].right = grid[i][j+1];
      }
    }

    int lastSwitchCount = -1;
    int switchCount = 0;
    int count = 0;

    do {
      DFS(grid[1][1]);
      
      lastSwitchCount = switchCount;
      switchCount = 0;
      count = 0;
      for (int i = 1; i < N + 1; i++) {
        for (int j = 1; j < N + 1; j++) {
          grid[i][j].visited = false;
          if (grid[i][j].isLightOn) {
            count++;
          }
          if (grid[i][j].switched) {
            switchCount++;
          }
        }
      }
    } while (lastSwitchCount != switchCount);

    return count;
  }

  private static void DFS(Room room) {
    room.visited = true;
    if (!room.switched) {
      for (Room r : room.switches) {
        r.isLightOn = true;
      }
      room.switched = true;
    }

    if (room.up != null && room.up.isLightOn && !room.up.visited) {
      DFS(room.up);
    }
    if (room.down != null && room.down.isLightOn && !room.down.visited) {
      DFS(room.down);
    }
    if (room.left != null && room.left.isLightOn && !room.left.visited) {
      DFS(room.left);
    }
    if (room.right != null && room.right.isLightOn && !room.right.visited) {
      DFS(room.right);
    }
  }
}
