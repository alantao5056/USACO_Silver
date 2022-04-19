import java.io.*;

public class Bcount {
  public static int[] getQuery(int[][] prefixSum, int start, int end) {
    int[] result = new int[3];
    if (start == 1) {
      result[0] = prefixSum[end - 1][0];
      result[1] = prefixSum[end - 1][1];
      result[2] = prefixSum[end - 1][2];
    } else {
      result[0] = prefixSum[end - 1][0] - prefixSum[start - 2][0];
      result[1] = prefixSum[end - 1][1] - prefixSum[start - 2][1];
      result[2] = prefixSum[end - 1][2] - prefixSum[start - 2][2];
    }
    return result;
  }

  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader("bcount.in"));
    String[] in = reader.readLine().split("\\s+");
    int N = Integer.parseInt(in[0]);
    int Q = Integer.parseInt(in[1]);

    int[][] prefixSum = new int[N][3];
    int curH = 0;
    int curG = 0;
    int curJ = 0;
    for (int i = 0; i < N; i++) {
      Integer cur = Integer.parseInt(reader.readLine());
      if (cur == 1) {
        curH++;
      } else if (cur == 2) {
        curG++;
      } else {
        curJ++;
      }
      prefixSum[i][0] = curH;
      prefixSum[i][1] = curG;
      prefixSum[i][2] = curJ;
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter("bcount.out"));

    for (int i = 0; i < Q; i++) {
      String[] q = reader.readLine().split("\\s+");
      int start = Integer.parseInt(q[0]);
      int end = Integer.parseInt(q[1]);
      int[] result = getQuery(prefixSum, start, end);
      writer.write(String.valueOf(result[0]) + " " + String.valueOf(result[1]) + " " + String.valueOf(result[2]) + "\n");
    }

    reader.close();
    writer.close();
  }
}