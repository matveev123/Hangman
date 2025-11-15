package functions.action;

import functions.data.Data;
import functions.input.Input;
import image.Human;

public class Game {

    final int TIME_TO_END = 3000;

    final int COUNT_OF_GUESS = 7;

    public final String PATTERN_OF_GUESS_CHECK = "^[ёа-яЁА-Я]{1}";// "INPUT in name???"
    public final String PATTERN_OF_DECISION_CHECK = "^(Y|y|N|n){1}$";
    public final String PATTERN_OF_REPLACE = "[^\\*]";

    public String inputS = "", res = "", patt = PATTERN_OF_GUESS_CHECK;/// input и класс Input


    Data data = new Data();
    Symbol symbol = new Symbol();
    Human human = new Human();


    public void start_menu(Input input) throws InterruptedException {//start_menu
        refresh();
        System.out.println("Игра началась!");
        int i = 0;
        while (i == 0) {
            System.out.println("Чтобы начать игру введите \"Y\", чтобы выйти из игры введите  \"N\"");
            res = input.inputCheck(1, PATTERN_OF_DECISION_CHECK);// перенести input check сюда и думать дальше!
            if (res.equals("Y") || res.equals("y"))
                i = 1;
            else if (res.equals("N") || res.equals("n")) {// выход  по людой клавише!!!
                exit_from_game();
            }

        }
    }

    public void guess(Input input) throws InterruptedException {
        int count_error = 0;
        refresh();
        while (true) {
            System.out.println("Подсказка:" + symbol.word);
            System.out.println(symbol.pse_word);
            inputS = input.inputCheck(2, patt);// типо в строку временную впихую Input(даннные введенные пользователем)
            // процесс отгадывания буквы
            if (symbol.word.contains(inputS)) {
                symbol.temp_char = inputS.charAt(0);
                // обнуление кол-ва повторов
                symbol.reset_mas();
                // сколько замен
                symbol.find_pos();
                // процесс замены повторов
                symbol.replace_symbol();
            } else {
                // ОШИБКА!!!
                patt = symbol.check_prev(count_error, patt, inputS);// отказ ошибки на дубликат символа!
                System.out.println("Ошибок:" + ++count_error);
                System.out.println(human.draw(count_error));
            }
            // losing game
            if (count_error == COUNT_OF_GUESS) {
                System.out.println("-------------------------------------------");
                System.out.println("Вы проиграли!");
                System.out.println("Хотите перезапустить игру введите \"Y\" или \"y\"? иначе \"N\" или \"n\" она будет завершена"); // для выхода? не строгая проверка?
                res = input.inputCheck(1, PATTERN_OF_DECISION_CHECK);// строго!!!!!!
                if (res.equals("Y") || res.equals("y")) {
                    count_error = 0;
                    refresh();
                } else {
                    exit_from_game();
                }
            }
            //win!
            if (symbol.pse_word.equals(symbol.word)) {
                System.out.println("Поздравляем! Вы выйграли ");
                System.out.println("Хотите перезапустить игру введите \"Y\" или \"y\"? иначе \"N\" или \"n\" она будет завершена"); // для выхода? не строгая проверка?
                res = input.inputCheck(1, PATTERN_OF_DECISION_CHECK);// строго!!!!!!
                if (res.equals("Y") || res.equals("y")) {
                    count_error = 0;
                    refresh();//final!
                } else {
                    exit_from_game();
                }
            }
        }
    }

    void refresh() {
        data.write_data();
        symbol.word = data.secret_data();
        symbol.pse_word = symbol.word.replaceAll(PATTERN_OF_REPLACE, "*");//final!
        System.out.println("-------------------------------------------");
    }

    void exit_from_game() throws InterruptedException {
        System.out.println("Выход из игры....");
        Thread.sleep(TIME_TO_END);
        System.exit(0);
    }
}
