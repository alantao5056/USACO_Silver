import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lemonade {
  static int N;
  static int[] cows;
  static StringTokenizer st;

  private static int getInt() {
    return Integer.parseInt(st.nextToken());
  }

  private static int getMinInLine() {
    int curInLine = 0;
    for (int i = N - 1; i >= 0; i--) {
      if (cows[i] >= curInLine) {
        curInLine++;
      }
    }
    return curInLine;
  }

  public static void main(String[] args) throws IOException{
    String in = new String(Files.readAllBytes(Paths.get("lemonade.in")));
    st = new StringTokenizer(in);
    PrintWriter pw = new PrintWriter(new FileWriter("lemonade.out"));

    N = getInt();
    cows = new int[N];

    for (int i = 0; i < N; i++) {
      cows[i] = getInt();
    }

    Arrays.sort(cows);

    pw.println(getMinInLine());

    pw.close();
  }
}