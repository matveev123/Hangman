package com.matveevilya.functions.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {

    final String LIST_OF_WORDS = Paths.get("src", "resources", "dictionary.txt").toString();
    List<String> dictionaryFromListOfWords = new ArrayList<>();

    public void readData() {
        try (BufferedReader fr = new BufferedReader(new FileReader(LIST_OF_WORDS))) {
            {
                String s;
                while ((s = fr.readLine()) != null) {
                    dictionaryFromListOfWords.add(s);
                }
                fr.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public String takeRandomWord() {
        Random random = new Random();
        return dictionaryFromListOfWords.get((random.nextInt(dictionaryFromListOfWords.size())));
    }


}
