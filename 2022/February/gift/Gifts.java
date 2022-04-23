import java.util.Scanner;

public class Gifts {

  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    
    int[][] prefs = new int[N][N];
    int[][] idxOfGift = new int[N][N];
    int[] curChoiceIdx = new int[N];
    int[] choiceToCow = new int[N];
    boolean[] locked = new boolean[N];
    
    for (int i = 0; i < N; i++) {
      choiceToCow[i] = i;
      for (int j = 0; j < N; j++) {
        prefs[i][j] = sc.nextInt()-1;
        idxOfGift[i][prefs[i][j]] = j;
        if (prefs[i][j] == i) {
          curChoiceIdx[i] = j;
          if (j == 0) locked[i] = true;
        }
      }
    }
    
//    solve
    for (int i = 0; i < N; i++) {
      if (curChoiceIdx[i] == 0) {
        // locked
        System.out.println(prefs[i][curChoiceIdx[i]] + 1);
        continue;
      }
      
      int curChoice = prefs[i][curChoiceIdx[i]];
      boolean better = false;
      
      for (int j = 0; j < curChoiceIdx[i]; j++) {
        // check if j can promote without damaging other cows
        int targetChoice = prefs[i][j];
        int cowWithChoiceIdx = choiceToCow[targetChoice];
        if (idxOfGift[cowWithChoiceIdx][curChoice] < curChoiceIdx[cowWithChoiceIdx]) {
          // the cow with the choice to give up
          // wants the curChoiceIdx to be smaller than his
          // if this happens we need to swap
//          curChoiceIdx[cowWithChoiceIdx] = idxOfGift[cowWithChoiceIdx][curChoice];
//          curChoiceIdx[i] = idxOfGift[i][targetChoice];
//          choiceToCow[curChoice] = cowWithChoiceIdx;
//          choiceToCow[targetChoice] = i;
          
//          int temp = prefs[cowWithChoiceIdx][idxOfGift[cowWithChoiceIdx][targetChoice]];
//          prefs[cowWithChoiceIdx][idxOfGift[cowWithChoiceIdx][targetChoice]] = prefs[cowWithChoiceIdx][idxOfGift[cowWithChoiceIdx][curChoice]];
//          prefs[cowWithChoiceIdx][idxOfGift[cowWithChoiceIdx][curChoice]] = temp;
//          
//          temp = prefs[i][idxOfGift[i][targetChoice]];
//          prefs[i][idxOfGift[i][targetChoice]] = prefs[i][idxOfGift[i][curChoice]];
//          prefs[i][idxOfGift[i][curChoice]] = temp;
          
//          int temp = idxOfGift[cowWithChoiceIdx][curChoice];
//          idxOfGift[cowWithChoiceIdx][curChoice] = idxOfGift[cowWithChoiceIdx][targetChoice];
//          idxOfGift[cowWithChoiceIdx][targetChoice] = temp;
//          
//          temp = idxOfGift[i][curChoice];
//          idxOfGift[i][curChoice] = idxOfGift[i][targetChoice];
//          idxOfGift[i][targetChoice] = temp;
//          if (idxOfGift[i][targetChoice] == 0) {
//            locked[i] = true;
//          }
//          if (idxOfGift[cowWithChoiceIdx][curChoice] == 0) {
//            locked[cowWithChoiceIdx] = true;
//          }
          System.out.println(targetChoice+1);
          better = true;
          break;
        }
      }
      if (!better) {
        System.out.println(curChoice + 1);
      }
    }
    
//    for (int i = 0; i < N; i++) {
//      System.out.println(prefs[i][curChoiceIdx[i]] + 1);
//    }
    
    sc.close();
  }

}
