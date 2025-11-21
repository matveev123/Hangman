package com.matveevilya.functions.action;

import java.util.Arrays;

public class Symbol {

    public String secret, maskOfSecret,specificLetters = "Ёё";
    public char charToGuess;
    public int[] countOfDuplicates = new int[10];

    public void findPositionOfLetter() {
        char[] mas_chars = secret.toCharArray();
        int j = 0;

        for (int i = 0; i < mas_chars.length; i++)
            if (mas_chars[i] == charToGuess) {
                countOfDuplicates[j++] = i;
            }
    }


    public void replaceSymbol() {
        if (this.maskOfSecret.contains(String.valueOf(charToGuess)) || this.maskOfSecret.contains(this.specificLetters)) {
            System.out.println("Ранее вы вводили текущую букву!");
            return;
        }
        StringBuilder str_build = new StringBuilder(maskOfSecret);
        if (countOfDuplicates[1] == 0)
            str_build.setCharAt(countOfDuplicates[0], charToGuess);// замена "*" на уже угаданную
        else {
            int i = 0;
            while (!(countOfDuplicates[i] == 0 && i != 0)) {
                str_build.setCharAt(countOfDuplicates[i++], charToGuess);
            }
        }
        maskOfSecret = str_build.toString();

    }

    public void resetMas() {
        Arrays.fill(countOfDuplicates, 0);
    }

    public String checkPrevInputErrorLetter(int flag, String patt, String input) {// здесь делаем!
        if (flag >= 1) {//после добавления буквы к шаблону
            if (patt.contains(input))
                return patt;
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
