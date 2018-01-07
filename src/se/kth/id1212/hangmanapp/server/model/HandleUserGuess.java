package se.kth.id1212.hangmanapp.server.model;

import java.util.ArrayList;

public class HandleUserGuess {

    private int numOfGuesses = 0;
    private GetWord generate = new GetWord();
    private String selectedWord;
    StringBuilder sb = new StringBuilder();
    private int score = 0;
    private ArrayList<String> allWords = new ArrayList<String>();
    boolean isGameOver = false;

    public String initialMessage() {
        allWords = generate.readFile();
        String returnMessage = newGame();
        return returnMessage;
    }

    public String newGame() {

        isGameOver = false;

        selectedWord = generate.getWord(allWords);
        sb.setLength(0);
        for (int i = 0; i < selectedWord.length(); i++) {
            sb.append("-");
        }
        System.out.println(selectedWord);
        numOfGuesses = selectedWord.length();
        return sb.toString() + "#" + numOfGuesses;
    }

    public String letterGuess(char letter) {

        if (isGameOver) {
            return "Click NEW GAME to start a new round.";
        }

        ArrayList<Integer> allPositionsInWord = checkLetter(letter);
        if (allPositionsInWord.isEmpty()) {
            numOfGuesses--;
        }

        if (numOfGuesses == 0) {
            score--;
            isGameOver = true;
            return "You lose! The word was: " + selectedWord + "# #" + score;
        } else {
            for (int i = 0; i < allPositionsInWord.size(); i++) {
                sb.setCharAt(allPositionsInWord.get(i), letter);
            }
        }

        if (!sb.toString().contains("-")) {
            score++;
            isGameOver = true;
            return ("Congratulations! You win! # #" + score);
        }

        return sb.toString() + "#" + numOfGuesses;
    }

    public ArrayList<Integer> checkLetter(char letter) {

        ArrayList<Integer> allPositionsInWord = new ArrayList<Integer>();

        for (int i = 0; i < selectedWord.length(); i++) {
            if (Character.toLowerCase(letter) == Character.toLowerCase(selectedWord.charAt(i))) {
                allPositionsInWord.add(i);
            }
        }

        return allPositionsInWord;
    }

    public String wordGuess(String guessedWord) {

        if (isGameOver) {
            return "Click NEW GAME to start a new round.";
        }

        if (numOfGuesses == 0) {
            score--;
            isGameOver = true;
            return "You lose! The word was: " + selectedWord + "# #" + score;
        }

        if (guessedWord.equalsIgnoreCase(selectedWord)) {
            score++;
            isGameOver = true;
            return ("Congratulations! You win!# # " + score);
        } else {
            numOfGuesses--;
            return sb.toString() + "#" + numOfGuesses;
        }
    }
}
