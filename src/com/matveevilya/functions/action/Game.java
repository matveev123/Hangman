package com.matveevilya.functions.action;


import com.matveevilya.functions.data.Data;
import com.matveevilya.functions.input.Input;
import com.matveevilya.functions.message.Message;
import image.Human;




public class Game {

    final int TIME_TO_END = 2000;

    final int COUNT_OF_GUESS = 7;

    public final String PATTERN_OF_GUESS_CHECK = "^[ёа-яЁА-Я]{1}";
    public final String PATTERN_OF_DECISION_CHECK = "^(Y|y|N|n){1}$";
    public final String PATTERN_OF_REPLACE = "[^\\*]";

    public String inputFromUser = "", letterToGuess = "", resultOfValid = "", patternToCheckDuplicates = PATTERN_OF_GUESS_CHECK, changeablePattern = "", test = "";


    Data data = new Data();
    Symbol symbol = new Symbol();
    Human human = new Human();
    Message message = new Message();
    Input input = new Input();

    public void startMenu() throws InterruptedException {
        refresh();
        System.out.println("Игра началась!");
        int i = 0;

        // начальное меню
        while (i == 0) {
            changeablePattern = PATTERN_OF_DECISION_CHECK;
            message.outputMessages(0);
            message.outputMessages(1);
            resultOfValid = input.inputValidation(inputFromUser, changeablePattern, 1);

            if (resultOfValid.equals("Y") || resultOfValid.equals("y")) {
                i = 1;
            }
            if (resultOfValid.equals("N") || resultOfValid.equals("n")) {
                exitFromTheGame();
            }
        }
    }

    public void guess() throws InterruptedException {

        int countOfError = 0;
        refresh();
        // основной процесс игры
        changeablePattern = patternToCheckDuplicates;
        while (true) {
            // подготовка к вводу пользователем
            System.out.println("Подсказка:" + symbol.secret);
            System.out.println(symbol.maskOfSecret);

            message.outputMessages(2);

            //ввод пользователя + валидация ввода
            letterToGuess = input.inputValidation(inputFromUser, changeablePattern, 2);

            letterToGuess = letterToGuess.toLowerCase();
            // процесс отгадывания буквы
            if (symbol.secret.contains(letterToGuess)) {
                symbol.charToGuess = letterToGuess.charAt(0);
                // обнуление количества повторов
                symbol.resetMas();
                // сколько замен
                symbol.findPositionOfLetter();
                // процесс замены повторов
                symbol.replaceSymbol();
            } else {
                test = patternToCheckDuplicates;
                // ОШИБКА!!!
                patternToCheckDuplicates = symbol.checkPrevInputErrorLetter(countOfError, patternToCheckDuplicates, letterToGuess);// отказ засчитывания ошибки на дубликат символа от User!
                if (test.equals(patternToCheckDuplicates)) {
                    System.out.println("Ошибка! Данную букву вы вводили ранее, смените свой выбор ввода буквы!");
                    //System.out.println("Введите букву ёщё раз, вы ранее вводили текущий символ");
                    continue;

                } else {
                    System.out.println("Ошибок:" + ++countOfError);
                    System.out.println(human.draw(countOfError));
                }


            }

            // losing game
            if (countOfError == COUNT_OF_GUESS) {
                message.outputMessages(4);
                // подготовка к вводу пользователем
                changeablePattern = PATTERN_OF_DECISION_CHECK;

                message.outputMessages(1);

                //запрос на выход из игры или следующий раунд?
                resultOfValid = input.inputValidation(inputFromUser, changeablePattern, 4);

                countOfError = chooseUser(countOfError);
            }


            //win!
            if (symbol.maskOfSecret.equals(symbol.secret)) {
                // подготовка ввода пользователем
                changeablePattern = PATTERN_OF_DECISION_CHECK;
                message.outputMessages(1);

                // Запрос на выход следующий раунд
                resultOfValid = input.inputValidation(inputFromUser, changeablePattern, 3);

                countOfError = chooseUser(countOfError);

            }
        }
    }

    int chooseUser(int countOfError) throws InterruptedException {
        if (resultOfValid.equals("Y") || resultOfValid.equals("y")) {
            countOfError = 0;
            refresh();
        }
        if (resultOfValid.equals("N") || resultOfValid.equals("n")) {
            exitFromTheGame();
        }
        return countOfError;
    }

    // чтение файла + создание маски заново для следующего раунда
    void refresh() {
        data.readData();
        symbol.secret = data.takeRandomWord();
        symbol.maskOfSecret = symbol.secret.replaceAll(PATTERN_OF_REPLACE, "*");
        System.out.println("-------------------------------------------");
    }

    void exitFromTheGame() throws InterruptedException {
        System.out.println("Выход из игры....");
        Thread.sleep(TIME_TO_END);
        System.exit(0);
    }
}
