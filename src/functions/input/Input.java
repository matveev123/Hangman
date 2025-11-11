package functions.input;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    public String inputCheck(int version, String pat) {

        String message1, message2;
        if (version == 2) {
            message1 = "Введите букву";
            message2 = "Неверный ввод! \n Пожалуйста, введите букву(пр. \"а\",\"б\",\"г\") ";
        } else {
            message1 = "Введите символ";
            message2 = "Неверный ввод \n Пожалуйста, введите символ(пр. \"Y\",\"y\",\"N\",\"n\") ";
        }


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message1);


            String line = scanner.nextLine();

            Pattern pattern = Pattern.compile(pat);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                return matcher.group();
            } else {
                System.out.println(message2);
            }
        }

    }
}
