import java.io.*;

class Moobuzz {
  public static int getNumberSpoken(int N) {
    int curSpoken = 1;
    int count = 0;
    while (count < N) {
      // check if divisible by 3
      boolean divisibleBy3 = curSpoken % 3==0;

      // check if divisible by 5
      boolean divisibleBy5 = curSpoken % 5==0;

      if (!divisibleBy3 && !divisibleBy5) {
        count++;
      }
      curSpoken++;
    }

    return curSpoken - 1;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("moobuzz.out"));

    int N = Integer.parseInt(br.readLine().strip());

    br.close();
    pr.println(getNumberSpoken(N));
    pr.close();
  }
}