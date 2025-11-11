import functions.input.Input;
import functions.action.Game;

class Main {
    public static void main(String [] args) throws InterruptedException {

        Game game = new Game();
        Input input = new Input();

        game.start_menu(input);
        game.guess(input);
    }
}




