import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Meetings2 {
  public static class Cow {
    int position;
    int weight;
    boolean direction;
    Cow(int weight, int position, boolean direction) {
      this.position = position;
      this.weight = weight;
      this.direction = direction;
    }
  }

  public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
    List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());

    Map<K, V> result = new LinkedHashMap<>();
    for (Entry<K, V> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

  public static int getMeetings(int L, Cow[] cows) {
    HashMap<Integer, Integer> leftBarn = new HashMap<>();
    HashMap<Integer, Integer> rightBarn = new HashMap<>();

    for (Cow cow : cows) {
      if (cow.direction) {
        // going to right barn
        rightBarn.put(L - cow.position, -1);
      } else {
        // going to left barn
        leftBarn.put(cow.position, -1);
      }
    }

    // putting leftBarn
    int i = 0;
    for (int k : leftBarn.keySet()) {
      leftBarn.put(k, cows[i].weight);
      i++;
    }

    // putting rightBarn
    for (int k : rightBarn.keySet()) {
      rightBarn.put(k, cows[i].weight);
      i++;
    }

    HashMap<Integer, Integer> barn = new HashMap<>();
    barn.putAll(leftBarn);
    barn.putAll(rightBarn);


    Map<Integer, Integer> barnMap = sortByValue(barn);
    int count = 0;
    int totalCount = 0;
    int T = 0;
    for (Integer integer : barnMap.values()){
      totalCount += integer;
    }
    for (Map.Entry<Integer, Integer> entry : barnMap.entrySet()) {
      count += entry.getValue();
      T = entry.getKey();
      if (count >= totalCount / 2) {
        break;
      }
    }

    int result = 0;
    // get meetings based on T
    for (int c = 0; c < cows.length; c++) {
      if (cows[c].direction) {
        // cow facing right
        int maxPos = cows[c].position + T*2;
        for (int d = c + 1; d < cows.length; d++) {
          if (cows[d].position <= maxPos) {
            if(!cows[d].direction) {
              result++;
            }
          } else {
            break;
          }
        }
      }
    }

    return result;
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

    Arrays.sort(cows, (c1, c2) -> {
      if (c1.position > c2.position) {
        return 0;
      }
      return 1;
    });

    br.close();
    pr.println(getMeetings(L, cows));
    pr.close();
  }
}
