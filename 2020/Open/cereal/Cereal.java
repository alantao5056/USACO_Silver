import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Cereal {
  static StringTokenizer st;
  static int N;
  static int M;
  static int count;
  static int[] occ;
  static int[] firstChoice;
  static int[] secondChoice;
  static Boolean[] choice;

  private static int getInt() {
    return Integer.parseInt(st.nextToken());
  }

  private static void eat(int index) {
    if (occ[firstChoice[index]] > index) {
      // first choice less priority
      choice[index] = true;
      int kickedOutIndex = occ[firstChoice[index]];
      occ[firstChoice[index]] = index;
      eat(kickedOutIndex);
    } else if (occ[firstChoice[index]] == 0) {
      // no one eating
      count++;
      choice[index] = true;
      occ[firstChoice[index]] = index;
      return;
    } else if (occ[secondChoice[index]] > index) {
      // second choice less priority
      choice[index] = false;
      int kickedOutIndex = occ[secondChoice[index]];
      occ[secondChoice[index]] = index;
      eat(kickedOutIndex);
    } else if (occ[secondChoice[index]] == 0) {
      // no one eating
      count++;
      choice[index] = false;
      occ[secondChoice[index]] = index;
      return;
    }
  }

  public static void main(String[] args) throws IOException{
    String fileInput = new String(Files.readAllBytes(Paths.get("cereal.in")));
    PrintWriter pw = new PrintWriter(new FileWriter("cereal.out"));
    st = new StringTokenizer(fileInput);

    N = getInt();
    M = getInt();
    count = 0;

    occ = new int[M + 1];
    firstChoice = new int[N + 1];
    secondChoice = new int[N + 1];
    choice = new Boolean[N + 1];
    for (int i = 0; i < N + 1; i++) {
      choice[i] = null;
    }


    for (int i = 1; i < N + 1; i++) {
      int f = getInt();
      int s = getInt();

      firstChoice[i] = f;
      secondChoice[i] = s;
    }

    int[] result = new int[N + 1];
    for (int i = N; i > 0; i--) {
      eat(i);
      result[i] = count;
    }

    for (int i = 1; i < N + 1; i++) {
      pw.println(result[i]);
    }
    pw.close();
  }
}
