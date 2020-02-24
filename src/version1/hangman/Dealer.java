package version1.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealer {
  final int DEFAULT_LIFE = 10;
  final String MASK_CHARACTER = "_";
  private Question question;
  private List<Guess> guesses = new ArrayList<Guess>();

  public Dealer(Question question) {
    this.question = question;
  }

  public Question getQuestion() {
    return this.question;
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

  public void ask(Scanner scan) {
    String str = "";
    while (true) {
      System.out.print("Guess a letter: ");
      str = scan.next();
      if (str.length() != 1) {
        System.out.println("Oops.. choose one character");
      } else {
        break;
      }
    }
    Guess guess = new Guess(str);
    getQuestion().guess(guess);
    addGuesses(guess);
    System.out.println("You are guessing: " + displayAnswer());
  }

  public void confirm() {
    int wrongCount = getWrongCount();
    String wrongLetters = String.join(" ", getWrongLetters());
    System.out.println("You have guessed(" + wrongCount + ") wrong letters: " + wrongLetters);
  }

  public void start() {
    System.out.println("Here's question:");
    System.out.println(displayAnswer());
  }

  public String displayAnswer() {
    String ans = getQuestion().getAnswer();
    List<String> letters = getHitLetters();
    String str = "";
    for (int i = 0; i < ans.length(); i++) {
      String character = Character.toString(ans.charAt(i));
      if (letters.contains(character)) {
        str += character;
      } else if (character.equals(" ")) {
        str += character;
      } else {
        str += MASK_CHARACTER;
      }
    }
    return str;
  }

  public void win() {
    String ans = getQuestion().getAnswer();
    System.out.println("You win");
    System.out.println("You have guessed \'" + ans + "\' correctly.");
  }

  public void lose() {
    String ans = getQuestion().getAnswer();
    System.out.println("You lose!");
    System.out.println("The correct word was \'" + ans + "\'!");
  }
}