import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Citystate {
  static int N;
  public static void main(String[] args) throws IOException {
    String in = new String(Files.readAllBytes(Paths.get("citystate.in")));
    PrintWriter pr = new PrintWriter(new FileWriter("citystate.out"));
    StringTokenizer st = new StringTokenizer(in);

    N = Integer.parseInt(st.nextToken());
    HashMap<String, Integer> cityHashSet = new HashMap<>();

    String city;
    String state;
    String cityInit;
    for (int i = 0; i < N; i++) {
      city = st.nextToken();
      cityInit = city.substring(0, 2);
      state = st.nextToken();
      if(!cityInit.equals(state)) {
				String key = cityInit + state;
				if(!cityHashSet.containsKey(key)) {
					cityHashSet.put(key, 0);
				}
				cityHashSet.put(key, cityHashSet.get(key) + 1);
			}
    }


    int count = 0;
		for(String key: cityHashSet.keySet()) {
			String otherKey = key.substring(2) + key.substring(0, 2);
			if(cityHashSet.containsKey(otherKey)) {
				count += cityHashSet.get(key) * cityHashSet.get(otherKey);
			}
		}

    pr.println(count/2);

    pr.close();
  }
}
