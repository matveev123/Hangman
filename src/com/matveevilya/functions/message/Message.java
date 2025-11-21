package com.matveevilya.functions.message;

public class Message {
    public String message1 = "", message2 = "";

    public void outputMessages(int state) {
        if (state == 2) {
            message1 = "Введите букву";
            message2 = "Неверный ввод! \n Пожалуйста, введите букву(пр. \"а\",\"б\",\"г\") ";
        } else {
            message1 = "Введите символ";
            message2 = "Неверный ввод \n Пожалуйста, введите символ(пр. \"Y\",\"y\",\"N\",\"n\") ";
        }
        if (state == 3) {
            System.out.println("Поздравляем! Вы выйграли ");
            System.out.println("Хотите перезапустить игру введите \"Y\" или \"y\"? иначе \"N\" или \"n\" она будет завершена");
        }
        if (state == 4) {
            System.out.println("-------------------------------------------");
            System.out.println("Вы проиграли!");
            System.out.println("Хотите перезапустить игру введите \"Y\" или \"y\"? иначе \"N\" или \"n\" она будет завершена");
        }
        if (state == 0) {
            System.out.println("Чтобы начать игру введите \"Y\", чтобы выйти из игры введите  \"N\"");
        }


    }
}
