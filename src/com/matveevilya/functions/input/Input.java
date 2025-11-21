package com.matveevilya.functions.input;

import com.matveevilya.functions.message.Message;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {


    public String inputValidation(String inputFromUser, String changeablePattern,int state){
        String resultOfValid = "";
        Message message = new Message();
        message.outputMessages(state);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(message.message1);
            inputFromUser = scanner.nextLine();
            Pattern pattern = Pattern.compile(changeablePattern);
            Matcher matcher = pattern.matcher(inputFromUser);
            if (matcher.find()) {
                resultOfValid = matcher.group();
                break;
            } else {
                System.out.println(message.message2);
            }
        }
        return resultOfValid;
    }
}
