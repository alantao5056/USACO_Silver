import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Clocktree {
  private static int N;
  private static StringTokenizer st;
  private static PrintWriter pw;
  private static Room[] rooms;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(new String(Files.readAllBytes(Paths.get("clocktree.in"))));
    pw = new PrintWriter("clocktree.out");
    N = getInt();
    rooms = new Room[N + 1];

    for (int i = 1; i < N + 1; i++) {
      rooms[i] = new Room(i, getInt()%12);
    }

    for (int i = 0; i < N - 1; i++) {
      Room a = rooms[getInt()];
      Room b = rooms[getInt()];

      a.nbs.add(b);
      b.nbs.add(a);
    }

    int count = 0;
    for (int i = 1; i < N + 1; i++) {
      rooms[i].visit(null);
      if (rooms[i].value == 0 || rooms[i].value == 1) {
        // good
        count++;
      }
      for (int j = 1; j < N + 1; j++) rooms[j].reset();
    }

    pw.println(count);
    pw.close();
  }

  public static class Room {
    public int index;
    private int initValue;
    public int value;
    public List<Room> nbs = new ArrayList<>();

    public Room(int index, int value) {
      this.index = index;
      this.initValue = value;
      this.value = value;
    }

    public void reset() {
      value = initValue;
    }

    public void addToValue(int x) {
      value += x;
      value %= 12;
    }

    public void visit(Room from) {
      for (Room nb : nbs) {
        if (nb == from) continue;
        nb.visit(this);
        this.addToValue(12 - nb.value);
        nb.value = 0;
      }
    }
  }

  private static int getInt() {
    return Integer.parseInt(st.nextToken());
  }
}