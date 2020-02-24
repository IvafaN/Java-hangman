package version1.hangman;

import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.List;

import java.nio.file.Path;

public class Question {
    private String answer;

    Question() {
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

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Guess guess(Guess guess) {
        if (getAnswer().contains(guess.getValue())) {
            guess.Success();
        } else {
            guess.Fail();
        }
        return guess;
    }
}