//13/13
import java.io.*;
import java.util.*;

public class CorrectSolution {
	public static int n, l;
	public static int[][] cows;
	//position, weight, direction
	public static ArrayList<Integer> ltimes, rtimes;
	public static ArrayList<Integer> facingl;
	public static ArrayList<int[]> tw;
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(new File("test/2.in"));
//		Scanner s = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
//		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		n = s.nextInt();
		l = s.nextInt();
		cows = new int[n][3];
		ltimes = new ArrayList<>();
		rtimes = new ArrayList<>();
		tw = new ArrayList<>();
		facingl = new ArrayList<>();
		int totalweight = 0;
		for(int i = 0;i < n;i++) {
			int w = s.nextInt(), x = s.nextInt(), d = s.nextInt();
			cows[i] = new int[] {x, w, d};
			if(d == -1) facingl.add(x);
			totalweight += w;
		}
		Collections.sort(facingl);
		Arrays.sort(cows, Comparator.comparingInt(o -> o[0]));
		for(int i = 0;i < n;i++){
			if(cows[i][2] == -1) ltimes.add(cows[i][0]);
			else rtimes.add(l - cows[i][0]);
		}
		for(int i = 0;i < ltimes.size();i++) tw.add(new int[] {ltimes.get(i), cows[i][1]});
		Collections.reverse(rtimes);
		for(int i = 0;i < rtimes.size();i++) tw.add(new int[] {rtimes.get(i), cows[n - i - 1][1]});
		Collections.sort(tw, Comparator.comparingInt(o -> o[0]));
		int count = 0, ind = 0;
		while(2 * count < totalweight) {
			count += tw.get(ind)[1];
			ind++;
		}
		int t = tw.get(ind - 1)[0];
		count = 0;
		ind = 0;
		for(int i = 0;i < n;i++){
			if(cows[i][2] == 1){
				Integer higher = upperbound(cows[i][0] + (2 * t)), lower = lowerbound(cows[i][0]);
				count += higher - lower;
				System.out.println(higher + " " + lower);
			}
		}
//		System.out.println(Arrays.deepToString(cows));
		pw.println(count);
		pw.close();
	}
	public static int lowerbound(int val){
		int a = 0, b = facingl.size();
		while(a != b){
			int mid = (a + b) / 2;
			if(facingl.get(mid) >= val) b = mid;
			else a = mid + 1;
		}
		return a;
	}
	public static int upperbound(int val){
		int a = 0, b = facingl.size();
		while(a != b){
			int mid = (a + b) / 2;
			if(facingl.get(mid) > val) b = mid;
			else a = mid + 1;
		}
		return a;
	}
}