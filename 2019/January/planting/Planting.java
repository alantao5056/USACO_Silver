import java.io.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.lang.Math;

public class Planting {
  static int count = 0;
  static Field[] fields;
  static int N;

  static class Field {
    public Integer type = null;
    public List<Field> neighbors = new ArrayList<>();
    public Set<Integer> cannotBe = new HashSet<>();
    public Integer fieldID;

    public Boolean notVisited() {
      return type == null;
    }
  }

  public static void getMinTypesOfGrass(Field cur) {
    // assigning cur.type
    for (int i = 1; i < 100001; i++) {
      if (!cur.cannotBe.contains(i)) {
        count = Math.max(count, i);
        cur.type = i;
        break;
      }
    }

    // adding to cannotBe
    for (Field c : cur.neighbors) {
      if (c.notVisited()) {
        c.cannotBe.add(cur.type);
      }
      for (Field cc : c.neighbors) {
        if (cc.notVisited()) {
          cc.cannotBe.add(cur.type);
        }
      }
    }

    if (cur.fieldID != N) {
      getMinTypesOfGrass(fields[cur.fieldID + 1]);
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("planting.in"));
    N = Integer.parseInt(reader.readLine());
    fields = new Field[N + 1];

    for (int i = 0; i < N - 1; i++) {
      String[] line = reader.readLine().split("\\s+");
      Integer key = Integer.parseInt(line[0]);
      Integer value = Integer.parseInt(line[1]);

      // key to value
      if (fields[key] == null) {
        fields[key] = new Field();
        fields[key].fieldID = key;
      }
      if (fields[value] == null) {
        fields[value] = new Field();
        fields[value].fieldID = value;
      }
      fields[key].neighbors.add(fields[value]);
      
      // value to key
      fields[value].neighbors.add(fields[key]);
      
    }

    reader.close();
    getMinTypesOfGrass(fields[1]);

    BufferedWriter writer = new BufferedWriter(new FileWriter("planting.out"));
    writer.write(Integer.toString(count));
    writer.write('\n');
    writer.close();
  }
}