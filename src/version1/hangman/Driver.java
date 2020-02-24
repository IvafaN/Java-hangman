package version1.hangman;

import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    Question ans = new Question();
    Dealer dealer = new Dealer(ans);
    Scanner sc = new Scanner(System.in);

    dealer.start();
    while (!dealer.getIsDone()) {
      dealer.ask(sc);
      dealer.confirm();
      if (dealer.getIsFail()) {
        dealer.lose();
        return;
      }
    }
    dealer.win();
    sc.close();
  }
}