package se.kth.id1212.hangmanapp.server.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GetWord {

    public ArrayList<String> readFile() {

        ArrayList<String> words = new ArrayList<String>();

        Scanner wordFile = null;
        try {
            wordFile = new Scanner(new File("words.txt")).useDelimiter("\\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (wordFile.hasNext()) {
            words.add(wordFile.next());
        }

        return words;
    }

    public String getWord(ArrayList<String> words) {
        Random random = new Random();
        int upperLimit = words.size() - 1;
        int lowerLimit = 0;
        int wordNumber = random.nextInt(upperLimit - lowerLimit) + lowerLimit;
        String chosenWord = words.get(wordNumber);

        return chosenWord;
    }
}
