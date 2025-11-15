package functions.action;

import java.util.Arrays;

public class Symbol {

    public String word, pse_word;
    public char temp_char;
    public int[] count_dublicates = new int[10];

    public void find_pos() {
        char[] mas_chars = word.toCharArray();
        int j = 0;

        for (int i = 0; i < mas_chars.length; i++)
            if (mas_chars[i] == temp_char) {
                count_dublicates[j++] = i;
            }
    }


    public void replace_symbol() {
        StringBuilder str_build = new StringBuilder(pse_word);
        if (count_dublicates[1] == 0)
            str_build.setCharAt(count_dublicates[0], temp_char);// замена "*" на уже угаданную
        else {
            int i = 0;
            while (!(count_dublicates[i] == 0 && i != 0)) {
                str_build.setCharAt(count_dublicates[i++], temp_char);
            }
        }
        pse_word = str_build.toString();

    }

    public void reset_mas() {
        Arrays.fill(count_dublicates, 0);
    }

    public String check_prev(int flag, String patt, String input) {
        if (flag >= 1) {//уже добавили букву у шаблону
            StringBuilder str_build = new StringBuilder(patt);
            str_build.insert(15, input);
            patt = str_build.toString();

        } else {//меняем первый раз шаблон
            StringBuilder str_build = new StringBuilder(patt);
            str_build.insert(10, "&&[^" + input + "]");
            patt = str_build.toString();
        }
        return patt;
    }
}
