package functions.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {

    final String WORD_LIST= "src/resources/word_list.txt";
    List<String> list_of_words = new ArrayList<>();

    public void write_data() {
        try (BufferedReader fr = new BufferedReader(new FileReader(WORD_LIST))) {
            {
                String s;
                while ((s = fr.readLine()) != null) {
                    list_of_words.add(s);
                }
                fr.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public String secret_data() {
        Random random = new Random();
        return list_of_words.get((random.nextInt(list_of_words.toArray().length - 1) + 1));
    }



}
