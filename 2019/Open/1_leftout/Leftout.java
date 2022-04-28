import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Leftout {
  static int N;
  public static void main(String[] args) throws IOException{
    String fileInput = new String(Files.readAllBytes(Paths.get("leftout.in")));
    PrintWriter pr = new PrintWriter(new FileWriter("leftout.out"));
    StringTokenizer st = new StringTokenizer(fileInput);
    
    final int N = Integer.parseInt(st.nextToken());

    // first sequence 
    List<Integer> sequence = new ArrayList<>();
    String column = st.nextToken();
    char curValue = column.charAt(0);
    int streak = 0;

    for (char c : column.toCharArray()) {
      if (c == curValue) {
        streak++;
      } else {
        sequence.add(streak);
        streak = 1;
        curValue = c;
      }
    }
    sequence.add(streak);



    int foundOnRow = -1;
    int foundOnColumn = -1;
    boolean foundOnce = false;
    boolean problemOnOrig = false;
    for (int i = 1; i < N; i++) {
      column = st.nextToken();
      boolean flag = false;
      char[] columnChar = column.toCharArray();
      char lastSequence = columnChar[0] == 'L' ? 'R' : 'L';
      int j = 0;

      for (int s : sequence) {
        char cur = columnChar[j];
        for (int k = j + 1; k < j + s; k++) {
          if (columnChar[k] != cur || columnChar[k] == lastSequence) {
            flag = true;
            foundOnColumn = k;
            foundOnRow = i;
            break;
          }
        }
        if (cur == lastSequence) {
          flag = true;
          foundOnColumn = j;
          foundOnRow = i;
          break;
        }
        j += s;
        lastSequence = cur;
      }

      if (flag) {
        // found wrong sequence
        if (foundOnce) {
          // already found once, problem on original sequence
          problemOnOrig = true;
          break;
        } else {
          foundOnce = true;
        }
      }

    }

    if (problemOnOrig) {
      pr.printf("%d %d%n", 1, 1);
    } else {
      if (foundOnRow == -1) {
        pr.println(-1);
      } else {
        pr.printf("%d %d%n", ++foundOnRow, ++foundOnColumn == 3 ? 1 : foundOnColumn);
      }
    }

    pr.close();
  }
}