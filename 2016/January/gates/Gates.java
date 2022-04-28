import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Gates {
  static int N;
  static Cell[][] farm;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("gates.in"));
    PrintWriter pw = new PrintWriter("gates.out");

    N = sc.nextInt();
    farm = new Cell[N*2+2][N*2+2];

    for (int i = 1; i < N*2+1; i++) {
      for (int j = 1; j < N*2+1; j++) {
        farm[i][j] = new Cell(i, j);
      }
    }

    char[] line = sc.next().toCharArray();
    int curX = N;
    int curY = N;
    for (int i = 0; i < N; i++) {
      if (line[i] == 'N') {
        farm[curX][curY].right = true;
        farm[curX][curY+1].left = true;
        curX--;
      } else if (line[i] == 'E') {
        curY++;
        farm[curX][curY].down = true;
        farm[curX+1][curY].up = true;
      } else if (line[i] == 'S') {
        curX++;
        farm[curX][curY].right = true;
        farm[curX][curY+1].left = true;
      } else { // line[i] = 'W'
        farm[curX][curY].down = true;
        farm[curX+1][curY].up = true;
        curY--;
      }
    }
    // for (int i = 1; i < N*2+1; i++) {
    //   for (int j = 1; j < N*2+1; j++) {
    //     if (farm[i][j].up && farm[i][j].down && farm[i][j].left && farm[i][j].right) {
    //       System.out.println("HIHIHIHIH");
    //     }
    //   }
    // }

    // flood fill
    int count = 0;
    for (int i = 1; i < N*2+1; i++) {
      for (int j = 1; j < N*2+1; j++) {
        if (farm[i][j].visited) continue;
        count++;
        Queue<Cell> q = new LinkedList<>();
        q.add(farm[i][j]);
        farm[i][j].visited = true;
        while (!q.isEmpty()) {
          Cell curCell = q.poll();
          int x = curCell.x;
          int y = curCell.y;

          if (!curCell.down && farm[x+1][y] != null && !farm[x+1][y].visited) {
            q.add(farm[x+1][y]);
            farm[x+1][y].visited = true;
          }
          if (!curCell.right && farm[x][y+1] != null && !farm[x][y+1].visited) {
            q.add(farm[x][y+1]);
            farm[x][y+1].visited = true;
          }
          if (!curCell.up && farm[x-1][y] != null && !farm[x-1][y].visited) {
            q.add(farm[x-1][y]);
            farm[x-1][y].visited = true;
          }
          if (!curCell.left && farm[x][y-1] != null && !farm[x][y-1].visited) {
            q.add(farm[x][y-1]);
            farm[x][y-1].visited = true;
          }
        }
      }
    }

    pw.println(count-1);
    
    sc.close();
    pw.close();
  }

  public static class Cell {
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    boolean visited = false;
    int x;
    int y;
    public Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public String toString() {
      return Boolean.toString(visited);
    }
  }
}