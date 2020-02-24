package version1.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealer {
  final int DEFAULT_LIFE = 10;
  final String MASK_CHARACTER = "_";
  private Answer answer;
  private List<Guess> guesses = new ArrayList<Guess>();

  public Dealer(Answer answer) {
    this.answer = answer;
  }

  public Answer getAnswer() {
    return this.answer;
  }

  public List<Guess> getGuesses() {
    return this.guesses;
  }

  public boolean getIsDone() {
    String ans = displayAnswer();
    if (!ans.contains(MASK_CHARACTER)) {
      return true;
    }
    return false;
  }

  public boolean getIsFail() {
    return getWrongCount() >= DEFAULT_LIFE;
  }

  public int getWrongCount() {
    int count = 0;
    for (Guess guess : this.getGuesses()) {
      if (guess.isFail()) {
        count++;
      }
    }
    return count;
  }

  public List<String> getWrongLetters() {
    List<String> letters = new ArrayList<String>();
    for (Guess guess : this.getGuesses()) {
      if (guess.isFail()) {
        letters.add(guess.getValue());
      }
    }
    return letters;
  }

  public List<String> getHitLetters() {
    List<String> letters = new ArrayList<String>();
    for (Guess guess : this.getGuesses()) {
      if (guess.isSuccess()) {
        letters.add(guess.getValue());
      }
    }
    return letters;
  }

  public void addGuesses(Guess guess) {
    this.guesses.add(guess);
  }

  public void ask() {
    System.out.print("Guess a letter: ");
    Scanner scan = new Scanner(System.in);

    boolean flg = true;
    String str = "";
    while (flg) {
      str = scan.next();
      if (str.length() != 0) {
        System.out.println("Oops.. choose one character");
      } else {
        break;
      }
    }
    scan.close();
    Guess guess = new Guess(str);
    getAnswer().guess(guess);
    addGuesses(guess);
    System.out.println("You are guessing: " + displayAnswer());
  }

  public void confirm() {
    int wrongCount = getWrongCount();
    List<String> wrongLetters = getWrongLetters();
    System.out.println("You have guessed(" + wrongCount + ") wrong letters: " + wrongLetters);
  }

  public void start() {
    System.out.println("Here's question:");
    System.out.println(displayAnswer());
  }

  public String displayAnswer() {
    String ans = getAnswer().getValue();
    List<String> letters = getHitLetters();
    String str = "";
    for (int i = 0; i < ans.length(); i++) {
      String character = Character.toString(ans.charAt(i));
      if (letters.contains(character)) {
        str += character;
      } else {
        str += '_';
      }
    }
    return str;
  }

  public void win() {
    String ans = getAnswer().getValue();
    System.out.println("You win");
    System.out.println("You have guessed \'" + ans + "\' correctly.");
  }

  public void lose() {
    String ans = getAnswer().getValue();
    System.out.println("You lose!");
    System.out.println("The correct word was \'" + ans + "\'!");
  }
}