import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Multimoo {
  static int N;
  static int[][] board;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("multimoo.in"));
    PrintWriter pw = new PrintWriter("multimoo.out");
    N = sc.nextInt();
    board = new int[N+2][N+2];
    
    // solve
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    // flood fill get biggest reigion
    int[][] groups = new int[N+2][N+2];
    List<Integer> sizeList = new ArrayList<>();
    sizeList.add(0);
    int curGroupNum = 0;

    int maxGroupSize = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (groups[i][j] != 0) continue;
        curGroupNum++;
        groups[i][j] = curGroupNum;
        int curId = board[i][j];
        int groupSize = 0;

        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();

        q1.add(i);
        q2.add(j);

        while (!q1.isEmpty()) {
          int curI = q1.poll();
          int curJ = q2.poll();
          groupSize++;

          if (groups[curI+1][curJ] == 0 && board[curI+1][curJ] == curId) {
            q1.add(curI+1);
            q2.add(curJ);
            groups[curI+1][curJ] = curGroupNum;
          }

          if (groups[curI][curJ+1] == 0 && board[curI][curJ+1] == curId) {
            q1.add(curI);
            q2.add(curJ+1);
            groups[curI][curJ+1] = curGroupNum;
          }

          if (groups[curI][curJ-1] == 0 && board[curI][curJ-1] == curId) {
            q1.add(curI);
            q2.add(curJ-1);
            groups[curI][curJ-1] = curGroupNum;
          }

          if (groups[curI-1][curJ] == 0 && board[curI-1][curJ] == curId) {
            q1.add(curI-1);
            q2.add(curJ);
            groups[curI-1][curJ] = curGroupNum;
          }
        }

        maxGroupSize = Math.max(maxGroupSize, groupSize);
        sizeList.add(groupSize);
      }
    }

    pw.println(maxGroupSize);

    // find max touching
    boolean[][] visited = new boolean[N+2][N+2];
    int maxTwoGroups = 0;

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        // traverse
        if (visited[i][j]) continue;
        int curId = groups[i][j];
        visited[i][j] = true;
        Map<Integer, Integer> nbSizes = new HashMap<>();
        boolean[] visitedGroup = new boolean[curGroupNum+1];

        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();

        q1.add(i);
        q2.add(j);

        while (!q1.isEmpty()) {
          int curI = q1.poll();
          int curJ = q2.poll();

          if (groups[curI+1][curJ] == curId) {
            if (!visited[curI+1][curJ]) {
              q1.add(curI+1);
              q2.add(curJ);
              visited[curI+1][curJ] = true;
            }
          } else if (!visitedGroup[groups[curI+1][curJ]]) {
            int boardNum = board[curI+1][curJ];
            int groupNum = groups[curI+1][curJ];
            visitedGroup[groupNum] = true;
            if (nbSizes.containsKey(boardNum)) {
              nbSizes.put(boardNum, nbSizes.get(boardNum) + sizeList.get(groupNum));
            } else {
              nbSizes.put(boardNum, sizeList.get(groupNum));
            }
          }

          if (groups[curI][curJ+1] == curId) {
            if (!visited[curI][curJ+1]) {
              q1.add(curI);
              q2.add(curJ+1);
              visited[curI][curJ+1] = true;
            }
          } else if (!visitedGroup[groups[curI][curJ+1]]) {
            int boardNum = board[curI][curJ+1];
            int groupNum = groups[curI][curJ+1];
            visitedGroup[groupNum] = true;
            if (nbSizes.containsKey(boardNum)) {
              nbSizes.put(boardNum, nbSizes.get(boardNum) + sizeList.get(groupNum));
            } else {
              nbSizes.put(boardNum, sizeList.get(groupNum));
            }
          }

          if (groups[curI][curJ-1] == curId) {
            if (!visited[curI][curJ-1]) {
              q1.add(curI);
              q2.add(curJ-1);
              visited[curI][curJ-1] = true;
            }
          } else if (!visitedGroup[groups[curI][curJ-1]]) {
            int boardNum = board[curI][curJ-1];
            int groupNum = groups[curI][curJ-1];
            visitedGroup[groupNum] = true;
            if (nbSizes.containsKey(boardNum)) {
              nbSizes.put(boardNum, nbSizes.get(boardNum) + sizeList.get(groupNum));
            } else {
              nbSizes.put(boardNum, sizeList.get(groupNum));
            }
          }

          if (groups[curI-1][curJ] == curId) {
            if (!visited[curI-1][curJ]) {
              q1.add(curI-1);
              q2.add(curJ);
              visited[curI-1][curJ] = true;
            }
          } else if (!visitedGroup[groups[curI-1][curJ]]) {  
            int boardNum = board[curI-1][curJ];
            int groupNum = groups[curI-1][curJ];
            visitedGroup[groupNum] = true;
            if (nbSizes.containsKey(boardNum)) {
              nbSizes.put(boardNum, nbSizes.get(boardNum) + sizeList.get(groupNum));
            } else {
              nbSizes.put(boardNum, sizeList.get(groupNum));
            }
          }
        }

        int maxNbSize = 0;
        for (int num : nbSizes.values()) {
          maxNbSize = Math.max(maxNbSize, num);
        }
        maxTwoGroups = Math.max(maxTwoGroups, maxNbSize + sizeList.get(curId));
      }
    }

    pw.println(maxTwoGroups);

    sc.close();
    pw.close();
  }
}