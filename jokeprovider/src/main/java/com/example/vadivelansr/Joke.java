package com.example.vadivelansr;

import java.util.Random;

public class Joke {
    private static String[] jokes;
    private Random random;

    public Joke() {
        jokes = new String[3];
        jokes[0] = "To understand what recursion is, you must first understand recursion.";
        jokes[1] = "There are 10 types of people in this world.\n" +
                "Those who understand binary, and those who don't.\n";
        jokes[2] = "Debugging: Removing the needles from the haystack.";
        random = new Random();
    }
    public String getJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
