import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Meetings {
  public static class Cow {
    int position;
    int weight;
    boolean direction;
    boolean stopped = false;
    Cow left;
    Cow right;
    Cow(int position, int weight, boolean direction) {
      this.position = position;
      this.weight = weight;
      this.direction = direction;
    }
  }

  static int getMeetings(int L, Cow[] cows) {
    int totalWeight = 0;
    for (Cow c : cows) {
      totalWeight += c.weight;
    }
    int curWeight = 0;
    int meetings = 0; // return this

    while (curWeight < totalWeight / 2) {
      // get next meeting
      int minDistance = Integer.MIN_VALUE;
      Cow nextMeetingCow1 = null;
      Cow nextMeetingCow2 = null;
      for (Cow c : cows) {
        if (c.direction) {
          minDistance = Math.min(minDistance, c.right.position - c.position);
          nextMeetingCow1 = c;
          nextMeetingCow2 = c.right;
        } else {
          minDistance = Math.min(minDistance, c.position - c.left.position);
          nextMeetingCow1 = c;
          nextMeetingCow2 = c.left;
        }
      }

      meetings++;

      // update all cow positions
      for (Cow c : cows) {
        if (c.direction) {
          c.position += minDistance;
          if (c.position >= L && !c.stopped) {
            // hit barn
            c.stopped = true;
            curWeight += c.weight;
          }
        } else {
          c.position -= minDistance;
          if (c.position <= 0 && !c.stopped) {
            // hit barn
            c.stopped = true;
            curWeight += c.weight;
          }
        }
      }
      
      // reverse directions
      nextMeetingCow1.direction = !nextMeetingCow1.direction;
      nextMeetingCow2.direction = !nextMeetingCow2.direction;
    }

    return meetings;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
    PrintWriter pr = new PrintWriter(new FileWriter("meetings.out"));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());

    // initialize cows
    Cow[] cows = new Cow[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) == 1);
    }

    Arrays.sort(cows, new Comparator<Cow>(){
      public int compare (Cow c1, Cow c2) {
        if (c1.position > c2.position) {
          return 0;
        }
        return 1;
      }
    });

    cows[0].left = null;
    cows[0].right = cows[1];

    cows[N - 1].left = cows[N - 2];
    cows[N - 1].right = null;
    
    for (int i = 1; i < N - 1; i++) {
      cows[i].left = cows[i - 1];
      cows[i].right = cows[i + 1];
    }

    br.close();
    pr.println(getMeetings(L, cows));
    pr.close();
  }
}