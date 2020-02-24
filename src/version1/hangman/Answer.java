package version1.hangman;

import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.List;

import java.nio.file.Path;

public class Answer {
    private String value;

    Answer() {
        Path file = Paths.get("cities.txt");
        try {
            List<String> cities = Files.readAllLines(file);
            Random rand = new Random();
            int index = rand.nextInt(cities.size());
            setAnswer(cities.get(index));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getValue() {
        return this.value;
    }

    public void setAnswer(String answer) {
        this.value = answer;
    }

    public Guess guess(Guess guess) {
        if (getValue().contains(guess.getValue())) {
            guess.Success();
        } else {
            guess.Fail();
        }
        return guess;
    }
}