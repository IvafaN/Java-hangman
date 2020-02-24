package version1.hangman;

public class Guess {
  private String value;
  final static int UNKNOWN = 0;
  final static int SUCCESS = 1;
  final static int FAIL = 9;
  private int result = 0;

  public Guess(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public void Success() {
    this.result = SUCCESS;
  }

  public void Fail() {
    this.result = FAIL;
  }

  public boolean isFail() {
    return this.result == FAIL;
  }

  public boolean isSuccess() {
    return this.result == SUCCESS;
  }
}