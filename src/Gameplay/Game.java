package Gameplay;


import Gameplay.Player.Controller;

public class Game {

    static Viewport viewport;
    static GameModel model;
    static Controller controller;

    static boolean bPlaying = true;

    /**
     * @param arg
     */
    public static void main(String... arg) {

        game();
    }

    public static void game() {
        model = new GameModel();
        controller = new Controller(model);
        viewport = new Viewport(model, controller);

        viewport.mainmenu();
    }

    public static void restart() {
        model = new GameModel();
        controller = new Controller(model);
        viewport = new Viewport(model, controller);

        viewport.startGame();
    }
}

