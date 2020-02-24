package version1.hangman;

public class Driver {
  public static void main(String[] args) {
    Answer ans = new Answer();
    Dealer dealer = new Dealer(ans);

    dealer.start();
    while (!dealer.getIsDone()) {
      dealer.ask();
      if (dealer.getIsFail()) {
        dealer.lose();
        return;
      }
    }
    dealer.win();
  }
}